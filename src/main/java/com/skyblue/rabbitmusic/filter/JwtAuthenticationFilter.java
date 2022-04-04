package com.skyblue.rabbitmusic.filter;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.skyblue.rabbitmusic.constant.SecurityConstants;
import com.skyblue.rabbitmusic.entity.User;
import com.skyblue.rabbitmusic.utils.JWTUtils;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        DocumentContext context = JsonPath.parse(request.getInputStream());
        String username = context.read("$.username", String.class);
        String password = context.read("$.password", String.class);
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password,new ArrayList<>()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
        User principal = ((User) authResult.getPrincipal());
        String token = JWTUtils.generateToken(principal.getUsername());
        response.setContentType("application/json;charset=UTF-8");
        response.addHeader(SecurityConstants.TOKEN_AUTHENTICATION, SecurityConstants.TOKEN_PREFIX + token);
//        response.setHeader("header", token);
        response.getWriter().write(token);
    }
}

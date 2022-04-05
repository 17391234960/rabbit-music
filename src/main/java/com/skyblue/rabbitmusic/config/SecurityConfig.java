package com.skyblue.rabbitmusic.config;

import com.skyblue.rabbitmusic.constant.SecurityConstants;
import com.skyblue.rabbitmusic.exception.RestAuthenticationEntryPoint;
import com.skyblue.rabbitmusic.filter.JwtAuthenticationFilter;
import com.skyblue.rabbitmusic.filter.JwtAuthorizationFilter;
import com.skyblue.rabbitmusic.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final Logger log = LoggerFactory.getLogger(JwtAuthorizationFilter.class);

    private final UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL).permitAll()
                .antMatchers(HttpMethod.GET, SecurityConstants.IMAGES).permitAll()
                .antMatchers(HttpMethod.GET, SecurityConstants.IMAGESURL).permitAll()
                .antMatchers("*", SecurityConstants.CREATE_TOKEN_URL).permitAll()
                .anyRequest().authenticated()
//                .and().formLogin().loginProcessingUrl("/login")
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), userService))
                .exceptionHandling().authenticationEntryPoint(new RestAuthenticationEntryPoint()) //添加错误访问页面
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
    }
}

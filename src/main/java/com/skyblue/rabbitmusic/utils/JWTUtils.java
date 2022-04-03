package com.skyblue.rabbitmusic.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.skyblue.rabbitmusic.constant.SecurityConstants;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtils {

    /**
     * 生成jwt 令牌
     * @param username 用户名
     * @return token
     */
    public static String generateToken(String username) {

        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + 1000 * SecurityConstants.EXPIRATION_TIME);
        Map<String, Object> header = new HashMap<>();
        header.put("typ","JWT");
        return JWT.create()
                .withHeader(header)
                .withSubject(username)
                .withExpiresAt(expireDate)
                .sign(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes(StandardCharsets.UTF_8)));
    }


    /**
     * 从token中解析当前用户
     * @param token jwt
     * @return 当前用户 不存在就是null
     */

    public static String getLoginUserByToken(String token) {
        String replaceToken = SecurityUtils.replaceTokenPrefix(token);
        return JWT.require(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes(StandardCharsets.UTF_8))).build().verify(replaceToken).getSubject();
    }

}

package com.skyblue.rabbitmusic.constant;

public interface SecurityConstants {

    /**
     * 令牌自定义标识
     */
    String TOKEN_AUTHENTICATION = "Authorization";

    /**
     * 令牌前缀
     */
    String TOKEN_PREFIX = "Bearer ";

    /**
     * 密钥
     */
    String SECRET = "RabbitMusic";

    long EXPIRATION_TIME = 864000000; // 10 days

    String CREATE_TOKEN_URL = "/tokens/**";

    String SIGN_UP_URL = "/users/";

    String IMAGES = "/images/getImage";

    String SITE_SETTING_URL = "/settings/site";

}

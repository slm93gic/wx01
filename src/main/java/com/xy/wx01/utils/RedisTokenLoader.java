package com.xy.wx01.utils;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.weixin4j.Configuration;
import org.weixin4j.loader.ITokenLoader;
import org.weixin4j.model.base.Token;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class RedisTokenLoader implements ITokenLoader {

    private final String ACCESS_TOKEN_KEY = "ats_wx100000000001";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private String appid;

    public RedisTokenLoader() {
        this.appid = Configuration.getOAuthAppId();//从配置文件中读取
    }

    @Override
    public Token get() {
        String accessToken = stringRedisTemplate.opsForValue().get(ACCESS_TOKEN_KEY);
//        log.info("wechat access_token:{}", accessToken);
        System.out.println(accessToken);
        return JSON.parseObject(accessToken, Token.class);
    }

    @Override
    public void refresh(Token token) {
//        log.info("refresh wechat access_token:{}", token.toString());
        System.out.println(token.toString());
        String accessToken = JSON.toJSONString(token);
        //ticket.getExpires_in() - 600L，是为了提前10分钟过期

        stringRedisTemplate.opsForValue().set(ACCESS_TOKEN_KEY, accessToken, token.getExpires_in() - 600L, TimeUnit.SECONDS);
    }
}
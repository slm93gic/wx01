package com.xy.wx01.utils;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.weixin4j.Configuration;
import org.weixin4j.loader.ITicketLoader;
import org.weixin4j.model.js.Ticket;
import org.weixin4j.model.js.TicketType;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class RedisTicketLoader implements ITicketLoader {

    private static final Logger LOG = LoggerFactory.getLogger(RedisTicketLoader.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private String appid;

    public RedisTicketLoader() {
        this.appid = Configuration.getOAuthAppId();
    }

    @Override
    public Ticket get(TicketType ticketType) {
        String key = "";
        if (null != ticketType) {
            switch (ticketType) {
                case JSAPI:
                    key = "wechat_ticket_jsapi";
                    break;
                case WX_CARD:
                    key = "wechat_ticket_wxcard";
                    break;
                default:
                    key = "wechat_ticket";
                    break;
            }
        }
        String ticket = stringRedisTemplate.opsForValue().get(key);
//        log.info("wechat ticket:{}", ticket);
        System.out.println(ticket);
        return JSON.parseObject(ticket, Ticket.class);
    }

    @Override
    public void refresh(Ticket ticket) {
        String key = "";
        if (null != ticket.getTicketType()) {
            switch (ticket.getTicketType()) {
                case JSAPI:
                    key = "wechat_ticket_jsapi_" + appid;
                    break;
                case WX_CARD:
                    key = "wechat_ticket_wxcard_" + appid;
                    break;
                default:
                    key = "wechat_ticket_" + appid;
                    break;
            }
        }
//        log.info("refresh wechat ticket:{}", ticket.toString());
        System.out.println(ticket.toString());
        String ticketValue = JSON.toJSONString(ticket);
        //ticket.getExpires_in() - 600L，是为了提前10分钟过期
        stringRedisTemplate.opsForValue().set(key, ticketValue, ticket.getExpires_in() - 600L, TimeUnit.SECONDS);
    }
}
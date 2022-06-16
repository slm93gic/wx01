package com.xy.wx01;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.weixin4j.Weixin;
import org.weixin4j.WeixinException;
import org.weixin4j.loader.ITicketLoader;
import org.weixin4j.loader.ITokenLoader;
import org.weixin4j.model.base.Token;
import org.weixin4j.spi.IEventMessageHandler;
import org.weixin4j.spi.INormalMessageHandler;
import org.weixin4j.spring.WeixinTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class WeixinTest {

    @Autowired
    private WeixinTemplate weixinTemplate;

    @Autowired
    private Weixin weixin;

    @Autowired
    private ITokenLoader tokenLoader;

    @Autowired
    private ITicketLoader ticketLoader;

    @Autowired
    private IEventMessageHandler eventMessageHandler;

    @Autowired
    private INormalMessageHandler normalMessageHandler;

    @Test
    public void weixinTest() throws WeixinException {
        Token token = weixin.getToken();
        System.out.println(token);
    }
}

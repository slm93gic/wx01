package com.xy.wx01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.weixin4j.spring.web.WeixinJieruController;

/**
 * 微信接入入口
 */
@Controller
@RequestMapping("/weixin/receiver")
public class ReceiverController extends WeixinJieruController {


}

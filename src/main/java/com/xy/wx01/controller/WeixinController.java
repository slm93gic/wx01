package com.xy.wx01.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.weixin4j.Weixin;
import org.weixin4j.WeixinException;
import org.weixin4j.component.MenuComponent;
import org.weixin4j.model.base.Token;
import org.weixin4j.model.menu.ClickButton;
import org.weixin4j.model.menu.Menu;
import org.weixin4j.model.menu.SingleButton;
import org.weixin4j.model.menu.ViewButton;
import org.weixin4j.spring.WeixinTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/wx")
public class WeixinController {

    @Autowired
    private WeixinTemplate weixinTemplate;

    @RequestMapping("/createMenu")
    @ResponseBody
    public Map<String, Object> createMenu(Model model, HttpServletRequest request) {
//        log.info("WeixinController.createMenu");
        String ctx = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();

//        log.info("ctx=" + ctx);
        System.out.println("ctx"+ctx);
        Map<String, Object> jsonData = new HashMap<String, Object>();
        jsonData.put("code", 0);
        jsonData.put("message", "微信菜单创建成功");
        try {
            Menu menu = new Menu();
            //创建菜单按钮
            List<SingleButton> buttons = new ArrayList<SingleButton>();
            menu.setButton(buttons);

            //http://tz3ydw.natappfree.cc/wx01/
            SingleButton btn1 = new ViewButton("主界面X", ctx + "/");
            buttons.add(btn1);

            SingleButton btn2 = new ViewButton("Y界面", ctx + "/toHome");
            buttons.add(btn2);

            SingleButton btn3 = new ClickButton("界面Z", "hello click!");
            buttons.add(btn3);

            //设置子菜单
            System.out.println(menu.toJSONObject().toString());

            //创建自定义菜单
            Weixin weixin = weixinTemplate.getWeixinFactory().getWeixin();
            MenuComponent menu1 = weixin.menu();
            menu1.create(menu);
            model.addAttribute("message", "微信菜单创建成功");
        } catch (Exception e) {
//            log.error(e.getMessage());
            System.err.println(e.getMessage());
            jsonData.put("code", -1);
            jsonData.put("message", "微信菜单创建失败，原因：" + e.getMessage());
        }

        return jsonData;
    }

    @RequestMapping("/deleteMenu")
    @ResponseBody
    public Map<String,Object> deleteMenu(){
        Map<String, Object> jsonData = new HashMap<String, Object>();
        jsonData.put("code", 0);
        jsonData.put("message", "微信菜单删除成功");
        try {
            Weixin weixin = weixinTemplate.getWeixinFactory().getWeixin();
            MenuComponent menu = weixin.menu();
            menu.delete();
        } catch (WeixinException e) {
            e.printStackTrace();
            jsonData.put("code", -1);
            jsonData.put("message", "微信菜单删除失败，原因："+e.getMessage());
        }
        return jsonData;
    }
}

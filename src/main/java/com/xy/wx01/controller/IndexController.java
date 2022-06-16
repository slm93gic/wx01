package com.xy.wx01.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class IndexController {

    @RequestMapping("")
    public String toIndex(){
//        log.info("aaa");
        return "index";
    }

    @RequestMapping("/toHome")
    public String toHome(){
        return "home";
    }
}

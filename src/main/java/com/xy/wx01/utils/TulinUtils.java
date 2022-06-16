package com.xy.wx01.utils;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.util.HashMap;
import java.util.Map;

public class TulinUtils {

    private final static String BASE = "http://openapi.tuling123.com/openapi/api/v2";
    private final static String BASE1 = "http://api.qingyunke.com/api.php";

    public static void main(String[] args) {
        freeGetMessage("您好");
    }

    public static String freeGetMessage(String message) {
        Map<String, Object> map = new HashMap<>();
        map.put("appid", 0);
        map.put("key", "free");
        map.put("msg", message);
        String post = HttpUtil.get(BASE1, map);
        return JSONUtil.parseObj(post).get("content").toString();
    }


    public static String getMessage(String message) {
        JSONObject params = new JSONObject();
        params.putOnce("reqType", 0);
        params.putOnce("perception", setMsg(message));
        params.putOnce("userInfo", setuserInfo());

        String post = HttpUtil.post(BASE, params.toString());
        Object results = JSONUtil.parseObj(post).get("results");
        return JSONUtil.parseArray(results).getJSONObject(0).getJSONObject("values").get("text").toString();
    }


    private static Map setMsg(String msg) {
        Map<String, Object> map = new HashMap<>();
        map.put("inputText", setText(msg));
        map.put("inputImage", setInputImage());
        map.put("selfInfo", setlocation());
        return map;
    }

    private static Map setText(String msg) {
        Map<String, Object> text = new HashMap<>();
        text.put("text", msg);
        return text;
    }

    private static Map setlocation() {
        Map<String, Object> map = new HashMap<>();
        map.put("city", "北京");
        map.put("province", "北京");
        map.put("street", "信息路");
        return map;
    }

    private static Map setInputImage() {
        Map<String, Object> map = new HashMap<>();
        map.put("url", "imageUrl");
        return map;
    }


    private static Map setuserInfo() {
        Map<String, Object> map = new HashMap<>();
        map.put("apiKey", "b0fa08693239485595cc83b4ee82f20e");
        map.put("userId", "b0fa08693239485595cc83b4ee82f20e");
        return map;
    }


}

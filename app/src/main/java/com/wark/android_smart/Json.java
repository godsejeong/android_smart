package com.wark.android_smart;

import org.json.simple.JSONObject;

/**
 * Created by pc on 2017-07-24.
 */

public class Json {
    String json;
    void jsonadd(){
        JSONObject inner = new JSONObject();
        inner.put("1","kbs");
        inner.put("2","sejeong");

        JSONObject outer = new JSONObject();
        outer.put("univ",inner);

        json = outer.toJSONString();
    }
}

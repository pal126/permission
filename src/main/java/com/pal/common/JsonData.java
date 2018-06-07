package com.pal.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class JsonData {

    private boolean res;
    private String msg;
    private Object data;

    public JsonData(boolean res) {
        this.res = res;
    }

    public static JsonData success(String msg, Object data) {
        JsonData jsonData = new JsonData(true);
        jsonData.setMsg(msg);
        jsonData.setData(data);
        return jsonData;
    }

    public static JsonData success(String msg) {
        JsonData jsonData = new JsonData(true);
        jsonData.setMsg(msg);
        return jsonData;
    }

    public static JsonData success(Object data) {
        JsonData jsonData = new JsonData(true);
        jsonData.setData(data);
        return jsonData;
    }

    public static JsonData error(String msg) {
        JsonData jsonData = new JsonData(false);
        jsonData.setMsg(msg);
        return jsonData;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> map = new HashMap<>(6);
        map.put("res", res);
        map.put("msg", msg);
        map.put("data", data);
        return map;
    }

}

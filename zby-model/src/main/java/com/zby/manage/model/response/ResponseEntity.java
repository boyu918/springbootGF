package com.zby.manage.model.response;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zby
 * @time 2019/5/21 16:23
 */
@Data
public class ResponseEntity extends HashMap{
    private int code;
    private String msg;

    ResponseEntity(){
        put("code",0);
        put("msg","success");
    }
    public static ResponseEntity success(Map<String,Object> map) {
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.putAll(map);
        return responseEntity;
    }


    public static ResponseEntity success() {
        return new ResponseEntity();
    }
    public static ResponseEntity success(String msg) {
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.put("msg",msg);
        return responseEntity;
    }

    public static ResponseEntity error() {
        return error(500);
    }
    public static ResponseEntity error(int code) {
        return error(code,"未知异常，请联系管理员");
    }

    public static ResponseEntity error(int code, String msg) {
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.put("code",code);
        responseEntity.put("msg",msg);
        return responseEntity;
    }
    public ResponseEntity addData(Object data){
        this.put("data",data);
        return this;
    }
}

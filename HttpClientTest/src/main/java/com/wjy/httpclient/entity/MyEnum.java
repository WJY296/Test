package com.wjy.httpclient.entity;

/**
 * @author wangjingyang
 * @date 2021-01-10 15:48
 */
public enum  MyEnum {
    FLE(1,"111");
    private int code;
    private String msg;
    MyEnum(int code, String msg){
        this.code=code;
        this.msg=msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

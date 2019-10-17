package com.tlc.eduweb.modles.entity;

public class ResponseMsg {

    private String msg;
    private int ret;
    private Object data;
    public ResponseMsg(){
    }
    public ResponseMsg(String msg,int ret,Object data){
        this.msg=msg;
        this.ret=ret;
        this.data=data;
    }

    public int getRet() {
        return ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

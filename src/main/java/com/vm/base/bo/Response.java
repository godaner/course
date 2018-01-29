package com.vm.base.bo;


import java.util.HashMap;
import java.util.Map;

/**
 * <b>Title:</b>
 * <br/>
 * <br/>
 * <b>Description:</b>响应体
 * <br/>
 * <br/>
 * <b>Author:</b>ZhangKe
 * <br/>
 * <br/>
 * <b>Date:</b>2017/11/24 10:04
 */
public class Response {
    private int code;
    private Map<Object,Object> data = new HashMap<Object,Object>();
    private String msg;

    public Response() {
        super();
        setSuccess();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map getData() {
        return data;
    }

    public Object getData(Object key) {
        if (data.containsKey(key)) {
            return data.get(key);
        } else {
            return new Object();
        }
    }

    public void setData(Map<Object,Object> data) {
        this.data = data;
    }

    public void putData(Object key, Object value) {
        this.data.put(key, value);
    }

    public void setFailure() {
        this.setCode(ResponseCode.FAILURE.getCode());
        this.setMsg(ResponseCode.FAILURE.getName());
        this.data.clear();
    }

    public void setFailure(ResponseCode responseCodeEnum) {
        this.setCode(responseCodeEnum.getCode());
        this.setMsg(responseCodeEnum.getName());
        this.data.clear();
    }

    public void setSuccess() {
        this.setCode(ResponseCode.SUCCESS.getCode());
        this.setMsg(ResponseCode.SUCCESS.getName());
    }

    /**
     * <b>Title:</b>
     * <br/>
     * <br/>
     * <b>Description:</b>响应异常
     * <br/>
     * <br/>
     * <b>Author:</b>ZhangKe
     * <br/>
     * <br/>
     * <b>Date:</b>2017/11/24 10:03
     */
    public enum ResponseCode {

        //通用
        SUCCESS(1, "success"),
        FAILURE(2, "fail");

        int code;
        String name;

        ResponseCode(int code, String name) {
            this.code = code;
            this.name = name;
        }

        @Override
        public String toString() {
            return "ResponseCode{" +
                    "code=" + code +
                    ", name='" + name + '\'' +
                    "} " + super.toString();
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        ResponseCode() {

        }
    }

}

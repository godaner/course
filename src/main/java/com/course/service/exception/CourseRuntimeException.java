package com.course.service.exception;


import com.course.util.Response;

import java.util.Map;

public class CourseRuntimeException extends RuntimeException {
    protected int errorCode;
    protected Map params;

    public CourseRuntimeException(int errorCode, String message, Map params, Throwable e) {

        super(message, e);
        this.errorCode = errorCode;
        this.params = params;
    }

    public CourseRuntimeException(int errorCode, String message, Map data) {

        this(errorCode, message, data, null);
    }

    public CourseRuntimeException(int errorCode, String message) {
        this(errorCode, message, null, null);
    }

    public CourseRuntimeException(Response.ResponseCode errorCode) {
        this(errorCode.getCode(), errorCode.getMsg(), null, null);
    }

    public CourseRuntimeException(String message) {

        this(Response.ResponseCode.FAILURE.getCode(), message, null, null);
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(super.toString());
        if (getParams() != null) {
            Map args = getParams();
            for (Object object : args.keySet()) {
                String name = (String) object;
                sb.append(" " + name + ":").append(args.get(name)).append(";");
            }
        }
        return sb.toString();
    }


    public Map getParams() {
        return params;
    }

    public void setParams(Map params) {
        this.params = params;
    }


}

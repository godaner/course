package com.course.frontend.exception;

import com.course.service.exception.CourseRuntimeException;


public class UsersException extends CourseRuntimeException {
    public UsersException(int errorCode, String message) {
        super(errorCode, message);
    }

    public UsersException(String message) {
        super(message);
    }

    public enum ErrorCode {

        USERNAME_IS_EXITS(-101, "用户名已存在"),
        PASSWORD_IS_ERROR(-102, "密码错误"),
        USERNAME_IS_NOT_EXITS(-103, "用户不已存在");

        int code;
        String msg;

        ErrorCode(int code, String msg) {
            this.code = code;
            this.msg = msg;
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

        ErrorCode() {

        }
    }
}
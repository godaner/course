package com.course.service.exception;

import com.course.service.exception.base.CourseRuntimeException;


public class AdminsException extends CourseRuntimeException {
    public AdminsException(int errorCode, String errorMsg) {
        super(errorCode, errorMsg);
    }

    public AdminsException(String logMsg, int errorCode, String errorMsg) {
        super(logMsg, errorCode, errorMsg);
    }

    public AdminsException(String logMsg) {
        super(logMsg);
    }

    public enum ErrorCode {

        USERNAME_IS_EXITS(-601, "用户名已存在"),
        PASSWORD_IS_ERROR(-602, "密码错误"),
        USERNAME_IS_NOT_EXITS(-603, "用户名不存在"),
        UPDATE_USER_FAIL(-604, "更新用户失败"),
        USER_IS_NOT_EXITS(-605, "用户不存在"),
        PASSWORD_IS_NULL(-606, "密码为空");

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

package com.course.service.exception;

import com.course.service.exception.base.CourseRuntimeException;


public class UsersException extends CourseRuntimeException {
    public UsersException(int errorCode, String errorMsg) {
        super(errorCode, errorMsg);
    }

    public UsersException(String logMsg, int errorCode, String errorMsg) {
        super(logMsg, errorCode, errorMsg);
    }

    public UsersException(String logMsg) {
        super(logMsg);
    }

    public enum ErrorCode {

        USERNAME_IS_EXITS(-101, "用户名已存在"),
        PASSWORD_IS_ERROR(-102, "密码错误"),
        USERNAME_IS_NOT_EXITS(-103, "用户名不存在"),
        UPDATE_USER_FAIL(-104, "更新用户失败"),
        USER_IS_NOT_EXITS(-105, "用户不存在"),
        PASSWORD_IS_NULL(-106, "密码为空");

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

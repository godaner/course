package com.course.service.exception;

import com.course.service.exception.base.CourseRuntimeException;


public class CoursesException extends CourseRuntimeException {
    public CoursesException(int errorCode, String errorMsg) {
        super(errorCode, errorMsg);
    }

    public CoursesException(String logMsg, int errorCode, String errorMsg) {
        super(logMsg, errorCode, errorMsg);
    }

    public CoursesException(String logMsg) {
        super(logMsg);
    }

    public enum ErrorCode {

        COURSE_IS_NOT_EXITS(-201, "用户不存在"),
        UPDATE_COURSE_FAIL(-202, "用户不存在");
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

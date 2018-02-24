package com.course.service.exception;

import com.course.service.exception.base.CourseRuntimeException;


public class TagGroupsException extends CourseRuntimeException {
    public TagGroupsException(int errorCode, String errorMsg) {
        super(errorCode, errorMsg);
    }

    public TagGroupsException(String logMsg, int errorCode, String errorMsg) {
        super(logMsg, errorCode, errorMsg);
    }

    public TagGroupsException(String logMsg) {
        super(logMsg);
    }

    public enum ErrorCode {
        TAG_GROUP_NAME_IS_NULL(-301, "标签分组名为空"), TAG_GROUP_NAME_IS_EXITS(-302, "标签分组名已存在");
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

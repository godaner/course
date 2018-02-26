package com.course.service.exception;

import com.course.service.exception.base.CourseRuntimeException;


public class TagsException extends CourseRuntimeException {
    public TagsException(int errorCode, String errorMsg) {
        super(errorCode, errorMsg);
    }

    public TagsException(String logMsg, int errorCode, String errorMsg) {
        super(logMsg, errorCode, errorMsg);
    }

    public TagsException(String logMsg) {
        super(logMsg);
    }

    public enum ErrorCode {

        UPDATE_TAG_FAIL(-901, "更新标签失败"),
        INSERT_TAG_FAIL(-902, "新增标签失败"),
        TAG_NAME_IS_NULL(-903, "标签名为空"),
        TAG_NAME_IS_NOT_EXITS(-904, "标签名已存在"),
        TAG_GROUP_IS_NOT_EXITS(-905, "标签分组不存在");
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

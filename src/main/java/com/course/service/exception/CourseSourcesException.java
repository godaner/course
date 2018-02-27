package com.course.service.exception;

import com.course.service.exception.base.CourseRuntimeException;


public class CourseSourcesException extends CourseRuntimeException {
    public CourseSourcesException(int errorCode, String errorMsg) {
        super(errorCode, errorMsg);
    }

    public CourseSourcesException(String logMsg, int errorCode, String errorMsg) {
        super(logMsg, errorCode, errorMsg);
    }

    public CourseSourcesException(String logMsg) {
        super(logMsg);
    }

    public enum ErrorCode {

        INSERT_COURSE_SOURCE_FAIL(-801, "课程视频资源插入数据库失败"),
        WRITE_COURSE_SOURCE_FAIL(-802, "课程资源视频写入磁盘失败");
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

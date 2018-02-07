package com.course.service.exception.base;


import com.course.util.Response;

public class CourseRuntimeException extends RuntimeException {
    protected int errorCode;//=returnCode
    protected String errorMsg;//=returnMsg
    protected String logMsg;//=exception#getMessage()

    public CourseRuntimeException(int errorCode, String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
    }

    public CourseRuntimeException(String logMsg, int errorCode, String errorMsg) {
        super(logMsg);
        this.logMsg = logMsg;
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
    }

    public CourseRuntimeException(String logMsg) {
        super(logMsg);
        this.logMsg = logMsg;
        this.errorMsg = Response.ResponseCode.FAILURE.getMsg();
        this.errorCode = Response.ResponseCode.FAILURE.getCode();
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getLogMsg() {
        return logMsg;
    }

    public void setLogMsg(String logMsg) {
        this.logMsg = logMsg;
    }
}

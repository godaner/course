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
}

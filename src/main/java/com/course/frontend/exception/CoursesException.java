package com.course.frontend.exception;

import com.course.service.exception.CourseRuntimeException;

/**
 * Created by ZhangKe on 2018/1/31.
 */
public class CoursesException extends CourseRuntimeException {
    public CoursesException(int errorCode, String message) {
        super(errorCode, message);
    }

    public CoursesException(String message) {
        super(message);
    }
}

package com.course.frontend.exception;

import com.course.service.exception.CourseRuntimeException;


public class CoursesException extends CourseRuntimeException {
    public CoursesException(int errorCode, String message) {
        super(errorCode, message);
    }

    public CoursesException(String message) {
        super(message);
    }
}

package com.course.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

//@PropertySource(value = {"classpath:config/project.properties"}, encoding = "utf-8")
@Component
public class ProjectConfig {
    public static String COURSE_IMG_PATH;
    public static String COURSE_SRC_PATH;
    public static String USER_IMG_PATH;

    @Value("${course.course.img.path}")
    public void setCourseImgPath(String courseImgPath) {
        COURSE_IMG_PATH = courseImgPath;
    }

    @Value("${course.course.src.path}")
    public void setCourseSrcPath(String courseSrcPath) {
        COURSE_SRC_PATH = courseSrcPath;
    }

    @Value("${course.user.img.path}")
    public void setUserImgPath(String userImgPath) {
        USER_IMG_PATH = userImgPath;
    }
}


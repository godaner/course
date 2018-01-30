package com.course.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by ZhangKe on 2018/1/30.
 */
@PropertySource(value = {"classpath:config/project.properties"}, encoding = "utf-8")
@Component
public class ProjectConfig {
    public static String COURSE_IMG_PATH;

    @Value("${course.course.img.path}")
    public void setCourseImgPath(String courseImgPath) {
        COURSE_IMG_PATH = courseImgPath;
    }
}


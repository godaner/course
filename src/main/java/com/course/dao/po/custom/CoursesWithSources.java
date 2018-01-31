package com.course.dao.po.custom;

import com.course.dao.po.CourseSources;
import com.course.dao.po.Courses;

import java.util.List;

/**
 * Created by ZhangKe on 2018/1/31.
 */
public class CoursesWithSources extends Courses{
    private List<CourseSources> courseSourcesList;

    public List<CourseSources> getCourseSourcesList() {
        return courseSourcesList;
    }

    public void setCourseSourcesList(List<CourseSources> courseSourcesList) {
        this.courseSourcesList = courseSourcesList;
    }
}

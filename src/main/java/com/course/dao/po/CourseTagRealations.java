package com.course.dao.po;

/**
 * Created by ZhangKe on 2018/1/30.
 */
public class CourseTagRealations extends BasePo{
    private Long tagId;
    private Long courseId;

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}

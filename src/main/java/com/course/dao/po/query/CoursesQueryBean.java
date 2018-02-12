package com.course.dao.po.query;

import org.assertj.core.util.Lists;

import java.util.List;


public class CoursesQueryBean extends BaseQueryBean {
    private List<Long> tagIds;
    private List<Long> courseIds;
    private Long courseId;

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CoursesQueryBean() {
        tagIds = Lists.newArrayList();
    }

    public List<Long> getTagIds() {
        return tagIds;
    }

    public CoursesQueryBean setTagIds(List<Long> tagIds) {
        this.tagIds = tagIds;
        return this;
    }

    public List<Long> getCourseIds() {
        return courseIds;
    }

    public void setCourseIds(List<Long> courseIds) {
        this.courseIds = courseIds;
    }
}

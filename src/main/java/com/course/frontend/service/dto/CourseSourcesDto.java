package com.course.frontend.service.dto;

/**
 * Created by ZhangKe on 2018/1/31.
 */
public class CourseSourcesDto {
    private Long courseSourceId;
    private String courseSourceName;
    private String courseSourceUrl;
    private Integer courseSourceSort;
    private String courseSourceDescription;

    public Long getCourseSourceId() {
        return courseSourceId;
    }

    public void setCourseSourceId(Long courseSourceId) {
        this.courseSourceId = courseSourceId;
    }

    public String getCourseSourceName() {
        return courseSourceName;
    }

    public void setCourseSourceName(String courseSourceName) {
        this.courseSourceName = courseSourceName;
    }

    public String getCourseSourceUrl() {
        return courseSourceUrl;
    }

    public void setCourseSourceUrl(String courseSourceUrl) {
        this.courseSourceUrl = courseSourceUrl;
    }

    public Integer getCourseSourceSort() {
        return courseSourceSort;
    }

    public void setCourseSourceSort(Integer courseSourceSort) {
        this.courseSourceSort = courseSourceSort;
    }

    public String getCourseSourceDescription() {
        return courseSourceDescription;
    }

    public void setCourseSourceDescription(String courseSourceDescription) {
        this.courseSourceDescription = courseSourceDescription;
    }
}

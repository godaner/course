package com.course.frontend.service.dto;

/**
 * Created by ZhangKe on 2018/1/31.
 */
public class CourseSourcesDto {
    private Long courseSourceId;
    private String courseSourceName;
    private String courseWatchSourceUrl;
    private String courseDownloadSourceUrl;
    private Integer courseSourceSort;
    private String courseSourceDescription;

    public String getCourseDownloadSourceUrl() {
        return courseDownloadSourceUrl;
    }

    public void setCourseDownloadSourceUrl(String courseDownloadSourceUrl) {
        this.courseDownloadSourceUrl = courseDownloadSourceUrl;
    }

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

    public String getCourseWatchSourceUrl() {
        return courseWatchSourceUrl;
    }

    public void setCourseWatchSourceUrl(String courseWatchSourceUrl) {
        this.courseWatchSourceUrl = courseWatchSourceUrl;
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

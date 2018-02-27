package com.course.service.dto;


import org.springframework.web.multipart.MultipartFile;

public class CourseSourcesDto {
    private Long courseSourceId;
    private String courseSourceName;
    private String courseWatchSourceUrl;
    private String courseDownloadSourceUrl;
    private Integer courseSourceSort;
    private String courseSourceDescription;
    private Long courseId;
    private MultipartFile sourceFile;

    public MultipartFile getSourceFile() {
        return sourceFile;
    }

    public void setSourceFile(MultipartFile sourceFile) {
        this.sourceFile = sourceFile;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

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

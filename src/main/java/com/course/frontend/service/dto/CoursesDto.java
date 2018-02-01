package com.course.frontend.service.dto;

import com.course.dao.po.CourseSources;

import java.util.List;


public class CoursesDto {
    protected Long courseId;
    private String imgUrl;
    private String name;
    private String description;
    private Long downloadNumber;
    private Long watchNumber;
    private Long collectNumber;
    private List<CourseSourcesDto> courseSourcesDtoList;

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getDownloadNumber() {
        return downloadNumber;
    }

    public void setDownloadNumber(Long downloadNumber) {
        this.downloadNumber = downloadNumber;
    }

    public Long getWatchNumber() {
        return watchNumber;
    }

    public void setWatchNumber(Long watchNumber) {
        this.watchNumber = watchNumber;
    }

    public Long getCollectNumber() {
        return collectNumber;
    }

    public void setCollectNumber(Long collectNumber) {
        this.collectNumber = collectNumber;
    }

    public List<CourseSourcesDto> getCourseSourcesDtoList() {
        return courseSourcesDtoList;
    }

    public void setCourseSourcesDtoList(List<CourseSourcesDto> courseSourcesDtoList) {
        this.courseSourcesDtoList = courseSourcesDtoList;
    }
}

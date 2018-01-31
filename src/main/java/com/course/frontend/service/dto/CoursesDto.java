package com.course.frontend.service.dto;

import com.course.dao.po.CourseSources;

import java.util.List;

/**
 * Created by ZhangKe on 2018/1/30.
 */
public class CoursesDto {
    protected Long courseId;
    private String imgUrl;
    private String name;
    private String description;
    private Long downloadNumber;
    private Long learnNumber;
    private List<CourseSourcesDto> courseSourcesDtoList;

    @Override
    public String toString() {
        return "CoursesDto{" +
                "courseId=" + courseId +
                ", imgUrl='" + imgUrl + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", downloadNumber='" + downloadNumber + '\'' +
                ", learnNumber='" + learnNumber + '\'' +
                '}';
    }

    public List<CourseSourcesDto> getCourseSourcesDtoList() {
        return courseSourcesDtoList;
    }

    public void setCourseSourcesDtoList(List<CourseSourcesDto> courseSourcesDtoList) {
        this.courseSourcesDtoList = courseSourcesDtoList;
    }

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

    public Long getLearnNumber() {
        return learnNumber;
    }

    public void setLearnNumber(Long learnNumber) {
        this.learnNumber = learnNumber;
    }
}

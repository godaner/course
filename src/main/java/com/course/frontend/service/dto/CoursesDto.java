package com.course.frontend.service.dto;

/**
 * Created by ZhangKe on 2018/1/30.
 */
public class CoursesDto {
    protected Long courseId;
    private String imgUrl;
    private String name;
    private String description;
    private String downloadNumber;
    private String learnNumber;

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

    public String getDownloadNumber() {
        return downloadNumber;
    }

    public void setDownloadNumber(String downloadNumber) {
        this.downloadNumber = downloadNumber;
    }

    public String getLearnNumber() {
        return learnNumber;
    }

    public void setLearnNumber(String learnNumber) {
        this.learnNumber = learnNumber;
    }
}

package com.course.service.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public class CoursesDto {
    protected Long courseId;
    private String imgUrl;
    private String name;
    private String description;
    private Long downloadNumber;
    private Long watchNumber;
    private Long collectNumber;

    private MultipartFile courseImgFile;
    /**
     * 创建时间，unixtimestamp
     */
    protected Integer createTime;
    /**
     * 更新时间,unixtimestamp
     */
    protected Integer updateTime;
    /**
     * 是否被删除
     */
    protected Byte status;

    public MultipartFile getCourseImgFile() {
        return courseImgFile;
    }

    public void setCourseImgFile(MultipartFile courseImgFile) {
        this.courseImgFile = courseImgFile;
    }

    private List<CourseSourcesDto> courseSourcesDtoList;


    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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

package com.course.dao.po;

/**
 * Created by ZhangKe on 2018/1/30.
 */
public class Courses extends BasePo{
    private String imgUrl;
    private String name;
    private String description;
    private Long downloadNumber;
    private Long learnNumber;

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

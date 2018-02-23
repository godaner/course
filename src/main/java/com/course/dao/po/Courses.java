package com.course.dao.po;


public class Courses extends BasePo{
    public static final String IMG_URL_PREFIX = "/courses/img/";
    public static final Long EMPTY_DOWNLOAD_NUMBER = 0L;
    public static final Long EMPTY_COLLECT_NUMBER = 0L;
    public static final Long EMPTY_WATCH_NUMBER = 0L;
    private String imgUrl;
    private String name;
    private String description;
    private Long downloadNumber;
    private Long watchNumber;
    private Long collectNumber;

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
}

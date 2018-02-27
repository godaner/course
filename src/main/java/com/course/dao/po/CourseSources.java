package com.course.dao.po;

public class CourseSources extends BasePo {
    public final static String DOWNLOAD_URL_PREFIX = "/courses/download/";
    public final static String WATCH_URL_PREFIX = "/courses/src/";
    public final static Integer DEFAULT_SORT = 0;
    private String name;
    private String watchUrl;
    private String downloadUrl;
    private Integer sort;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWatchUrl() {
        return watchUrl;
    }

    public void setWatchUrl(String watchUrl) {
        this.watchUrl = watchUrl;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

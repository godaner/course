package com.vm.dao.po.query;

import java.util.List;

/**
 * Created by ZhangKe on 2018/1/30.
 */
public class CoursesQueryBean {
    private String keyword;
    private Byte status;
    private List<Long> tagIds;

    public List<Long> getTagIds() {
        return tagIds;
    }

    public CoursesQueryBean setTagIds(List<Long> tagIds) {
        this.tagIds = tagIds;
        return this;
    }

    public Byte getStatus() {
        return status;
    }

    public CoursesQueryBean setStatus(Byte status) {
        this.status = status;
        return this;
    }

    public String getKeyword() {
        return keyword;
    }

    public CoursesQueryBean setKeyword(String keyword) {
        this.keyword = keyword;return this;
    }
}

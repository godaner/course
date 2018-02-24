package com.course.dao.po.query;


import java.util.List;

public class TagQueryBean extends BaseQueryBean {
    private String tagName;
    private List<Long> tagIds;

    public List<Long> getTagIds() {
        return tagIds;
    }

    public void setTagIds(List<Long> tagIds) {
        this.tagIds = tagIds;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}

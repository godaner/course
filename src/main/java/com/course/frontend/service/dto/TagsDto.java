package com.course.frontend.service.dto;


public class TagsDto {
    private Long tagId;
    private String tagName;

    @Override
    public String toString() {
        return "TagsDto{" +
                "tagId=" + tagId +
                ", tagName='" + tagName + '\'' +
                '}';
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }
}

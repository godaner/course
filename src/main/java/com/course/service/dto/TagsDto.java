package com.course.service.dto;


public class TagsDto {
    private Long tagId;
    private String tagName;
    private Byte status;
    private Long TagGroupId;

    public Long getTagGroupId() {
        return TagGroupId;
    }

    public void setTagGroupId(Long tagGroupId) {
        TagGroupId = tagGroupId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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

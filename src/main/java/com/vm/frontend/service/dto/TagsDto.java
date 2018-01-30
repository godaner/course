package com.vm.frontend.service.dto;

/**
 * Created by ZhangKe on 2018/1/30.
 */
public class TagsDto {
    private Long tagId;
    private String tagName;

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

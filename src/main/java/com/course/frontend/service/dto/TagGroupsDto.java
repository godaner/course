package com.course.frontend.service.dto;

import java.util.List;

/**
 * Created by ZhangKe on 2018/1/30.
 */
public class TagGroupsDto {

    private Long tagGroupId;
    private String tagGroupName;
    private List<TagsDto> tags;

    @Override
    public String toString() {
        return "TagGroupsDto{" +
                "tagGroupId=" + tagGroupId +
                ", tagGroupName='" + tagGroupName + '\'' +
                ", tags=" + tags +
                '}';
    }

    public String getTagGroupName() {
        return tagGroupName;
    }

    public void setTagGroupName(String tagGroupName) {
        this.tagGroupName = tagGroupName;
    }

    public Long getTagGroupId() {
        return tagGroupId;
    }

    public void setTagGroupId(Long tagGroupId) {
        this.tagGroupId = tagGroupId;
    }

    public List<TagsDto> getTags() {
        return tags;
    }

    public void setTags(List<TagsDto> tags) {
        this.tags = tags;
    }
}

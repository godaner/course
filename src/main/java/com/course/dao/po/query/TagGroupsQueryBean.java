package com.course.dao.po.query;


import java.util.List;

public class TagGroupsQueryBean extends BaseQueryBean {
    private String tagGroupName;
    private Long tagGroupId;

    public Long getTagGroupId() {
        return tagGroupId;
    }

    public void setTagGroupId(Long tagGroupId) {
        this.tagGroupId = tagGroupId;
    }

    public String getTagGroupName() {
        return tagGroupName;
    }

    public void setTagGroupName(String tagGroupName) {
        this.tagGroupName = tagGroupName;
    }
}

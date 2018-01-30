package com.course.dao.po;

/**
 * Created by ZhangKe on 2018/1/30.
 */
public class TagGroupRealations extends BasePo{
    private Long tagId;
    private Long groupId;

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}

package com.course.dao.po.custom;

import com.course.dao.po.TagGroups;
import com.course.dao.po.Tags;

import java.util.List;

/**
 * Created by ZhangKe on 2018/1/30.
 */
public class TagGroupsWithTags extends TagGroups {
    private List<Tags> tags;

    public List<Tags> getTags() {
        return tags;
    }

    public void setTags(List<Tags> tags) {
        this.tags = tags;
    }
}

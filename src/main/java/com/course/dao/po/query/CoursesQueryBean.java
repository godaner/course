package com.course.dao.po.query;

import org.assertj.core.util.Lists;

import java.util.List;
import java.util.Set;


public class CoursesQueryBean extends BaseQueryBean {
    private List<Long> tagIds;

    public CoursesQueryBean() {
        tagIds = Lists.newArrayList();
    }

    public List<Long> getTagIds() {
        return tagIds;
    }

    public CoursesQueryBean setTagIds(List<Long> tagIds) {
        this.tagIds = tagIds;
        return this;
    }

}

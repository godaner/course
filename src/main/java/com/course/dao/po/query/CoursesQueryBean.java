package com.course.dao.po.query;

import java.util.List;
import java.util.Set;

/**
 * Created by ZhangKe on 2018/1/30.
 */
public class CoursesQueryBean extends BaseQueryBean {
    private List<Long> tagIds;

    public List<Long> getTagIds() {
        return tagIds;
    }

    public CoursesQueryBean setTagIds(List<Long> tagIds) {
        this.tagIds = tagIds;
        return this;
    }

}

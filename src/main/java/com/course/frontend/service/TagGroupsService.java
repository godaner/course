package com.course.frontend.service;

import com.course.dao.po.query.TagGroupsQueryBean;
import com.course.frontend.service.dto.TagGroupsDto;
import com.course.util.PageBean;

import java.util.List;

/**
 * Created by ZhangKe on 2018/1/30.
 */
public interface TagGroupsService {

    List<TagGroupsDto> getTagGroupsWithTags(PageBean pageBean, TagGroupsQueryBean queryBean);

    Long getTagGroupsWithTagsCount(PageBean pageBean, TagGroupsQueryBean queryBean);
}

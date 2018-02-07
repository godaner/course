package com.course.service;

import com.course.dao.po.query.TagGroupsQueryBean;
import com.course.service.dto.TagGroupsDto;
import com.course.util.PageBean;

import java.util.List;


public interface TagGroupsService {

    List<TagGroupsDto> getTagGroupsWithTags(PageBean pageBean, TagGroupsQueryBean queryBean);

    Long getTagGroupsWithTagsCount(PageBean pageBean, TagGroupsQueryBean queryBean);
}

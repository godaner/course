package com.vm.frontend.service;

import com.vm.dao.po.query.TagGroupsQueryBean;
import com.vm.frontend.service.dto.TagGroupsDto;
import com.vm.util.PageBean;

import java.util.List;

/**
 * Created by ZhangKe on 2018/1/30.
 */
public interface TagGroupsService {

    List<TagGroupsDto> getTagGroupsWithTags(PageBean pageBean, TagGroupsQueryBean queryBean);

    Long getTagGroupsWithTagsCount(PageBean pageBean, TagGroupsQueryBean queryBean);
}

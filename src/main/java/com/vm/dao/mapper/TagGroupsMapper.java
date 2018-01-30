package com.vm.dao.mapper;

import com.vm.dao.po.TagGroups;
import com.vm.dao.po.custom.TagGroupsWithTags;
import com.vm.dao.po.query.TagGroupsQueryBean;
import com.vm.frontend.service.dto.TagGroupsDto;
import com.vm.util.PageBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ZhangKe on 2018/1/30.
 */
public interface TagGroupsMapper extends BaseCrudMapper<TagGroups> {
    List<TagGroupsWithTags> getTagGroupsWithTags(@Param("pageBean") PageBean pageBean, @Param("queryBean") TagGroupsQueryBean queryBean);
    Long getTagGroupsWithTagsCount(@Param("pageBean") PageBean pageBean, @Param("queryBean") TagGroupsQueryBean queryBean);
}

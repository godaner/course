package com.course.dao.mapper;

import com.course.dao.po.TagGroups;
import com.course.dao.po.custom.TagGroupsWithTags;
import com.course.dao.po.query.TagGroupsQueryBean;
import com.course.util.PageBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface TagGroupsMapper extends BaseCrudMapper<TagGroups> {
    List<TagGroupsWithTags> getTagGroupsWithTags(@Param("page") PageBean pageBean, @Param("query") TagGroupsQueryBean queryBean);

    Long getTagGroupsWithTagsCount(@Param("page") PageBean pageBean, @Param("query") TagGroupsQueryBean queryBean);

    TagGroups getTagGroupByTagGroupId(@Param("query") TagGroupsQueryBean query);

    TagGroups getTagGroupByTagGroupName(@Param("query") TagGroupsQueryBean query);
}

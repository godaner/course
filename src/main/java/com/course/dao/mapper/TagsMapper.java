package com.course.dao.mapper;

import com.course.dao.po.Tags;
import com.course.dao.po.query.TagQueryBean;
import com.course.service.dto.TagsDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TagsMapper extends BaseCrudMapper<Tags> {
    int updateTagsStatusByTagIds(@Param("tagIds") List<Long> tagIds, @Param("status") Byte status);

    Tags getTagByTagName(@Param("query") TagQueryBean query);

    List<Tags> getTagsByTagsIds(@Param("query") TagQueryBean queryBean);
}

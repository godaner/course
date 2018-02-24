package com.course.dao.mapper;

import com.course.dao.po.Tags;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ZhangKe on 2018/1/30.
 */
public interface TagsMapper extends BaseCrudMapper<Tags> {
    int updateTagsStatusByTagIds(@Param("tagIds") List<Long> tagIds, @Param("status") Byte status);
}

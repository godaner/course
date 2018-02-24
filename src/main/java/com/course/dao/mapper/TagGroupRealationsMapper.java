package com.course.dao.mapper;

import com.course.dao.po.TagGroupRealations;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TagGroupRealationsMapper extends BaseCrudMapper<TagGroupRealations> {
    int updateRealationsStatusByTagGroupId(@Param("tagGroupId") Long tagGroupId, @Param("status") Byte status);

    List<Long> getTagIdsByTagGroupId(@Param("tagGroupId") Long tagGroupId, @Param("status") List<Byte> status);
}

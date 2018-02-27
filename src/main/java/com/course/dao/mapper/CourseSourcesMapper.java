package com.course.dao.mapper;

import com.course.dao.po.CourseSources;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface CourseSourcesMapper extends BaseCrudMapper<CourseSources> {

    int updateCourseSourcesStatusByIds(@Param("courseSourcesIdsList") List<Long> courseSourcesIdsList, @Param("status") Byte status);
}

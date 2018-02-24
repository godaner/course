package com.course.dao.mapper;

import com.course.dao.po.CourseTagRealations;
import com.course.dao.po.query.TagCourseRealationsQueryBean;
import com.course.dao.po.query.TagQueryBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface CourseTagRealationsMapper extends BaseCrudMapper<CourseTagRealations> {

    List<Long> getTagIdsOfCourse(@Param("query") TagCourseRealationsQueryBean query);

    int updateRealationsStatusByCourseId(@Param("courseId") Long courseId, @Param("status") Byte status);

    int updateRealationsStatusByTagIds(@Param("tagGroupIds") List<Long> tagGroupIds, @Param("status") Byte status);
}

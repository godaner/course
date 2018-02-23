package com.course.dao.mapper;

import com.course.dao.po.CourseTagRealations;
import com.course.dao.po.query.TagCourseRealationsQueryBean;
import com.course.dao.po.query.TagQueryBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ZhangKe on 2018/1/30.
 */
public interface CourseTagRealationsMapper extends BaseCrudMapper<CourseTagRealations> {

    List<Long> getCourseTagIds(@Param("query") TagCourseRealationsQueryBean query);

    int updateCourseTagRealationsStatusByCourseId(@Param("courseId") Long courseId,@Param("status") Byte status);
}

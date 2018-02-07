package com.course.dao.mapper;

import com.course.dao.po.UserCollectCourse;
import com.course.dao.po.query.UserCollectCourseQueryBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserCollectCourseMapper extends BaseCrudMapper<UserCollectCourse> {


    List<Long> getCourseIdsByUserId(@Param("query") UserCollectCourseQueryBean userCollectCourseQueryBean);
}

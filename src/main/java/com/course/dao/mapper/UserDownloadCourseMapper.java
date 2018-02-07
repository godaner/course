package com.course.dao.mapper;

import com.course.dao.po.UserDownloadCourse;
import com.course.dao.po.query.UserDownloadCourseQueryBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDownloadCourseMapper extends BaseCrudMapper<UserDownloadCourse> {


    List<Long> getCourseIdsByUserId(@Param("query") UserDownloadCourseQueryBean userCollectCourseQueryBean);
}

package com.course.dao.mapper;

import com.course.dao.po.Courses;
import com.course.dao.po.query.CoursesQueryBean;
import com.course.util.PageBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ZhangKe on 2018/1/30.
 */
public interface CoursesMapper extends BaseCrudMapper<Courses> {


    Long getCoursesCount(@Param("page") PageBean pageBean, @Param("query") CoursesQueryBean queryBean);

    List<Courses> getCoursesList(@Param("page") PageBean pageBean, @Param("query") CoursesQueryBean queryBean);
}

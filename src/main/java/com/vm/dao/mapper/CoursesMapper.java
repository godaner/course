package com.vm.dao.mapper;

import com.vm.dao.po.Courses;
import com.vm.dao.po.query.CoursesQueryBean;
import com.vm.util.PageBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ZhangKe on 2018/1/30.
 */
public interface CoursesMapper extends BaseCrudMapper<Courses> {


    Long getCoursesCount(@Param("pageBean") PageBean pageBean, @Param("queryBean") CoursesQueryBean queryBean);

    List<Courses> getCoursesList(@Param("pageBean") PageBean pageBean, @Param("queryBean") CoursesQueryBean queryBean);
}

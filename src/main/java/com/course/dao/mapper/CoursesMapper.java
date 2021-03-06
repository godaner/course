package com.course.dao.mapper;

import com.course.dao.po.Courses;
import com.course.dao.po.Users;
import com.course.dao.po.custom.CoursesWithSources;
import com.course.dao.po.query.CourseSourcesQueryBean;
import com.course.dao.po.query.CoursesQueryBean;
import com.course.dao.po.query.UsersQueryBean;
import com.course.service.dto.CoursesDto;
import com.course.util.PageBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CoursesMapper extends BaseCrudMapper<Courses> {


    Long getCoursesCount(@Param("page") PageBean pageBean, @Param("query") CoursesQueryBean queryBean);

    List<Courses> getCoursesList(@Param("page") PageBean pageBean, @Param("query") CoursesQueryBean queryBean);

    CoursesWithSources getCourseWithSources(@Param("page") PageBean pageBean, @Param("query") CourseSourcesQueryBean queryBean, @Param("courseId") Long courseId);

    List<Courses> getCoursesByIds(@Param("query") CoursesQueryBean coursesQueryBean);

    List<Courses> getCoursesV2(@Param("page") PageBean page, @Param("que" +
            "ry") CoursesQueryBean query);

    Long getUsersCountV2(@Param("page") PageBean page, @Param("que" +
            "ry") CoursesQueryBean query);

    Courses getCourseByCourseId(@Param("query") CoursesQueryBean coursesQueryBean);
}

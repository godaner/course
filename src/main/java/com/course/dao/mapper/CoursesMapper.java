package com.course.dao.mapper;

import com.course.dao.po.Courses;
import com.course.dao.po.custom.CoursesWithSources;
import com.course.dao.po.query.CourseSourcesQueryBean;
import com.course.dao.po.query.CoursesQueryBean;
import com.course.frontend.service.dto.CoursesDto;
import com.course.util.PageBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CoursesMapper extends BaseCrudMapper<Courses> {


    Long getCoursesCount(@Param("page") PageBean pageBean, @Param("query") CoursesQueryBean queryBean);

    List<Courses> getCoursesList(@Param("page") PageBean pageBean, @Param("query") CoursesQueryBean queryBean);

    CoursesWithSources getCourseWithSources(@Param("page") PageBean pageBean, @Param("query") CourseSourcesQueryBean queryBean, @Param("courseId") Long courseId);

    List<CoursesDto> getCoursesByIds(@Param("query") CoursesQueryBean coursesQueryBean);
}

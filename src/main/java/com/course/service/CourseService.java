package com.course.service;

import com.course.dao.po.query.CoursesQueryBean;
import com.course.service.dto.CoursesDto;
import com.course.util.PageBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public interface CourseService {
    List<CoursesDto> getCoursesList(PageBean pageBean, CoursesQueryBean queryBean) throws Exception;

    Long getCoursesCount(PageBean pageBean, CoursesQueryBean queryBean) throws Exception;

    CoursesDto getCourseInfo(PageBean pageBean, Long courseId);

    void getCourseSrc(String name, Long courseId, HttpServletResponse httpServletResponse) throws Exception;

    void downloadCourseSrc(String name, Long courseId, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception;

    void getCourseImg(String name, HttpServletResponse httpServletResponse) throws Exception;

}
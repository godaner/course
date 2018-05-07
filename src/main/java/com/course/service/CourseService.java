package com.course.service;

import com.course.dao.po.query.CoursesQueryBean;
import com.course.service.dto.CoursesDto;
import com.course.service.dto.UsersDto;
import com.course.util.PageBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public interface CourseService {
    List<CoursesDto> getCoursesList(PageBean pageBean, CoursesQueryBean queryBean) throws Exception;

    Long getCoursesCount(PageBean pageBean, CoursesQueryBean queryBean) throws Exception;

    CoursesDto getCourseInfo(PageBean pageBean, Long courseId);

    void getCourseSrc(String name,String suffix, Long courseId, HttpServletResponse httpServletResponse) throws Exception;

    void downloadCourseSrc(String name, Long courseId, UsersDto onlineUser, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception;

    void getCourseImg(String name, HttpServletResponse httpServletResponse) throws Exception;

    List<CoursesDto> getCoursesV2(PageBean page, CoursesQueryBean query);

    Long getCoursesCountV2(PageBean page, CoursesQueryBean query);

    void updateCourses(CoursesDto coursesDto);

    void deleteCourse(Long courseId);

    void updateHeadFile(CoursesDto coursesDto);

    void addCourse(CoursesDto coursesDto);
}

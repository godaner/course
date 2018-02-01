package com.course.frontend.service.impl;

import com.course.dao.mapper.CoursesMapper;
import com.course.dao.po.BasePo;
import com.course.dao.po.Courses;
import com.course.dao.po.custom.CoursesWithSources;
import com.course.dao.po.query.CourseSourcesQueryBean;
import com.course.dao.po.query.CoursesQueryBean;
import com.course.frontend.exception.CoursesException;
import com.course.frontend.service.CourseService;
import com.course.frontend.service.dto.CourseSourcesDto;
import com.course.frontend.service.dto.CoursesDto;
import com.course.util.BaseService;
import com.course.util.PageBean;
import com.course.util.ProjectConfig;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.toList;


@Service
@Scope("prototype")
public class CourseServiceImpl extends BaseService implements CourseService {
    @Autowired
    private CoursesMapper coursesMapper;

    @Override
    public List<CoursesDto> getCoursesList(PageBean pageBean, CoursesQueryBean queryBean) throws Exception {
        if (isEmptyList(queryBean.getTagIds())) {
            queryBean.setTagIds(null);
        }
        return coursesMapper.getCoursesList(pageBean, queryBean).stream().parallel().map(
                courses -> {
                    return makeCourseDto(courses);
                }
        ).collect(toList());
    }

    @Override
    public Long getCoursesCount(PageBean pageBean, CoursesQueryBean queryBean) throws Exception {
        if (isEmptyList(queryBean.getTagIds())) {
            queryBean.setTagIds(null);
        }
        return coursesMapper.getCoursesCount(pageBean, queryBean);
    }

    @Override
    @Transactional
    public CoursesDto getCourseInfo(PageBean pageBean, Long courseId) {

        CourseSourcesQueryBean queryBean = new CourseSourcesQueryBean();
        queryBean.setStatus(BasePo.Status.NORMAL.getCode());
        CoursesWithSources coursesWithSources = coursesMapper.getCourseWithSources(pageBean, queryBean, courseId);
        return makeCourseDto(coursesWithSources);
    }

    @Override
    public void getCourseSrc(String name, Long courseId, HttpServletResponse httpServletResponse) throws Exception {

        Courses courses = coursesMapper.select(courseId);
        if (courses == null) {
            throw new CoursesException("getCourseSrc select is fail !! name is : " + name + " ,courseId is : " + courseId);
        }
        courses.setWatchNumber(courses.getWatchNumber() + 1);

        if (coursesMapper.update(courses) <= 0) {
            throw new CoursesException("getCourseSrc update is fail !! name is : " + name + " ,courseId is : " + courseId);
        }
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        try {
            IOUtils.copy(new FileInputStream(ProjectConfig.COURSE_SRC_PATH + name + ".mp4"), outputStream);
        } catch (IOException e) {
            e.printStackTrace();
            IOUtils.copy(new FileInputStream(ProjectConfig.COURSE_SRC_PATH + "default.mp4"), outputStream);
        }
    }

    @Override
    public void downloadCourseSrc(String name, Long courseId, HttpServletResponse httpServletResponse) throws Exception {
        Courses courses = coursesMapper.select(courseId);
        if (courses == null) {
            throw new CoursesException("getCourseSrc select is fail !! name is : " + name + " ,courseId is : " + courseId);
        }
        courses.setDownloadNumber(courses.getDownloadNumber() + 1);

        if (coursesMapper.update(courses) <= 0) {
            throw new CoursesException("getCourseSrc update is fail !! name is : " + name + " ,courseId is : " + courseId);
        }
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("video/mp4");
        httpServletResponse.addHeader("Content-Disposition", "attachment; filename=" + name + ".mp4");

        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        try {
            IOUtils.copy(new FileInputStream(ProjectConfig.COURSE_SRC_PATH + name + ".mp4"), outputStream);
        } catch (IOException e) {
            e.printStackTrace();
            IOUtils.copy(new FileInputStream(ProjectConfig.COURSE_SRC_PATH + "default.mp4"), outputStream);
        }
    }

    @Override
    public void getCourseImg(String name, HttpServletResponse httpServletResponse) throws Exception {
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        try {
            IOUtils.copy(new FileInputStream(ProjectConfig.COURSE_IMG_PATH + name + ".png"), outputStream);
        } catch (IOException e) {
            e.printStackTrace();
            IOUtils.copy(new FileInputStream(ProjectConfig.COURSE_IMG_PATH + "default.png"), outputStream);
        }
    }

    private CoursesDto makeCourseDto(CoursesWithSources coursesWithSources) {
        CoursesDto coursesDto = new CoursesDto();
        coursesDto.setCourseId(coursesWithSources.getId());
        coursesDto.setDescription(coursesWithSources.getDescription());
        coursesDto.setDownloadNumber(coursesWithSources.getDownloadNumber());
        coursesDto.setWatchNumber(coursesWithSources.getWatchNumber());
        coursesDto.setCollectNumber(coursesWithSources.getCollectNumber());
        coursesDto.setName(coursesWithSources.getName());
        coursesDto.setImgUrl(coursesWithSources.getImgUrl());
        List<CourseSourcesDto> courseSourcesDtos = coursesWithSources.getCourseSourcesList().stream().parallel().map(courseSources -> {

            CourseSourcesDto courseSourcesDto = new CourseSourcesDto();
            courseSourcesDto.setCourseSourceDescription(courseSources.getDescription());
            courseSourcesDto.setCourseSourceId(courseSources.getId());
            courseSourcesDto.setCourseSourceName(courseSources.getName());
            courseSourcesDto.setCourseSourceSort(courseSources.getSort());
            courseSourcesDto.setCourseWatchSourceUrl(courseSources.getWatchUrl());
            courseSourcesDto.setCourseDownloadSourceUrl(courseSources.getDownloadUrl());

            return courseSourcesDto;
        }).collect(toList());
        coursesDto.setCourseSourcesDtoList(courseSourcesDtos);
        return coursesDto;
    }

    private CoursesDto makeCourseDto(Courses courses) {
        CoursesDto coursesDto = new CoursesDto();
        coursesDto.setCourseId(courses.getId());
        coursesDto.setDescription(courses.getDescription());
        coursesDto.setDownloadNumber(courses.getDownloadNumber());
        coursesDto.setWatchNumber(courses.getWatchNumber());
        coursesDto.setCollectNumber(courses.getCollectNumber());
        coursesDto.setName(courses.getName());
        coursesDto.setImgUrl(courses.getImgUrl());
        return coursesDto;
    }
}

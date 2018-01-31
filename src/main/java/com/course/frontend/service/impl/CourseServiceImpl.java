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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by ZhangKe on 2018/1/30.
 */
@Service
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

        Courses courses = coursesMapper.select(courseId);
        if (courses == null) {
            throw new CoursesException("getCourseInfo select is fail !! pageBean is : " + pageBean + " ,courseId is : " + courseId);
        }
        courses.setLearnNumber(courses.getLearnNumber() + 1);

        if (coursesMapper.update(courses) <= 0) {
            throw new CoursesException("getCourseInfo update is fail !! pageBean is : " + pageBean + " ,courseId is : " + courseId);
        }

        CourseSourcesQueryBean queryBean = new CourseSourcesQueryBean();
        queryBean.setStatus(BasePo.Status.NORMAL.getCode());
        CoursesWithSources coursesWithSources = coursesMapper.getCourseWithSources(pageBean, queryBean, courseId);
        return makeCourseDto(coursesWithSources);
    }

    private CoursesDto makeCourseDto(CoursesWithSources coursesWithSources) {
        CoursesDto coursesDto = new CoursesDto();
        coursesDto.setCourseId(coursesWithSources.getId());
        coursesDto.setDescription(coursesWithSources.getDescription());
        coursesDto.setDownloadNumber(coursesWithSources.getDownloadNumber());
        coursesDto.setLearnNumber(coursesWithSources.getLearnNumber());
        coursesDto.setName(coursesWithSources.getName());
        coursesDto.setImgUrl(coursesWithSources.getImgUrl());
        List<CourseSourcesDto> courseSourcesDtos = coursesWithSources.getCourseSourcesList().stream().parallel().map(courseSources -> {

            CourseSourcesDto courseSourcesDto = new CourseSourcesDto();
            courseSourcesDto.setCourseSourceDescription(courseSources.getDescription());
            courseSourcesDto.setCourseSourceId(courseSources.getId());
            courseSourcesDto.setCourseSourceName(courseSources.getName());
            courseSourcesDto.setCourseSourceSort(courseSources.getSort());
            courseSourcesDto.setCourseSourceUrl(courseSources.getUrl());

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
        coursesDto.setLearnNumber(courses.getLearnNumber());
        coursesDto.setName(courses.getName());
        coursesDto.setImgUrl(courses.getImgUrl());
        return coursesDto;
    }
}

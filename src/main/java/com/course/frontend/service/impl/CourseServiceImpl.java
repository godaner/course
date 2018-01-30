package com.course.frontend.service.impl;

import com.course.dao.mapper.CoursesMapper;
import com.course.dao.po.BasePo;
import com.course.dao.po.query.CoursesQueryBean;
import com.course.frontend.service.CourseService;
import com.course.frontend.service.dto.CoursesDto;
import com.course.util.BaseService;
import com.course.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return coursesMapper.getCoursesList(pageBean, queryBean).stream().parallel().map(
                courses -> {
                    CoursesDto coursesDto = new CoursesDto();
                    coursesDto.setCourseId(courses.getId());
                    coursesDto.setDescription(courses.getDescription());
                    coursesDto.setDownloadNumber(courses.getDownloadNumber());
                    coursesDto.setLearnNumber(courses.getLearnNumber());
                    coursesDto.setName(courses.getName());
                    coursesDto.setImgUrl(courses.getImgUrl());
                    return coursesDto;
                }
        ).collect(toList());
    }

    @Override
    public Long getCoursesCount(PageBean pageBean, CoursesQueryBean queryBean) throws Exception {
        return coursesMapper.getCoursesCount(pageBean, queryBean);
    }
}

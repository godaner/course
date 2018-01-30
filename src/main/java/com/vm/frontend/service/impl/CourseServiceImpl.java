package com.vm.frontend.service.impl;

import com.vm.dao.mapper.CoursesMapper;
import com.vm.dao.po.BasePo;
import com.vm.dao.po.query.CoursesQueryBean;
import com.vm.frontend.service.CourseService;
import com.vm.frontend.service.dto.CoursesDto;
import com.vm.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by ZhangKe on 2018/1/30.
 */
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CoursesMapper coursesMapper;

    @Override
    public List<CoursesDto> getCoursesList(PageBean pageBean, CoursesQueryBean queryBean) throws Exception {
        return coursesMapper.getCoursesList(pageBean, queryBean.setStatus(BasePo.Status.NORMAL.getCode())).stream().parallel().map(
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
        return coursesMapper.getCoursesCount(pageBean, queryBean.setStatus(BasePo.Status.NORMAL.getCode()));
    }
}

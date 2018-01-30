package com.vm.frontend.service;

import com.vm.dao.po.query.CoursesQueryBean;
import com.vm.frontend.service.dto.CoursesDto;
import com.vm.util.PageBean;

import java.util.List;


public interface CourseService {
    List<CoursesDto> getCoursesList(PageBean pageBean, CoursesQueryBean queryBean) throws Exception;

    Long getCoursesCount(PageBean pageBean, CoursesQueryBean queryBean) throws Exception;
}

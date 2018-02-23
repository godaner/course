package com.course.service;

import com.course.service.dto.CourseTagRealationsDto;

import java.util.List;


public interface TagService {

    List<Long> getCourseTagIds(Long courseId);

    void updateCourseTagRealations(CourseTagRealationsDto courseTagRealationsDto);
}
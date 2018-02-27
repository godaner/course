package com.course.service;

import com.course.service.dto.CourseSourcesDto;

import java.util.List;

public interface CourseSourcesService {
    void addCourseSource(CourseSourcesDto courseSourcesDto);

    void deleteCourseSource(String courseSourcesIds);

    List<CourseSourcesDto> getCourseSource(Long courseId);
}

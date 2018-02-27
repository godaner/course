package com.course.service;

import com.course.service.dto.CourseSourcesDto;

public interface CourseSourcesService {
    void addCourseSource(CourseSourcesDto courseSourcesDto);

    void deleteCourseSource(String courseSourcesIds);
}

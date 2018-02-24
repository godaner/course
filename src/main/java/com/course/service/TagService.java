package com.course.service;

import com.course.service.dto.CourseTagRealationsDto;
import com.course.service.dto.TagsDto;

import java.util.List;


public interface TagService {

    List<Long> getTagIdsOfCourse(Long courseId);

    void updateCourseTagRealations(CourseTagRealationsDto courseTagRealationsDto);

    void addTag(TagsDto tagsDto);

    List<Long> getTagIdsOfTagGroup(Long tagGroupId);
}

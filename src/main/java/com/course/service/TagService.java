package com.course.service;

import com.course.service.dto.CourseTagRealationsDto;
import com.course.service.dto.TagsDto;

import java.util.List;


public interface TagService {

    List<Long> getTagIdsOfCourse(Long courseId) throws Exception;

    void updateCourseTagRealations(CourseTagRealationsDto courseTagRealationsDto) throws Exception;

    void addTag(TagsDto tagsDto) throws Exception;

    List<TagsDto> getTagsOfTagGroup(Long tagGroupId) throws Exception;

    void deleteTag(String tagIds) throws Exception;
}

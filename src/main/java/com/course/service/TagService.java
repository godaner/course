package com.course.service;

import java.util.List;


public interface TagService {

    List<Long> getCourseTagIds(Long courseId);

    void updateCourseTagRealations(Long courseId, List<Long> selectedTagIds);
}

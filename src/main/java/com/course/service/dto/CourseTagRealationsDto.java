package com.course.service.dto;


public class CourseTagRealationsDto {
    private Long courseId;
    private String selectedTagIds;

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getSelectedTagIds() {
        return selectedTagIds;
    }

    public void setSelectedTagIds(String selectedTagIds) {
        this.selectedTagIds = selectedTagIds;
    }
}

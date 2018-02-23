package com.course.service.impl;

import com.course.dao.mapper.CourseTagRealationsMapper;
import com.course.dao.po.BasePo;
import com.course.dao.po.CourseTagRealations;
import com.course.dao.po.query.TagCourseRealationsQueryBean;
import com.course.service.TagService;
import com.course.service.exception.TagsException;
import com.course.util.DateUtil;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;


@Service
@Scope("prototype")
public class TagServiceImpl implements TagService {

    @Autowired
    private CourseTagRealationsMapper courseTagRealationsMapper;


    @Override
    public List<Long> getCourseTagIds(Long courseId) {
        TagCourseRealationsQueryBean query = new TagCourseRealationsQueryBean();
        query.setStatus(Lists.newArrayList(BasePo.Status.NORMAL.getCode()));
        query.setCourseId(courseId);
        return courseTagRealationsMapper.getCourseTagIds(query);
    }

    @Override
    @Transactional
    public void updateCourseTagRealations(Long courseId, List<Long> selectedTagIds) {
        if (courseTagRealationsMapper.updateCourseTagRealationsStatusByCourseId(courseId, BasePo.Status.DELETED.getCode()) < 0) {
            throw new TagsException("updateCourseTagRealations#updateCourseTagRealationsStatusByCourseId is fail !! courseId is :" + courseId + " , selectedTagIds is " + selectedTagIds,
                    TagsException.ErrorCode.UPDATE_TAG_STATUS_FAIL.getCode(),
                    TagsException.ErrorCode.UPDATE_TAG_STATUS_FAIL.getMsg());
        }
        List<CourseTagRealations> courseTagRealations = makeCourseTagRealations(courseId, selectedTagIds);

        if (selectedTagIds.size() != courseTagRealationsMapper.batchInsert(courseTagRealations)) {
            throw new TagsException("updateCourseTagRealations#batchInsert is fail !! courseId is :" + courseId + " , selectedTagIds is " + selectedTagIds,
                    TagsException.ErrorCode.INSERT_TAG_STATUS_FAIL.getCode(),
                    TagsException.ErrorCode.INSERT_TAG_STATUS_FAIL.getMsg());
        }
    }

    private List<CourseTagRealations> makeCourseTagRealations(Long courseId, List<Long> selectedTagIds) {
        return selectedTagIds.stream().parallel().map(selectedTagId -> {
            return makeCourseTagRealation(courseId, selectedTagId);
        }).collect(toList());
    }

    private CourseTagRealations makeCourseTagRealation(Long courseId, Long selectedTagId) {
        CourseTagRealations courseTagRealations = new CourseTagRealations();
        Integer now = DateUtil.unixTime().intValue();
        courseTagRealations.setCourseId(courseId);
        courseTagRealations.setTagId(selectedTagId);
        courseTagRealations.setCreateTime(now);
        courseTagRealations.setUpdateTime(now);
        courseTagRealations.setStatus(CourseTagRealations.Status.NORMAL.getCode());
        return courseTagRealations;
    }

}

package com.course.service.impl;

import com.course.dao.mapper.CourseTagRealationsMapper;
import com.course.dao.mapper.TagGroupRealationsMapper;
import com.course.dao.mapper.TagGroupsMapper;
import com.course.dao.mapper.TagsMapper;
import com.course.dao.po.*;
import com.course.dao.po.query.TagCourseRealationsQueryBean;
import com.course.dao.po.query.TagGroupsQueryBean;
import com.course.dao.po.query.TagQueryBean;
import com.course.service.TagService;
import com.course.service.dto.CourseTagRealationsDto;
import com.course.service.dto.TagsDto;
import com.course.service.exception.TagsException;
import com.course.service.exception.UsersException;
import com.course.util.BaseService;
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
public class TagServiceImpl extends BaseService implements TagService {

    @Autowired
    private CourseTagRealationsMapper courseTagRealationsMapper;
    @Autowired
    private TagGroupRealationsMapper tagGroupRealationsMapper;
    @Autowired
    private TagGroupsMapper tagGroupsMapper;
    @Autowired
    private TagsMapper tagsMapper;


    @Override
    public List<Long> getCourseTagIds(Long courseId) {
        TagCourseRealationsQueryBean query = new TagCourseRealationsQueryBean();
        query.setStatus(Lists.newArrayList(BasePo.Status.NORMAL.getCode()));
        query.setCourseId(courseId);
        return courseTagRealationsMapper.getCourseTagIds(query);
    }

    @Override
    @Transactional
    public void updateCourseTagRealations(CourseTagRealationsDto courseTagRealationsDto) {
        Long courseId = courseTagRealationsDto.getCourseId();
        List<Long> selectedTagIds = Lists.newArrayList(courseTagRealationsDto.getSelectedTagIds().split(",")).stream().parallel().map(s -> {
            return Long.valueOf(s);
        }).collect(toList());
        if (courseTagRealationsMapper.updateRealationsStatusByCourseId(courseId, BasePo.Status.DELETED.getCode()) < 0) {
            throw new TagsException("updateCourseTagRealations#updateRealationsStatusByCourseId is fail !! courseId is :" + courseId + " , selectedTagIds is " + selectedTagIds,
                    TagsException.ErrorCode.UPDATE_TAG_FAIL.getCode(),
                    TagsException.ErrorCode.UPDATE_TAG_FAIL.getMsg());
        }
        List<CourseTagRealations> courseTagRealations = makeCourseTagRealations(courseId, selectedTagIds);

        if (selectedTagIds.size() != courseTagRealationsMapper.batchInsert(courseTagRealations)) {
            throw new TagsException("updateCourseTagRealations#batchInsert is fail !! courseId is :" + courseId + " , selectedTagIds is " + selectedTagIds,
                    TagsException.ErrorCode.INSERT_TAG_FAIL.getCode(),
                    TagsException.ErrorCode.INSERT_TAG_FAIL.getMsg());
        }
    }

    @Override
    public void addTag(TagsDto tagsDto) {
        //validate
        if (isEmptyString(tagsDto.getTagName())) {
            throw new TagsException("addTag tag name is null !! tagsDto is :" + tagsDto,
                    TagsException.ErrorCode.TAG_NAME_IS_NULL.getCode(),
                    TagsException.ErrorCode.TAG_NAME_IS_NULL.getMsg());
        }
        Tags tags = this.getTagByTagName(tagsDto.getTagName(), Lists.newArrayList(BasePo.Status.FORAZEN.getCode(), BasePo.Status.NORMAL.getCode()));
        if (null != tags) {
            throw new TagsException("addTag tag name is not exits !! tagsDto is :" + tagsDto,
                    TagsException.ErrorCode.TAG_NAME_IS_NOT_EXITS.getCode(),
                    TagsException.ErrorCode.TAG_NAME_IS_NOT_EXITS.getMsg());
        }

        //insert tag
        tags = makeAddTags(tagsDto);
        if (1 != tagsMapper.insert(tags)) {
            throw new UsersException("addTag tagsMapper#insert is fail !! tagsDto is :" + tagsDto);
        }

    }

    private Tags makeAddTags(TagsDto tagsDto) {
        Tags tags = new Tags();
        Integer now = DateUtil.unixTime().intValue();
        tags.setName(tagsDto.getTagName());
        tags.setCreateTime(now);
        tags.setUpdateTime(now);
        tags.setStatus(tagsDto.getStatus());
        return tags;
    }

    private Tags getTagByTagName(String tagName, List<Byte> status) {
        TagQueryBean query = new TagQueryBean();
        query.setStatus(status);
        query.setTagName(tagName);
        return tagsMapper.getTagByTagName(query);
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

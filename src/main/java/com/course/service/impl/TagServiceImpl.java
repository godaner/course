package com.course.service.impl;

import com.course.dao.mapper.CourseTagRealationsMapper;
import com.course.dao.mapper.TagGroupRealationsMapper;
import com.course.dao.mapper.TagGroupsMapper;
import com.course.dao.mapper.TagsMapper;
import com.course.dao.po.*;
import com.course.dao.po.query.TagCourseRealationsQueryBean;
import com.course.dao.po.query.TagGroupRealationsQueryBean;
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
    public List<Long> getTagIdsOfCourse(Long courseId) {
        TagCourseRealationsQueryBean query = new TagCourseRealationsQueryBean();
        query.setStatus(Lists.newArrayList(BasePo.Status.NORMAL.getCode()));
        query.setCourseId(courseId);
        return courseTagRealationsMapper.getTagIdsOfCourse(query);
    }

    @Override
    public List<TagsDto> getTagsOfTagGroup(Long tagGroupId) {
        //get tag ids
        TagGroupRealationsQueryBean query = new TagGroupRealationsQueryBean();
        query.setStatus(Lists.newArrayList(BasePo.Status.NORMAL.getCode(), BasePo.Status.FORAZEN.getCode()));
        query.setTagGroupId(tagGroupId);
        List<Long> tagIds = tagGroupRealationsMapper.getTagIdsOfTagGroup(query);

        if(isEmptyList(tagIds)){
            return Lists.newArrayList();
        }
        //get tags
        TagQueryBean queryBean = new TagQueryBean();
        queryBean.setStatus(Lists.newArrayList(BasePo.Status.NORMAL.getCode(), BasePo.Status.FORAZEN.getCode()));
        queryBean.setTagIds(tagIds);

        return tagsMapper.getTagsByTagsIds(queryBean).stream().parallel().map(tags -> {
            return makeTagDto(tags);
        }).collect(toList());
    }

    @Override
    public void deleteTag(String tagIds) {
        if (isEmptyString(tagIds)) {
            throw new TagsException("deleteTag tagIds is null !! tagIds is :" + tagIds,
                    TagsException.ErrorCode.TAG_IDS_IS_NULL.getCode(),
                    TagsException.ErrorCode.TAG_IDS_IS_NULL.getMsg());
        }
        List<Long> tagIdsList = Lists.newArrayList(tagIds.split(",")).stream().parallel().map(tagId -> {
            return Long.valueOf(tagId);
        }).collect(toList());
        if (0 > tagsMapper.updateTagsStatusByTagIds(tagIdsList, BasePo.Status.DELETED.getCode())) {
            throw new TagsException("deleteTag#updateTagsStatusByTagIds is fail !! tagIds is :" + tagIds,
                    TagsException.ErrorCode.UPDATE_TAG_FAIL.getCode(),
                    TagsException.ErrorCode.UPDATE_TAG_FAIL.getMsg());
        }
    }


    private TagsDto makeTagDto(Tags tags) {
        TagsDto tagsDto = new TagsDto();
        tagsDto.setTagName(tags.getName());
        tagsDto.setTagId(tags.getId());
        return tagsDto;
    }

    @Override
    @Transactional
    public void updateCourseTagRealations(CourseTagRealationsDto courseTagRealationsDto) {
        //delete old realations
        Long courseId = courseTagRealationsDto.getCourseId();
        if (courseTagRealationsMapper.updateRealationsStatusByCourseId(courseId, BasePo.Status.DELETED.getCode()) < 0) {
            throw new TagsException("updateCourseTagRealations#updateRealationsStatusByCourseId is fail !! courseTagRealationsDto is :" + courseTagRealationsDto,
                    TagsException.ErrorCode.UPDATE_TAG_FAIL.getCode(),
                    TagsException.ErrorCode.UPDATE_TAG_FAIL.getMsg());
        }
        //insert new realations
        if (isEmptyString(courseTagRealationsDto.getSelectedTagIds())) {
            return;
        }
        List<Long> selectedTagIds = Lists.newArrayList(courseTagRealationsDto.getSelectedTagIds().split(",")).stream().parallel().map(s -> {
            return Long.valueOf(s);
        }).collect(toList());
        List<CourseTagRealations> courseTagRealations = makeCourseTagRealations(courseId, selectedTagIds);
        if (selectedTagIds.size() != courseTagRealationsMapper.batchInsert(courseTagRealations)) {
            throw new TagsException("updateCourseTagRealations#batchInsert is fail !! courseId is :" + courseId + " , selectedTagIds is " + selectedTagIds,
                    TagsException.ErrorCode.INSERT_TAG_FAIL.getCode(),
                    TagsException.ErrorCode.INSERT_TAG_FAIL.getMsg());
        }
    }

    @Override
    @Transactional
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
        if (tagsDto.getTagGroupId() == null) {
            throw new TagsException("addTag tag group id is null !! tagsDto is :" + tagsDto);
        }

        TagGroups tagGroups = this.getTagGroupByTagGroupId(tagsDto.getTagGroupId(), Lists.newArrayList(BasePo.Status.FORAZEN.getCode(), BasePo.Status.NORMAL.getCode()));
        if (null == tagGroups) {
            throw new TagsException("addTag tag group is not exits !! tagsDto is :" + tagsDto,
                    TagsException.ErrorCode.TAG_GROUP_IS_NOT_EXITS.getCode(),
                    TagsException.ErrorCode.TAG_GROUP_IS_NOT_EXITS.getMsg());
        }
        //insert tag
        tags = makeAddTags(tagsDto);
        if (1 != tagsMapper.insert(tags)) {
            throw new UsersException("addTag tagsMapper#insert is fail !! tagsDto is :" + tagsDto);
        }
        //insert tag group realation
        TagGroupRealations tagGroupRealations = makeTagGroupRealation(tags, tagGroups);
        if (1 != tagGroupRealationsMapper.insert(tagGroupRealations)) {
            throw new UsersException("addTag tagGroupRealationsMapper#insert is fail !! tagsDto is :" + tagsDto);
        }

    }

    private TagGroupRealations makeTagGroupRealation(Tags tags, TagGroups tagGroups) {
        TagGroupRealations tagGroupRealations = new TagGroupRealations();
        Integer now = DateUtil.unixTime().intValue();
        tagGroupRealations.setGroupId(tagGroups.getId());
        tagGroupRealations.setTagId(tags.getId());
        tagGroupRealations.setCreateTime(now);
        tagGroupRealations.setUpdateTime(now);
        tagGroupRealations.setStatus(BasePo.Status.NORMAL.getCode());
        return tagGroupRealations;
    }

    private TagGroups getTagGroupByTagGroupId(Long tagGroupId, List<Byte> status) {
        TagGroupsQueryBean query = new TagGroupsQueryBean();
        query.setStatus(status);
        query.setTagGroupId(tagGroupId);
        return tagGroupsMapper.getTagGroupByTagGroupId(query);
    }

    private Tags makeAddTags(TagsDto tagsDto) {
        Tags tags = new Tags();
        Integer now = DateUtil.unixTime().intValue();
        tags.setName(tagsDto.getTagName());
        tags.setCreateTime(now);
        tags.setUpdateTime(now);
        tags.setStatus(tagsDto.getStatus());
        tags.setStatus(BasePo.Status.NORMAL.getCode());
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

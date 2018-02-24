package com.course.service.impl;

import com.course.dao.mapper.CourseTagRealationsMapper;
import com.course.dao.mapper.TagGroupRealationsMapper;
import com.course.dao.mapper.TagGroupsMapper;
import com.course.dao.mapper.TagsMapper;
import com.course.dao.po.BasePo;
import com.course.dao.po.TagGroups;
import com.course.dao.po.query.TagGroupsQueryBean;
import com.course.service.TagGroupsService;
import com.course.service.dto.TagGroupsDto;
import com.course.service.dto.TagsDto;
import com.course.service.exception.CoursesException;
import com.course.service.exception.TagGroupsException;
import com.course.service.exception.UsersException;
import com.course.util.BaseService;
import com.course.util.DateUtil;
import com.course.util.PageBean;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;


@Service
@Scope("prototype")
public class TagGroupsServiceImpl extends BaseService implements TagGroupsService {

    @Autowired
    private TagsMapper tagsMapper;
    @Autowired
    private TagGroupsMapper tagGroupsMapper;
    @Autowired
    private TagGroupRealationsMapper tagGroupRealationsMapper;
    @Autowired
    private CourseTagRealationsMapper courseTagRealationsMapper;

    @Override
    public List<TagGroupsDto> getTagGroupsWithTags(PageBean pageBean, TagGroupsQueryBean queryBean) {

        return tagGroupsMapper.getTagGroupsWithTags(pageBean, queryBean).stream().parallel().map(tagGroupsWithTags -> {
            TagGroupsDto tagGroupsDto = new TagGroupsDto();
            tagGroupsDto.setTagGroupId(tagGroupsWithTags.getId());
            tagGroupsDto.setTagGroupName(tagGroupsWithTags.getName());
            tagGroupsDto.setCreateTime(tagGroupsWithTags.getCreateTime());
            tagGroupsDto.setStatus(tagGroupsWithTags.getStatus());

            List<TagsDto> tagsDtos = tagGroupsWithTags.getTags().stream().parallel().map(tags -> {
                TagsDto tagsDto = new TagsDto();
                tagsDto.setTagId(tags.getId());
                tagsDto.setTagName(tags.getName());
                return tagsDto;
            }).collect(toList());

            tagGroupsDto.setTags(tagsDtos);

            return tagGroupsDto;
        }).collect(toList());
    }

    @Override
    public Long getTagGroupsWithTagsCount(PageBean pageBean, TagGroupsQueryBean queryBean) {
        return tagGroupsMapper.getTagGroupsWithTagsCount(pageBean, queryBean);
    }

    @Override
    public void updateTagGroup(TagGroupsDto tagGroupsDto) {
        if (isEmptyString(tagGroupsDto.getTagGroupName())) {
            throw new TagGroupsException("updateTagGroup group name is null !! tagGroupsDto is :" + tagGroupsDto,
                    TagGroupsException.ErrorCode.TAG_GROUP_NAME_IS_NULL.getCode(),
                    TagGroupsException.ErrorCode.TAG_GROUP_NAME_IS_NULL.getMsg());
        }

        if (null == this.getTagGroupByTagGroupId(tagGroupsDto.getTagGroupId(), Lists.newArrayList(BasePo.Status.FORAZEN.getCode(), BasePo.Status.NORMAL.getCode()))) {
            throw new TagGroupsException("updateTagGroup tag group not exits !! tagGroupsDto is :" + tagGroupsDto,
                    TagGroupsException.ErrorCode.TAG_GROUP_NAME_IS_NOT_EXITS.getCode(),
                    TagGroupsException.ErrorCode.TAG_GROUP_NAME_IS_NOT_EXITS.getMsg());
        }


        if (tagGroupsMapper.update(makeUpdateTagGroup(tagGroupsDto)) != 1) {
            throw new TagGroupsException("updateTagGroup is fail !! tagGroupsDto is :" + tagGroupsDto,
                    TagGroupsException.ErrorCode.UPDATE_TAG_GROUP_FAIL.getCode(),
                    TagGroupsException.ErrorCode.UPDATE_TAG_GROUP_FAIL.getMsg());
        }
    }

    private TagGroups makeUpdateTagGroup(TagGroupsDto tagGroupsDto) {
        TagGroups tagGroups = new TagGroups();
        tagGroups.setStatus(tagGroupsDto.getStatus());
        tagGroups.setUpdateTime(DateUtil.unixTime().intValue());
        tagGroups.setName(tagGroupsDto.getTagGroupName());
        tagGroups.setId(tagGroupsDto.getTagGroupId());
        return tagGroups;
    }

    private TagGroups getTagGroupByTagGroupId(Long tagGroupId, List<Byte> status) {
        TagGroupsQueryBean query = new TagGroupsQueryBean();
        query.setStatus(status);
        query.setTagGroupId(tagGroupId);
        return tagGroupsMapper.getTagGroupByTagGroupId(query);
    }

    @Override
    public void deleteTagGroup(Long tagGroupId) {
        //delete tag group
        TagGroups tagGroups = this.getTagGroupByTagGroupId(tagGroupId, Lists.newArrayList(BasePo.Status.NORMAL.getCode(), BasePo.Status.FORAZEN.getCode()));
        if (null != tagGroups) {
            tagGroups.setStatus(BasePo.Status.DELETED.getCode());
            if (tagGroupsMapper.update(tagGroups) != 1) {
                throw new CoursesException("tagGroupId update is fail !! tagGroupId is : " + tagGroupId);
            }
        }
        //delete tag
        List<Long> tagIds = tagGroupRealationsMapper.getTagIdsByTagGroupId(tagGroupId, Lists.newArrayList(BasePo.Status.NORMAL.getCode(), BasePo.Status.FORAZEN.getCode()));
        if (!isEmptyList(tagIds)) {
            if (0 > tagsMapper.updateTagsStatusByTagIds(tagIds, BasePo.Status.DELETED.getCode())) {
                throw new CoursesException("deleteTagGroup#updateTagsStatusByTagIds is fail !! tagGroupId is : " + tagGroupId);
            }
        }
        //delete tag and tag group realation
        if (0 > tagGroupRealationsMapper.updateRealationsStatusByTagGroupId(tagGroupId, BasePo.Status.DELETED.getCode())) {
            throw new CoursesException("deleteTagGroup#updateRealationsStatusByTagGroupId is fail !! tagGroupId is : " + tagGroupId);
        }
        //delete tag and course realation
        if (0 > courseTagRealationsMapper.updateRealationsStatusByTagIds(tagIds, BasePo.Status.DELETED.getCode())) {
            throw new CoursesException("deleteTagGroup#updateRealationsStatusByTagIds is fail !! tagGroupId is : " + tagGroupId);
        }
    }

    @Override
    public void addTagGroup(TagGroupsDto tagGroupsDto) {

        if (isEmptyString(tagGroupsDto.getTagGroupName())) {
            throw new TagGroupsException("addTagGroup tag group name is null !! tagGroupsDto is :" + tagGroupsDto,
                    TagGroupsException.ErrorCode.TAG_GROUP_NAME_IS_NULL.getCode(),
                    TagGroupsException.ErrorCode.TAG_GROUP_NAME_IS_NULL.getMsg());
        }
        TagGroups tagGroups = this.getTagGroupByTagGroupName(tagGroupsDto.getTagGroupName(), Lists.newArrayList(BasePo.Status.FORAZEN.getCode(), BasePo.Status.NORMAL.getCode()));
        if (null != tagGroups) {
            throw new TagGroupsException("addTagGroup tag group name is not exits !! tagGroupsDto is :" + tagGroupsDto,
                    TagGroupsException.ErrorCode.TAG_GROUP_NAME_IS_EXITS.getCode(),
                    TagGroupsException.ErrorCode.TAG_GROUP_NAME_IS_EXITS.getMsg());
        }

        tagGroups = makeAddTagGroups(tagGroupsDto);
        if (1 != tagGroupsMapper.insert(tagGroups)) {
            throw new UsersException("addTagGroup#insert is fail !! tagGroupsDto is :" + tagGroupsDto);
        }
    }

    private TagGroups makeAddTagGroups(TagGroupsDto tagGroupsDto) {
        TagGroups tagGroups = new TagGroups();
        Integer now = DateUtil.unixTime().intValue();
        tagGroups.setName(tagGroupsDto.getTagGroupName());
        tagGroups.setCreateTime(now);
        tagGroups.setUpdateTime(now);
        tagGroups.setStatus(tagGroupsDto.getStatus());
        return tagGroups;
    }

    private TagGroups getTagGroupByTagGroupName(String tagGroupName, List<Byte> status) {
        TagGroupsQueryBean query = new TagGroupsQueryBean();
        query.setStatus(status);
        query.setTagGroupName(tagGroupName);
        return tagGroupsMapper.getTagGroupByTagGroupName(query);
    }
}

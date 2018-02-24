package com.course.service.impl;

import com.course.dao.mapper.TagGroupsMapper;
import com.course.dao.po.BasePo;
import com.course.dao.po.TagGroups;
import com.course.dao.po.query.TagGroupsQueryBean;
import com.course.service.TagGroupsService;
import com.course.service.dto.TagGroupsDto;
import com.course.service.dto.TagsDto;
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
    private TagGroupsMapper tagGroupsMapper;

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

    }

    @Override
    public void deleteTagGroup(Long tagGroupId) {

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

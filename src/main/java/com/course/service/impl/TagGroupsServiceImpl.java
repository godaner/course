package com.course.service.impl;

import com.course.dao.mapper.TagGroupsMapper;
import com.course.dao.po.query.TagGroupsQueryBean;
import com.course.service.TagGroupsService;
import com.course.service.dto.TagGroupsDto;
import com.course.service.dto.TagsDto;
import com.course.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;


@Service
@Scope("prototype")
public class TagGroupsServiceImpl implements TagGroupsService {

    @Autowired
    private TagGroupsMapper tagGroupsMapper;

    @Override
    public List<TagGroupsDto> getTagGroupsWithTags(PageBean pageBean, TagGroupsQueryBean queryBean) {
//        queryBean.setStatus(Lists.newArrayList(BasePo.Status.NORMAL.getCode()));
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
//        queryBean.setStatus(Lists.newArrayList(BasePo.Status.NORMAL.getCode()));
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

    }
}

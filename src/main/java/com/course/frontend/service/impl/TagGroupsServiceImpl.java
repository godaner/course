package com.course.frontend.service.impl;

import com.course.dao.mapper.TagGroupsMapper;
import com.course.dao.po.query.TagGroupsQueryBean;
import com.course.frontend.service.TagGroupsService;
import com.course.frontend.service.dto.TagGroupsDto;
import com.course.frontend.service.dto.TagsDto;
import com.course.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;


@Service
public class TagGroupsServiceImpl implements TagGroupsService {

    @Autowired
    private TagGroupsMapper tagGroupsMapper;

    @Override
    public List<TagGroupsDto> getTagGroupsWithTags(PageBean pageBean, TagGroupsQueryBean queryBean) {
        return tagGroupsMapper.getTagGroupsWithTags(pageBean, queryBean).stream().parallel().map(tagGroupsWithTags -> {
            TagGroupsDto tagGroupsDto = new TagGroupsDto();
            tagGroupsDto.setTagGroupId(tagGroupsWithTags.getId());
            tagGroupsDto.setTagGroupName(tagGroupsWithTags.getName());

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
}

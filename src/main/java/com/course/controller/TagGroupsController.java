package com.course.controller;

import com.course.controller.base.ServiceController;
import com.course.dao.po.BasePo;
import com.course.dao.po.query.TagGroupsQueryBean;
import com.course.service.TagGroupsService;
import com.course.service.dto.TagGroupsDto;
import com.course.service.dto.TagsDto;
import com.course.util.PageBean;
import org.assertj.core.util.Lists;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("/tagGroup")
@Scope("prototype")
public class TagGroupsController extends ServiceController<TagGroupsService> {
    /*************************************** 用户端 *************************************/
    @RequestMapping(value = "/list")
    public String getTagGroupsWithTags(Model model, PageBean pageBean, TagGroupsQueryBean queryBean) throws Exception {
        queryBean.setStatus(Lists.newArrayList(BasePo.Status.NORMAL.getCode()));
        List<TagGroupsDto> tagGroupsList = service.getTagGroupsWithTags(pageBean, queryBean);
        Long tagGroupsCount = service.getTagGroupsWithTagsCount(pageBean, queryBean);
        model.addAttribute("tagGroupsList", tagGroupsList);
        model.addAttribute("tagGroupsCount", tagGroupsCount);
        return "frontend/courses/index";
    }

    /*************************************** 管理端 *************************************/
    @RequestMapping(value = "/list/v2")
    @ResponseBody
    public Object getTagGroupsWithTagsV2(PageBean pageBean, TagGroupsQueryBean queryBean) throws Exception {
        queryBean.setStatus(Lists.newArrayList(BasePo.Status.NORMAL.getCode(), BasePo.Status.FORAZEN.getCode()));
        List<TagGroupsDto> list = service.getTagGroupsWithTags(pageBean, queryBean);
        Long total = service.getTagGroupsWithTagsCount(pageBean, queryBean);
        return response.putData("list", list).putData("total", total);
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    public Object updateTagGroup(TagGroupsDto tagGroupsDto) throws Exception {
        service.updateTagGroup(tagGroupsDto);
        return response.setMsg("更新标签分组成功");
    }


    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object deleteTagGroup(@RequestParam("tagGroupId") Long tagGroupId) throws Exception {
        service.deleteTagGroup(tagGroupId);
        return response;
    }

    @RequestMapping(value = "/add")
    @ResponseBody
    public Object addTagGroup(TagGroupsDto tagGroupsDto) throws Exception {
        service.addTagGroup(tagGroupsDto);
        return response.setMsg("添加标签分组 '" + tagGroupsDto.getTagGroupName() + "' 成功");
    }

}

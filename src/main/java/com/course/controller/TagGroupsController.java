package com.course.controller;

import com.course.controller.base.ServiceController;
import com.course.dao.po.query.TagGroupsQueryBean;
import com.course.service.TagGroupsService;
import com.course.service.dto.TagGroupsDto;
import com.course.util.PageBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/tagGroup")
@Scope("prototype")
public class TagGroupsController extends ServiceController<TagGroupsService>{
    /*************************************** 用户端 *************************************/
    @RequestMapping(value = "/list")
    public String getTagGroupsWithTags(Model model, PageBean pageBean, TagGroupsQueryBean queryBean) throws Exception {
        List<TagGroupsDto> tagGroupsList = service.getTagGroupsWithTags(pageBean,queryBean);
        Long tagGroupsCount = service.getTagGroupsWithTagsCount(pageBean,queryBean);
        model.addAttribute("tagGroupsList", tagGroupsList);
        model.addAttribute("tagGroupsCount", tagGroupsCount);
        return "frontend/courses/index";
    }
    /*************************************** 管理端 *************************************/
}

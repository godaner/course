package com.vm.frontend.controller;

import com.vm.controller.ServiceController;
import com.vm.dao.po.query.TagGroupsQueryBean;
import com.vm.frontend.service.TagGroupsService;
import com.vm.frontend.service.dto.TagGroupsDto;
import com.vm.util.PageBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/tagGroup")
public class TagGroupsController extends ServiceController<TagGroupsService>{
    @RequestMapping(value = "/list")
    public String getTagGroupsWithTags(Model model, PageBean pageBean, TagGroupsQueryBean queryBean) throws Exception {
        List<TagGroupsDto> tagGroupsList = service.getTagGroupsWithTags(pageBean,queryBean);
        Long tagGroupsCount = service.getTagGroupsWithTagsCount(pageBean,queryBean);
        model.addAttribute("tagGroupsList", tagGroupsList);
        model.addAttribute("tagGroupsCount", tagGroupsCount);
        return "forward:/courses/list";
    }
}

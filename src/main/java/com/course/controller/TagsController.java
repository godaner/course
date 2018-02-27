package com.course.controller;

import com.course.aop.RequiredAdminLogin;
import com.course.controller.base.ServiceController;
import com.course.service.TagService;
import com.course.service.dto.CourseTagRealationsDto;
import com.course.service.dto.TagsDto;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("/tags")
@Scope("prototype")
public class TagsController extends ServiceController<TagService> {
    /*************************************** 用户端 *************************************/

    /*************************************** 管理端 *************************************/

    @RequiredAdminLogin
    @RequestMapping(value = "/ids/course/{courseId}")
    @ResponseBody
    public Object getTagIdsOfCourse(@PathVariable Long courseId) throws Exception {
        List<Long> list = service.getTagIdsOfCourse(courseId);
        return response.putData("list", list);
    }
    @RequiredAdminLogin
    @RequestMapping(value = "/tagGroup/{tagGroupId}")
    @ResponseBody
    public Object getTagIdsOfTagGroup(@PathVariable Long tagGroupId) throws Exception {
        List<TagsDto> list = service.getTagsOfTagGroup(tagGroupId);
        return response.putData("list", list);
    }

    @RequiredAdminLogin
    @RequestMapping(value = "/course/realation")
    @ResponseBody
    public Object updateCourseTagRealations(CourseTagRealationsDto courseTagRealationsDto) throws Exception {
        service.updateCourseTagRealations(courseTagRealationsDto);
        return response;
    }
    @RequiredAdminLogin
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object addTag(TagsDto tagsDto) throws Exception {
        service.addTag(tagsDto);
        return response.setMsg("添加标签 '" + tagsDto.getTagName() + "' 成功");
    }
    @RequiredAdminLogin
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object deleteTags(@RequestParam("tagIds") String tagIds) throws Exception {
        service.deleteTag(tagIds);
        return response.setMsg("删除标签成功");
    }
}

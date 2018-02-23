package com.course.controller;

import com.course.controller.base.ServiceController;
import com.course.service.TagService;
import com.course.service.dto.CourseTagRealationsDto;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("/tags")
@Scope("prototype")
public class TagsController extends ServiceController<TagService> {
    /*************************************** 用户端 *************************************/

    /*************************************** 管理端 *************************************/
    @RequestMapping(value = "/ids/course/{courseId}")
    @ResponseBody
    public Object getCourseTagIds(@PathVariable Long courseId) throws Exception {
        List<Long> list = service.getCourseTagIds(courseId);
        return response.putData("list", list);
    }

    @RequestMapping(value = "/course/realation")
    @ResponseBody
    public Object updateCourseTagRealations(CourseTagRealationsDto courseTagRealationsDto) throws Exception {
        service.updateCourseTagRealations(courseTagRealationsDto);
        return response;
    }
}

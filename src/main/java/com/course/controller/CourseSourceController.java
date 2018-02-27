package com.course.controller;

import com.course.aop.RequiredAdminLogin;
import com.course.controller.base.ServiceController;
import com.course.service.CourseSourcesService;
import com.course.service.dto.CourseSourcesDto;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/courseSource")
@Scope("prototype")
public class CourseSourceController extends ServiceController<CourseSourcesService> {
    /*************************************** 用户端 *************************************/


    /*************************************** 管理端 *************************************/
    @RequiredAdminLogin
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object addCourseSource(CourseSourcesDto courseSourcesDto) throws Exception {
        service.addCourseSource(courseSourcesDto);
        return response.setMsg("添加视频资源成功");
    }

    @RequiredAdminLogin
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object deleteCourseSource(String courseSourcesIds) throws Exception {
        service.deleteCourseSource(courseSourcesIds);
        return response.setMsg("删除视频资源成功");
    }

    @RequiredAdminLogin
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object getCourseSource(Long courseId) throws Exception {
        return response.setMsg("获取视频资源成功").putData("list", service.getCourseSource(courseId));
    }
}

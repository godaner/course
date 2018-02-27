package com.course.controller;

import com.course.aop.RequiredAdminLogin;
import com.course.aop.RequiredUserLogin;
import com.course.controller.base.ServiceController;
import com.course.dao.po.CourseSources;
import com.course.dao.po.query.CoursesQueryBean;
import com.course.resolver.OnlineUser;
import com.course.service.CourseService;
import com.course.service.CourseSourcesService;
import com.course.service.dto.CourseSourcesDto;
import com.course.service.dto.CoursesDto;
import com.course.service.dto.UsersDto;
import com.course.util.PageBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


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
        service.addCours
        eSource(courseSourcesDto);
        return response.setMsg("添加视频资源成功");
    }

}

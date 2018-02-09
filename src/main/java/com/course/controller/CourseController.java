package com.course.controller;

import com.course.aop.RequiredUserLogin;
import com.course.controller.base.ServiceController;
import com.course.dao.po.query.CoursesQueryBean;
import com.course.resolver.OnlineUser;
import com.course.service.CourseService;
import com.course.service.dto.CoursesDto;
import com.course.service.dto.UsersDto;
import com.course.util.PageBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
@RequestMapping("/courses")
@Scope("prototype")
public class CourseController extends ServiceController<CourseService> {
    /*************************************** 用户端 *************************************/

    @RequestMapping("/index")
    public String index() {
        return "forward:/courses/list";
    }

    @RequestMapping(value = "/list")
    public String getCourseList(Model model, PageBean pageBean, CoursesQueryBean queryBean) throws Exception {

        List<CoursesDto> courseList = service.getCoursesList(pageBean, queryBean);
        Long courseCount = service.getCoursesCount(pageBean, queryBean);
        model.addAttribute("courseList", courseList);
        model.addAttribute("courseCount", courseCount);
        model.addAttribute("page", pageBean);
        model.addAttribute("query", queryBean);
        return "forward:/tagGroup/list";
    }

    @RequestMapping(value = "/{courseId}")
    public String getCourseInfo(Model model, PageBean pageBean, @PathVariable("courseId") Long courseId) throws Exception {
        CoursesDto coursesDto = service.getCourseInfo(pageBean, courseId);
        model.addAttribute("course", coursesDto);
        return "frontend/courses/show";
    }

    @RequestMapping(value = "/img/{name}")
    public void getCourseImg(@PathVariable("name") String name, HttpServletResponse httpServletResponse) throws Exception {

        service.getCourseImg(name, httpServletResponse);

    }

    @RequestMapping(value = "/src/{name}")
    public void getCourseSrc(@PathVariable("name") String name, @RequestParam("courseId") Long courseId, HttpServletResponse httpServletResponse) throws Exception {
        service.getCourseSrc(name, courseId, httpServletResponse);

    }

    @RequiredUserLogin
    @RequestMapping(value = "/download/{name}")
    public Object downloadCourseSrc(@OnlineUser UsersDto onlineUser,@PathVariable("name") String name, @RequestParam("courseId") Long courseId, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        service.downloadCourseSrc(name, courseId, onlineUser,httpServletRequest,httpServletResponse);
        return null;
    }
    /*************************************** 管理端 *************************************/

}

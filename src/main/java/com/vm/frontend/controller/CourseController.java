package com.vm.frontend.controller;

import com.vm.controller.ServiceController;
import com.vm.dao.po.query.CoursesQueryBean;
import com.vm.frontend.service.CourseService;
import com.vm.frontend.service.dto.CoursesDto;
import com.vm.util.PageBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/courses")
public class CourseController extends ServiceController<CourseService>{


    @RequestMapping(value = "/list")
    public String getCourseList(Model model, PageBean pageBean, CoursesQueryBean queryBean) throws Exception {
        List<CoursesDto> courseList = service.getCoursesList(pageBean,queryBean);
        Long courseCount = service.getCoursesCount(pageBean,queryBean);
        model.addAttribute("courseList", courseList);
        model.addAttribute("courseCount", courseCount);
        return "frontend/courses/index";
    }
}

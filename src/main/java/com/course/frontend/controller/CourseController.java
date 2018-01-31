package com.course.frontend.controller;

import com.course.controller.ServiceController;
import com.course.dao.po.query.CoursesQueryBean;
import com.course.frontend.service.CourseService;
import com.course.frontend.service.dto.CoursesDto;
import com.course.util.PageBean;
import com.course.util.ProjectConfig;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/courses")
public class CourseController extends ServiceController<CourseService> {

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

    @RequestMapping(value = "/img/{name}")
    public void getCourseImg(@PathVariable("name") String name, HttpServletResponse httpServletResponse) throws Exception {
        try {
            IOUtils.copy(new FileInputStream(ProjectConfig.COURSE_IMG_PATH + name), httpServletResponse.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            IOUtils.copy(new FileInputStream(ProjectConfig.COURSE_IMG_PATH + "default.png"), httpServletResponse.getOutputStream());
        }
    }
}

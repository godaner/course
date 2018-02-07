package com.course.controller;

import com.course.controller.base.ServiceController;
import com.course.dao.po.query.CoursesQueryBean;
import com.course.service.CourseService;
import com.course.service.dto.CoursesDto;
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
@RequestMapping("/")
@Scope("prototype")
public class IndexController {

    @RequestMapping("/backend/index")
    public String index() {
        return "backend/index";
    }

}

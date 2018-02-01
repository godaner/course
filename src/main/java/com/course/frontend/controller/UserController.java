package com.course.frontend.controller;

import com.course.controller.ServiceController;
import com.course.frontend.service.UserService;
import com.course.frontend.service.dto.UsersDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/users")
public class UserController extends ServiceController<UserService> {
    @RequestMapping(value = "/regist")
    public Object regist(UsersDto usersDto) throws Exception {
        service.regist(usersDto,getSession());
        return "frontend/courses/index";
    }

}

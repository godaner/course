package com.course.frontend.controller;

import com.course.controller.ServiceController;
import com.course.frontend.service.UserService;
import com.course.frontend.service.dto.UsersDto;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/users")
@Scope("prototype")
public class UserController extends ServiceController<UserService> {
    @RequestMapping(value = "/regist")
    @ResponseBody
    public Object regist(UsersDto usersDto) throws Exception {
        service.regist(usersDto, getSession());
        return response.setMsg("注册成功");
    }

    @RequestMapping(value = "/login")
    @ResponseBody
    public Object login(UsersDto usersDto) throws Exception {
        service.login(usersDto, getSession());
        return response.setMsg("登录成功");
    }

    @RequestMapping(value = "/logout")
    public Object logout() throws Exception {
        service.logout(getSession());
        return "frontend/course/index";
    }

    @RequestMapping(value = "/img/{name}")
    public Object getUserImg(@PathVariable("name") String name, HttpServletResponse httpServletResponse) throws Exception {
        service.getUserImg(name, httpServletResponse);
        return response.setMsg("获取成功");
    }

}

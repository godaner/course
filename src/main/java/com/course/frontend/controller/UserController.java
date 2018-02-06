package com.course.frontend.controller;

import com.course.controller.ServiceController;
import com.course.dao.po.Users;
import com.course.frontend.service.UserService;
import com.course.frontend.service.dto.UsersDto;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
    @ResponseBody
    public Object logout() throws Exception {
        service.logout(getSession());
        return response.setMsg("注销成功");
    }

    @RequestMapping(value = "/img/{name}")
    public void getUserImg(@PathVariable("name") String name, HttpServletResponse httpServletResponse) throws Exception {
        service.getUserImg(name, httpServletResponse);
    }

    @RequestMapping(value = "/{userId}")
    public Object getUser(HttpServletRequest request, @PathVariable("userId") Long userId) throws Exception {
        UsersDto usersDto = service.getUser(userId);
        request.setAttribute("users", usersDto);
        return "frontend/users/user_info";
    }

    @RequestMapping(value = "/updateOnlineUser")
    public Object updateOnlineUser(UsersDto usersDto) throws Exception {
        service.updateOnlineUser(usersDto, getRequest());
        return "frontend/users/user_info";
    }

    @RequestMapping(value = "/updateOnlinePwd")
    public Object updateOnlinePwd(UsersDto usersDto) throws Exception {
        service.updateOnlinePwd(usersDto, getRequest());
        return "frontend/users/user_info";
    }

    @RequestMapping(value = "/updateOnlineUserHead")
    public Object updateOnlineUserHead(UsersDto usersDto) throws Exception {
        service.updateOnlineUserHead(usersDto, getRequest());
        return "frontend/users/user_info";
    }

    @RequestMapping(value = "/collect/course/{courseId}")
    public Object collectCourse(@PathVariable("courseId") Long courseId) throws Exception {
        service.collectCourse(courseId, getRequest());
        return "forward:/courses/" + courseId;
    }
}

package com.course.controller;

import com.course.controller.base.ServiceController;
import com.course.service.UserService;
import com.course.service.dto.UsersDto;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping(value = "/collect/course/{courseId}")
    public Object collectCourse(HttpServletRequest request,@PathVariable("courseId") Long courseId) throws Exception {
        service.collectCourse(courseId, getRequest());
        return "forward:/courses/" + courseId;
    }
    @RequestMapping(value = "/onlineUser")
    public Object getOnlineUser(HttpServletRequest request) throws Exception {
        UsersDto usersDto = service.getOnlineUser(request);
        request.setAttribute("currtTab", request.getParameter("currtTab"));
        request.setAttribute("users", usersDto);
        return "frontend/users/user_info";
    }

    @RequestMapping(value = "/updateOnlineUser")
    public Object updateOnlineUser(HttpServletRequest request,UsersDto usersDto) throws Exception {
        service.updateOnlineUser(usersDto, getRequest());
        request.setAttribute("currtTab", request.getParameter("currtTab"));
        return "frontend/users/user_info";
    }

    @RequestMapping(value = "/updateOnlinePwd")
    public Object updateOnlinePwd(HttpServletRequest request,UsersDto usersDto) throws Exception {
        service.updateOnlinePwd(usersDto, getRequest());
        request.setAttribute("currtTab", request.getParameter("currtTab"));
        return "frontend/users/user_info";
    }

    @RequestMapping(value = "/updateOnlineUserHead")
    public Object updateOnlineUserHead(HttpServletRequest request,UsersDto usersDto) throws Exception {
        service.updateOnlineUserHead(usersDto, getRequest());
        request.setAttribute("currtTab", request.getParameter("currtTab"));
        return "frontend/users/user_info";
    }
    @RequestMapping(value = "/onlineUser/collect")
    public Object getUserCollectCourse(HttpServletRequest httpServletRequest) throws Exception {

        httpServletRequest.setAttribute("currtTab", httpServletRequest.getParameter("currtTab"));
        httpServletRequest.setAttribute("courses", service.getUserCollectCourse(httpServletRequest));
        return "frontend/users/user_info";
    }
    @RequestMapping(value = "/onlineUser/download")
    public Object getUserDownloadCourse(HttpServletRequest httpServletRequest) throws Exception {

        httpServletRequest.setAttribute("currtTab", httpServletRequest.getParameter("currtTab"));
        httpServletRequest.setAttribute("courses", service.getUserDownloadCourse(httpServletRequest));
        return "frontend/users/user_info";
    }
}

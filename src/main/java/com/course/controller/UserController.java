package com.course.controller;

import com.course.aop.RequiredUserLogin;
import com.course.controller.base.ServiceController;
import com.course.dao.po.query.UsersQueryBean;
import com.course.resolver.OnlineUser;
import com.course.service.UserService;
import com.course.service.dto.UsersDto;
import com.course.util.PageBean;
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
    /*************************************** 用户端 *************************************/
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

    @RequiredUserLogin
    @RequestMapping(value = "/collect/course/{courseId}")
    public Object collectCourse(@OnlineUser UsersDto onlineUser,HttpServletRequest request,@PathVariable("courseId") Long courseId) throws Exception {
        service.collectCourse(courseId,onlineUser, getRequest());
        return "forward:/courses/" + courseId;
    }
    @RequiredUserLogin
    @RequestMapping(value = "/onlineUser")
    public Object getOnlineUser(@OnlineUser UsersDto onlineUser,HttpServletRequest request) throws Exception {

//        UsersDto usersDto = service.getOnlineUser(request);
        request.setAttribute("users", onlineUser);
        return "frontend/users/user_info";
    }

    @RequiredUserLogin
    @RequestMapping(value = "/updateOnlineUser")
    public Object updateOnlineUser(@OnlineUser UsersDto onlineUser,HttpServletRequest request,UsersDto usersDto) throws Exception {

        service.updateOnlineUser(usersDto,onlineUser, getRequest());
        return "frontend/users/user_info";
    }

    @RequiredUserLogin
    @RequestMapping(value = "/updateOnlinePwd")
    public Object updateOnlinePwd(@OnlineUser UsersDto onlineUser,HttpServletRequest request,UsersDto usersDto) throws Exception {

        service.updateOnlinePwd(usersDto, onlineUser,getRequest());
        return "frontend/users/user_info";
    }

    @RequiredUserLogin
    @RequestMapping(value = "/updateOnlineUserHead")
    public Object updateOnlineUserHead(@OnlineUser UsersDto onlineUser,HttpServletRequest request,UsersDto usersDto) throws Exception {

        service.updateOnlineUserHead(usersDto,onlineUser, getRequest());
        return "frontend/users/user_info";
    }


    @RequiredUserLogin
    @RequestMapping(value = "/onlineUser/collect")
    public Object getUserCollectCourse(@OnlineUser UsersDto onlineUser,HttpServletRequest httpServletRequest) throws Exception {


        httpServletRequest.setAttribute("courses", service.getUserCollectCourse(onlineUser));
        return "frontend/users/user_info";
    }

    @RequiredUserLogin
    @RequestMapping(value = "/onlineUser/download")
    public Object getUserDownloadCourse(@OnlineUser UsersDto onlineUser, HttpServletRequest httpServletRequest) throws Exception {
        httpServletRequest.setAttribute("courses", service.getUserDownloadCourse(onlineUser));
        return "frontend/users/user_info";
    }



    /*************************************** 管理端 *************************************/
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object getUsers(PageBean page, UsersQueryBean query) throws Exception {
        return response.putData("users",service.getUsers(page,query)).putData("total",service.getUsersCount(page,query));
    }
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object updateUser(UsersDto usersDto) throws Exception {
        service.updateUser(usersDto);
        return response;
    }
}

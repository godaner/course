package com.course.controller;

import com.course.aop.RequiredAdminLogin;
import com.course.controller.base.ServiceController;
import com.course.dao.po.Admins;
import com.course.dao.po.query.AdminsQueryBean;
import com.course.service.AdminService;
import com.course.service.dto.AdminsDto;
import com.course.util.PageBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/admins")
@Scope("prototype")
public class AdminController extends ServiceController<AdminService> {

    @RequiredAdminLogin
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object getAdmins(PageBean page, AdminsQueryBean query) throws Exception {
        return response.putData("list", service.getAdmins(page, query)).putData("total", service.getAdminsCount(page, query));
    }

    @RequiredAdminLogin
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object addAdmin(AdminsDto adminsDto) throws Exception {
        service.addAdmin(adminsDto);
        return response.setMsg("添加管理员 '" + adminsDto.getUsername() + "' 成功");
    }

    @RequiredAdminLogin
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object updateAdmin(AdminsDto adminsDto) throws Exception {
        service.updateAdmin(adminsDto);
        return response.setMsg("更新管理员成功");
    }

    @RequiredAdminLogin
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object deleteAdmin(@RequestParam("adminId") Long adminId) throws Exception {
        service.deleteAdmin(adminId);
        return response;
    }

    @RequestMapping(value = "/login")
    @ResponseBody
    public Object login(AdminsDto adminsDto) throws Exception {
        adminsDto = service.login(adminsDto);
        setSessionAttr(Admins.KEY_OF_ONLINE_ADMIN_IN_HTTP_SESSION, adminsDto);
        return response.putData("admin", adminsDto).setMsg("管理员 '" + adminsDto.getUsername() + "' 登录成功！");
    }

    @RequestMapping(value = "/onlineAdmin")
    @ResponseBody
    public Object onlineAdmin() throws Exception {
        return response.putData("admin", getSessionAttr(Admins.KEY_OF_ONLINE_ADMIN_IN_HTTP_SESSION));
    }

    @RequiredAdminLogin
    @RequestMapping(value = "/logout")
    @ResponseBody
    public Object logout(AdminsDto adminsDto) throws Exception {
        removeSessionAttr(Admins.KEY_OF_ONLINE_ADMIN_IN_HTTP_SESSION);
        return response.setMsg("管理员注销成功！");
    }
}

package com.course.controller;

import com.course.controller.base.ServiceController;
import com.course.dao.po.query.AdminsQueryBean;
import com.course.dao.po.query.UsersQueryBean;
import com.course.service.AdminService;
import com.course.service.AdminsDto;
import com.course.service.dto.UsersDto;
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

    @RequestMapping(value = "/list")
    @ResponseBody
    public Object getAdmins(PageBean page, AdminsQueryBean query) throws Exception {
        return response.putData("list", service.getAdmins(page, query)).putData("total", service.getAdminsCount(page, query));
    }

    @RequestMapping(value = "/add")
    @ResponseBody
    public Object addAdmin(AdminsDto adminsDto) throws Exception {
        service.addAdmin(adminsDto);
        return response.setMsg("添加管理员 '" + adminsDto.getUsername() + "' 成功");
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    public Object updateAdmin(AdminsDto adminsDto) throws Exception {
        service.updateAdmin(adminsDto);
        return response.setMsg("更新管理员成功");
    }
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object deleteAdmin(@RequestParam("adminId") Long adminId) throws Exception {
        service.deleteAdmin(adminId);
        return response;
    }
}

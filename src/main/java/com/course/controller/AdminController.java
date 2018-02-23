package com.course.controller;

import com.course.controller.base.ServiceController;
import com.course.dao.po.query.AdminsQueryBean;
import com.course.dao.po.query.UsersQueryBean;
import com.course.service.AdminService;
import com.course.service.AdminsDto;
import com.course.util.PageBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/admins")
@Scope("prototype")
public class AdminController extends ServiceController<AdminService> {

    @RequestMapping(value = "/list")
    @ResponseBody
    public Object getAdmins(PageBean page, AdminsQueryBean query) throws Exception {
        return response.putData("users", service.getAdmins(page, query)).putData("total", service.getAdminsCount(page, query));
    }
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object addAdmin(AdminsDto adminsDto) throws Exception {
        service.addAdmin(adminsDto);
        return response.setMsg("添加管理员 '" + adminsDto.getUsername() + "' 成功");
    }
}

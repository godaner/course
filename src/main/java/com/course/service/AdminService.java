package com.course.service;

import com.course.dao.po.query.AdminsQueryBean;
import com.course.util.PageBean;

import java.util.List;

public interface AdminService {
    void addAdmin(AdminsDto adminsDto);

    List<AdminsDto> getAdmins(PageBean page, AdminsQueryBean query);

    Long getAdminsCount(PageBean page, AdminsQueryBean query);

    void updateAdmin(AdminsDto adminsDto);

    void deleteAdmin(Long adminId);
}

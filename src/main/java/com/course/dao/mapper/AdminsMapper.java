package com.course.dao.mapper;

import com.course.dao.po.Admins;
import com.course.dao.po.Users;
import com.course.dao.po.query.AdminsQueryBean;
import com.course.service.AdminsDto;
import com.course.util.PageBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminsMapper extends BaseCrudMapper<Admins> {


    List<Admins> getAdmins(@Param("page") PageBean page, @Param("query") AdminsQueryBean query);

    Long getAdminsCount(@Param("page") PageBean page, @Param("query") AdminsQueryBean query);

    Admins getAdminByUserId(@Param("query") AdminsQueryBean query);

    Admins getAdminByUsername(@Param("query") AdminsQueryBean query);
}

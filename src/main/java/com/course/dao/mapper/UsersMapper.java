package com.course.dao.mapper;

import com.course.dao.po.Users;
import com.course.dao.po.query.CoursesQueryBean;
import com.course.dao.po.query.UsersQueryBean;
import com.course.service.dto.UsersDto;
import com.course.util.PageBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UsersMapper extends BaseCrudMapper<Users> {


    List<Users> getUsers(@Param("page") PageBean page, @Param("que" +
            "ry") UsersQueryBean query);

    Long getUsersCount(@Param("page") PageBean page, @Param("que" +
            "ry") UsersQueryBean query);

    Users getUserByUserId(@Param("query") UsersQueryBean query);

    Users getUserByUsername(@Param("query") UsersQueryBean query);

}

package com.course.service;


import com.course.dao.po.query.UsersQueryBean;
import com.course.service.dto.CoursesDto;
import com.course.service.dto.UsersDto;
import com.course.util.PageBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


public interface UserService {

    void regist(UsersDto usersDto, HttpSession session) throws Exception;

    void login(UsersDto usersDto, HttpSession session) throws Exception;

    void logout(HttpSession session) throws Exception;

    void getUserImg(String name, HttpServletResponse httpServletResponse) throws Exception;

    UsersDto getOnlineUser(HttpServletRequest request);

    void updateOnlineUser(UsersDto usersDto, HttpServletRequest request);

    void updateOnlinePwd(UsersDto usersDto, HttpServletRequest request);

    void updateOnlineUserHead(UsersDto usersDto, HttpServletRequest request);

    void collectCourse(Long courseId, HttpServletRequest request);

    List<CoursesDto> getUserCollectCourse(HttpServletRequest request);

    List<CoursesDto> getUserDownloadCourse(HttpServletRequest httpServletRequest);

    List<UsersDto> getUsers(PageBean page, UsersQueryBean query);

    Long getUsersCount(PageBean page, UsersQueryBean query);

    void updateUser(UsersDto usersDto);
}

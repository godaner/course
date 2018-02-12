package com.course.service;


import com.course.dao.po.Users;
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

//    UsersDto getOnlineUser(HttpServletRequest request);

    void updateOnlineUser(UsersDto usersDto, UsersDto onlineUser, HttpServletRequest request);

    void updateOnlinePwd(UsersDto usersDto, UsersDto onlineUser, HttpServletRequest request);

    void updateOnlineUserHead(UsersDto usersDto, UsersDto onlineUser, HttpServletRequest request);

    void collectCourse(Long courseId, UsersDto onlineUser, HttpServletRequest request);

    List<CoursesDto> getUserCollectCourse(UsersDto onlineUser);

    List<CoursesDto> getUserDownloadCourse(UsersDto onlineUser);

    /*************************************** 管理端 *************************************/

    List<UsersDto> getUsers(PageBean page, UsersQueryBean query);

    Long getUsersCount(PageBean page, UsersQueryBean query);

    void updateUser(UsersDto usersDto);

    Users getUserById(Long userId);

    UsersDto makeUsersDto(Users user);

    void deleteUser(Long userId);

    void updateUserHeadImg(UsersDto usersDto);
}

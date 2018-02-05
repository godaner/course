package com.course.frontend.service;

import com.course.frontend.service.dto.UsersDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public interface UserService {

    void regist(UsersDto usersDto, HttpSession session) throws Exception;

    void login(UsersDto usersDto, HttpSession session) throws Exception;

    void logout(HttpSession session) throws Exception;

    void getUserImg(String name, HttpServletResponse httpServletResponse) throws Exception;

    UsersDto getUser(Long userId);

    void updateOnlineUser(UsersDto usersDto, HttpServletRequest request);

    void updateOnlinePwd(UsersDto usersDto, HttpServletRequest request);
}

package com.course.frontend.service;

import com.course.frontend.service.dto.UsersDto;

import javax.servlet.http.HttpSession;

public interface UserService {

    void regist(UsersDto usersDto, HttpSession session) throws Exception;

    void login(UsersDto usersDto, HttpSession session) throws Exception;
}

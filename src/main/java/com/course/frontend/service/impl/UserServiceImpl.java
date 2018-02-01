package com.course.frontend.service.impl;

import com.course.frontend.service.UserService;
import com.course.frontend.service.dto.UsersDto;
import com.course.util.BaseService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;


@Service
public class UserServiceImpl extends BaseService implements UserService {

    @Override
    public void regist(UsersDto usersDto, HttpSession session) {

    }
}

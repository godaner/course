package com.course.frontend.service.impl;

import com.course.dao.mapper.UsersMapper;
import com.course.dao.po.BasePo;
import com.course.dao.po.Users;
import com.course.frontend.exception.UsersException;
import com.course.frontend.service.UserService;
import com.course.frontend.service.dto.UsersDto;
import com.course.util.BaseService;
import com.course.util.DateUtil;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;


@Service
public class UserServiceImpl extends BaseService implements UserService {
    @Autowired
    private UsersMapper usersMapper;

    private Users getUserByUsername(String username) {
        return usersMapper.selectOneBy(ImmutableMap.of(
                "username", username,
                "status", BasePo.Status.NORMAL.getCode()
        ));
    }

    private boolean exitsUsername(String username) {
        return this.getUserByUsername(username) != null;
    }

    @Override
    public void regist(UsersDto usersDto, HttpSession session) throws UsersException {
        Users user = makeUsers(usersDto);

        if (this.exitsUsername(usersDto.getUsername())) {
            logger.error("regist username is exits !! userDto is : " + usersDto);
            throw new UsersException(UsersException.ErrorCode.USERNAME_IS_EXITS.getCode(),
                    UsersException.ErrorCode.USERNAME_IS_EXITS.getMsg());
        }
        if (usersMapper.insert(user) != 1) {
            throw new UsersException("regist insert is fail !! userDto is : " + usersDto);
        }
        session.setAttribute(Users.KEY_OF_ONLINE_USER_IN_HTTP_SESSION, user);
    }

    private Users makeUsers(UsersDto usersDto) {
        Integer now = DateUtil.unixTime().intValue();
        Users users = new Users();
        users.setBirthday(now);
        users.setUpdateTime(now);
        users.setCreateTime(now);
        users.setImgUrl("/users/img/" + uuid());
        users.setUsername(usersDto.getUsername());
        users.setPassword(usersDto.getPassword());
        users.setSex(Users.Sex.U.getCode());
        users.setDescription("");
        users.setStatus(BasePo.Status.NORMAL.getCode());
        return users;
    }
}

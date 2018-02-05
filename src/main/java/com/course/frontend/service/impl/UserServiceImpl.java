package com.course.frontend.service.impl;

import com.course.dao.mapper.UsersMapper;
import com.course.dao.po.BasePo;
import com.course.dao.po.Users;
import com.course.frontend.exception.UsersException;
import com.course.frontend.service.UserService;
import com.course.frontend.service.dto.UsersDto;
import com.course.util.BaseService;
import com.course.util.DateUtil;
import com.course.util.ProjectConfig;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


@Service
@Scope("prototype")
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
    public void regist(UsersDto usersDto, HttpSession session) throws Exception {
        Users user = makeUsers(usersDto);

        if (this.exitsUsername(usersDto.getUsername())) {
            logger.error("regist username is exits !! userDto is : " + usersDto);
            throw new UsersException(UsersException.ErrorCode.USERNAME_IS_EXITS.getCode(),
                    UsersException.ErrorCode.USERNAME_IS_EXITS.getMsg());
        }
        if (usersMapper.insert(user) != 1) {
            throw new UsersException("regist insert is fail !! userDto is : " + usersDto);
        }
        session.setAttribute(Users.KEY_OF_ONLINE_USER_IN_HTTP_SESSION, makeUsersDto(user));
    }

    private UsersDto makeUsersDto(Users user) {
        UsersDto usersDto = new UsersDto();
        usersDto.setUserId(user.getId());
        usersDto.setPassword("");
        usersDto.setBirthday(user.getBirthday());
        usersDto.setImgUrl(user.getImgUrl());
        usersDto.setSex(user.getSex());
        usersDto.setUsername(user.getUsername());
        usersDto.setDescription(user.getDescription());
        return usersDto;
    }

    @Override
    public void login(UsersDto usersDto, HttpSession session) throws Exception {
        Users users = this.getUserByUsername(usersDto.getUsername());
        if (users == null) {
            logger.error("login username is not exits !! userDto is : " + usersDto);
            throw new UsersException(UsersException.ErrorCode.USERNAME_IS_NOT_EXITS.getCode(),
                    UsersException.ErrorCode.USERNAME_IS_NOT_EXITS.getMsg());
        }
        if (!users.getPassword().equals(usersDto.getPassword())) {
            logger.error("login password is error !! userDto is : " + usersDto);
            throw new UsersException(UsersException.ErrorCode.PASSWORD_IS_ERROR.getCode(),
                    UsersException.ErrorCode.PASSWORD_IS_ERROR.getMsg());
        }
        session.setAttribute(Users.KEY_OF_ONLINE_USER_IN_HTTP_SESSION, makeUsersDto(users));

    }

    @Override
    public void logout(HttpSession session) throws Exception {
        session.removeAttribute(Users.KEY_OF_ONLINE_USER_IN_HTTP_SESSION);
        session.invalidate();
    }

    @Override
    public void getUserImg(String name, HttpServletResponse httpServletResponse) throws Exception {
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        try {
            IOUtils.copy(new FileInputStream(ProjectConfig.USER_IMG_PATH + name + ".png"), outputStream);
        } catch (IOException e) {
            e.printStackTrace();
            IOUtils.copy(new FileInputStream(ProjectConfig.USER_IMG_PATH + "default.png"), outputStream);
        }
    }

    @Override
    public UsersDto getUser(Long userId) {
        if (userId == null) {
            throw new UsersException("getUser userId is null !! userId is : " + userId);
        }
        Users users = this.getUserByUserId(userId);
        if (isNullObject(users)) {
            throw new UsersException("getUser users is null !! userId is : " + userId);
        }
        return makeUsersDto(users);
    }

    @Override
    public void updateOnlineUser(UsersDto usersDto, HttpServletRequest request) {
        UsersDto sessionUser = (UsersDto) request.getSession().getAttribute(Users.KEY_OF_ONLINE_USER_IN_HTTP_SESSION);
        if (null == sessionUser) {
            request.setAttribute("msg", "您已离线");
            request.setAttribute("forward", "forward:/courses/list");
            throw new UsersException("updateOnlineUser sessionUser is null !! usersDto is :" + usersDto);
        }
        usersDto.setUserId(sessionUser.getUserId());
        Users user = makeUpdateOnlineUser(usersDto);

        usersMapper.update(user);
        // update session user
        request.getSession().setAttribute(Users.KEY_OF_ONLINE_USER_IN_HTTP_SESSION, makeUsersDto(this.getUserByUserId(user.getId())));

        request.setAttribute("msg", "修改基本信息成功");

    }

    @Override
    @Transactional
    public void updateOnlinePwd(UsersDto usersDto, HttpServletRequest request) {
        UsersDto sessionUser = (UsersDto) request.getSession().getAttribute(Users.KEY_OF_ONLINE_USER_IN_HTTP_SESSION);
        if (null == sessionUser) {
            request.setAttribute("msg", "您已离线");
            request.setAttribute("forward", "forward:/courses/list");
            throw new UsersException("updateOnlinePwd sessionUser is null !! usersDto is :" + usersDto);
        }
        usersDto.setUserId(sessionUser.getUserId());

        if (isEmptyString(usersDto.getPassword()) || isEmptyString(usersDto.getRePassword())) {
            request.setAttribute("msg", "密码不能为空");
            request.setAttribute("forward", "forward:/users/" + sessionUser.getUserId());
            throw new UsersException("updateOnlinePwd password or repassword is null !! usersDto is :" + usersDto);
        }

        if (!usersDto.getPassword().equals(usersDto.getRePassword())) {
            request.setAttribute("msg", "两次密码不一致");
            request.setAttribute("forward", "forward:/users/" + sessionUser.getUserId());
            throw new UsersException("updateOnlinePwd password and repassword is not same !! usersDto is :" + usersDto);
        }

        Users users = this.getUserByUserId(sessionUser.getUserId());

        if (users == null) {
            request.setAttribute("msg", "账户目前不可用");
            request.setAttribute("forward", "forward:/courses/list");
            request.getSession().removeAttribute(Users.KEY_OF_ONLINE_USER_IN_HTTP_SESSION);
            throw new UsersException("updateOnlinePwd users can not use now !! usersDto is :" + usersDto);
        }

        users.setPassword(usersDto.getPassword());

        if (usersMapper.update(users) != 1) {
            request.setAttribute("msg", "修改密码失败");
            request.setAttribute("forward", "forward:/users/" + sessionUser.getUserId());
            throw new UsersException("updateOnlinePwd update users is fail !! usersDto is :" + usersDto);
        }

        request.setAttribute("msg", "修改密码成功");
    }

    @Override
    @Transactional
    public void updateOnlineUserHead(UsersDto usersDto, HttpServletRequest request) {
        UsersDto sessionUser = (UsersDto) request.getSession().getAttribute(Users.KEY_OF_ONLINE_USER_IN_HTTP_SESSION);
        if (null == sessionUser) {
            request.setAttribute("msg", "您已离线");
            request.setAttribute("forward", "forward:/courses/list");
            throw new UsersException("updateOnlineUserHead sessionUser is null !! usersDto is :" + usersDto);
        }

        usersDto.setUserId(sessionUser.getUserId());


        if (usersDto.getHeadFile().getOriginalFilename().equals("") || usersDto.getHeadFile().getOriginalFilename() == null) {
            request.setAttribute("msg", "文件为空");
            request.setAttribute("forward", "forward:/users/" + sessionUser.getUserId());
            throw new UsersException("updateOnlineUserHead user head file is null !! usersDto is :" + usersDto);
        }

        String uuid = uuid();
        try {
            OutputStream outputStream = new FileOutputStream(ProjectConfig.USER_IMG_PATH + uuid + ".png");
            IOUtils.copy(usersDto.getHeadFile().getInputStream(), outputStream);
        } catch (IOException e) {
            e.printStackTrace();
            request.setAttribute("msg", "上传文件错误");
            request.setAttribute("forward", "forward:/users/" + sessionUser.getUserId());
            throw new UsersException("updateOnlineUserHead upload headImg is fail !! usersDto is :" + usersDto);
        }

        Users users = this.getUserByUserId(usersDto.getUserId());

        users.setImgUrl("/users/img/" + uuid);


        usersMapper.update(users);

        request.getSession().setAttribute(Users.KEY_OF_ONLINE_USER_IN_HTTP_SESSION, makeUsersDto(this.getUserByUserId(sessionUser.getUserId())));

        request.setAttribute("msg", "更新头像成功");
    }

    private Users makeUpdateOnlineUser(UsersDto usersDto) {
        Users user = new Users();
        user.setDescription(usersDto.getDescription());
        user.setSex(usersDto.getSex());
        user.setUpdateTime(DateUtil.unixTime().intValue());
        user.setId(usersDto.getUserId());
        return user;
    }

    private Users getUserByUserId(Long userId) {
        Users users = usersMapper.select(userId);
        if (users == null || !users.getStatus().equals(BasePo.Status.NORMAL.getCode())) {
            users = null;
        }
        return users;
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

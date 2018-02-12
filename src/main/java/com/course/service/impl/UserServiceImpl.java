package com.course.service.impl;

import com.course.dao.mapper.CoursesMapper;
import com.course.dao.mapper.UserCollectCourseMapper;
import com.course.dao.mapper.UserDownloadCourseMapper;
import com.course.dao.mapper.UsersMapper;
import com.course.dao.po.BasePo;
import com.course.dao.po.Courses;
import com.course.dao.po.UserCollectCourse;
import com.course.dao.po.Users;
import com.course.dao.po.query.CoursesQueryBean;
import com.course.dao.po.query.UserCollectCourseQueryBean;
import com.course.dao.po.query.UserDownloadCourseQueryBean;
import com.course.dao.po.query.UsersQueryBean;
import com.course.service.UserService;
import com.course.service.dto.CoursesDto;
import com.course.service.dto.UsersDto;
import com.course.service.exception.UsersException;
import com.course.util.BaseService;
import com.course.util.DateUtil;
import com.course.util.PageBean;
import com.course.util.ProjectConfig;
import org.apache.commons.io.IOUtils;
import org.assertj.core.util.Lists;
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
import java.util.List;

import static java.util.stream.Collectors.toList;


@Service
@Scope("prototype")
public class UserServiceImpl extends BaseService implements UserService {
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private CoursesMapper coursesMapper;
    @Autowired
    private UserCollectCourseMapper userCollectCourseMapper;
    @Autowired
    private UserDownloadCourseMapper userDownloadCourseMapper;


    private boolean exitsUsername(String username, List<Byte> status) {
        return this.getUserByUsername(username, status) != null;
    }

    @Override
    public void regist(UsersDto usersDto, HttpSession session) throws Exception {
        Users user = makeUsers(usersDto);

        if (this.exitsUsername(usersDto.getUsername(), Lists.newArrayList(BasePo.Status.NORMAL.getCode()))) {
            logger.error("regist username is exits !! userDto is : " + usersDto);
            throw new UsersException(UsersException.ErrorCode.USERNAME_IS_EXITS.getCode(),
                    UsersException.ErrorCode.USERNAME_IS_EXITS.getMsg());
        }
        if (usersMapper.insert(user) != 1) {
            throw new UsersException("regist insert is fail !! userDto is : " + usersDto);
        }
        session.setAttribute(Users.KEY_OF_ONLINE_USER_IN_HTTP_SESSION, makeUsersDto(user));
    }

    public UsersDto makeUsersDto(Users user) {
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
    public void deleteUser(Long userId) {
        Users users = this.getUserByUserId(userId, Lists.newArrayList(BasePo.Status.NORMAL.getCode(), BasePo.Status.FORAZEN.getCode()));
        if (null != users) {
            users.setStatus(BasePo.Status.DELETED.getCode());
            usersMapper.update(users);
        }
    }

    @Override
    public void login(UsersDto usersDto, HttpSession session) throws Exception {
        Users users = this.getUserByUsername(usersDto.getUsername(), Lists.newArrayList(BasePo.Status.NORMAL.getCode()));
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

//    @Override
//    public UsersDto getOnlineUser(HttpServletRequest request) {
//        UsersDto sessionUser = (UsersDto) request.getSession().getAttribute(Users.KEY_OF_ONLINE_USER_IN_HTTP_SESSION);
//
//        Long userId = sessionUser.getUserId();
//
//        Users users = this.getUserByUserId(userId, Lists.newArrayList(BasePo.Status.NORMAL.getCode()));
//        if (isNullObject(users)) {
//            throw new UsersException("getOnlineUser users is null !! sessionUser is : " + sessionUser);
//        }
//        return makeUsersDto(users);
//    }

    @Override
    public void updateOnlineUser(UsersDto usersDto, UsersDto onlineUser, HttpServletRequest request) {


        usersDto.setUserId(onlineUser.getUserId());
        Users user = makeUpdateOnlineUser(usersDto);

        usersMapper.update(user);
        // update session user
        request.getSession().setAttribute(Users.KEY_OF_ONLINE_USER_IN_HTTP_SESSION, makeUsersDto(this.getUserByUserId(user.getId(), Lists.newArrayList(BasePo.Status.NORMAL.getCode()))));

        request.setAttribute("msg", "修改基本信息成功");

    }

    @Override
    @Transactional
    public void updateOnlinePwd(UsersDto usersDto, UsersDto onlineUser, HttpServletRequest request) {


        usersDto.setUserId(onlineUser.getUserId());

        if (isEmptyString(usersDto.getPassword()) || isEmptyString(usersDto.getRePassword())) {
            request.setAttribute("msg", "密码不能为空");
            request.setAttribute("forward", "forward:/users/" + onlineUser.getUserId());
            throw new UsersException("updateOnlinePwd password or repassword is null !! usersDto is :" + usersDto);
        }

        if (!usersDto.getPassword().equals(usersDto.getRePassword())) {
            request.setAttribute("msg", "两次密码不一致");
            request.setAttribute("forward", "forward:/users/" + onlineUser.getUserId());
            throw new UsersException("updateOnlinePwd password and repassword is not same !! usersDto is :" + usersDto);
        }

        Users users = this.getUserByUserId(onlineUser.getUserId(), Lists.newArrayList(BasePo.Status.NORMAL.getCode()));

        if (users == null) {
            request.setAttribute("msg", "账户目前不可用");
            request.setAttribute("forward", "forward:/courses/list");
            request.getSession().removeAttribute(Users.KEY_OF_ONLINE_USER_IN_HTTP_SESSION);
            throw new UsersException("updateOnlinePwd users can not use now !! usersDto is :" + usersDto);
        }

        users.setPassword(usersDto.getPassword());

        if (usersMapper.update(users) != 1) {
            request.setAttribute("msg", "修改密码失败");
            request.setAttribute("forward", "forward:/users/" + onlineUser.getUserId());
            throw new UsersException("updateOnlinePwd update users is fail !! usersDto is :" + usersDto);
        }

        request.setAttribute("msg", "修改密码成功");
    }

    @Override
    @Transactional
    public void updateOnlineUserHead(UsersDto usersDto, UsersDto onlineUser, HttpServletRequest request) {
        UsersDto sessionUser = (UsersDto) request.getSession().getAttribute(Users.KEY_OF_ONLINE_USER_IN_HTTP_SESSION);


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

        Users users = this.getUserByUserId(usersDto.getUserId(), Lists.newArrayList(BasePo.Status.NORMAL.getCode()));

        users.setImgUrl("/users/img/" + uuid);


        usersMapper.update(users);

        request.getSession().setAttribute(Users.KEY_OF_ONLINE_USER_IN_HTTP_SESSION, makeUsersDto(this.getUserByUserId(sessionUser.getUserId(), Lists.newArrayList(BasePo.Status.NORMAL.getCode()))));

        request.setAttribute("msg", "更新头像成功");
    }

    @Override
    @Transactional
    public void collectCourse(Long courseId, UsersDto onlineUser, HttpServletRequest request) {

        Courses courses = this.getCourseByCourseId(courseId);

        if (courses == null) {
            request.setAttribute("msg", "课程不存在");
            request.setAttribute("forward", "forward:/courses/list");
            throw new UsersException("collectCourse courses is not exits !! courseId is : " + courseId);
        }

        UsersDto sessionUser = (UsersDto) request.getSession().getAttribute(Users.KEY_OF_ONLINE_USER_IN_HTTP_SESSION);

        Users users = this.getUserByUserId(sessionUser.getUserId(), Lists.newArrayList(BasePo.Status.NORMAL.getCode()));

        UserCollectCourse userCollectCourse = this.getUserCollectCourseByUserIdAndCourseId(users.getId(), courseId);

        if (userCollectCourse != null) {
            request.setAttribute("msg", "本课程您已收藏过了");
            return;
        }
        userCollectCourse = makeUserCollectCourse(users, courses);


        if (userCollectCourseMapper.insert(userCollectCourse) != 1) {
            request.setAttribute("msg", "课程不存在");
            request.setAttribute("forward", "forward:/courses/" + courses.getId());
            throw new UsersException("collectCourse insert userCollectCourse is fail !! userCollectCourse is : " + userCollectCourse);
        }

        request.setAttribute("msg", "收藏成功");
    }

    @Override
    public List<CoursesDto> getUserCollectCourse(UsersDto onlineUser) {

        Long userId = onlineUser.getUserId();

        UserCollectCourseQueryBean userCollectCourseQueryBean = new UserCollectCourseQueryBean();
        userCollectCourseQueryBean.setStatus(Lists.newArrayList(BasePo.Status.NORMAL.getCode()));
        userCollectCourseQueryBean.setUserId(userId);

        List<Long> courseIds = userCollectCourseMapper.getCourseIdsByUserId(userCollectCourseQueryBean);

        if (isEmptyList(courseIds)) {
            return Lists.newArrayList();
        }

        CoursesQueryBean coursesQueryBean = new CoursesQueryBean();
        coursesQueryBean.setCourseIds(courseIds);

        coursesQueryBean.setStatus(Lists.newArrayList(BasePo.Status.NORMAL.getCode()));
        return coursesMapper.getCoursesByIds(coursesQueryBean).stream().parallel().map(
                courses -> {
                    return makeCourseDto(courses);
                }
        ).collect(toList());
    }

    @Override
    public List<CoursesDto> getUserDownloadCourse(UsersDto onlineUser) {

        Long userId = onlineUser.getUserId();

        UserDownloadCourseQueryBean userCollectCourseQueryBean = new UserDownloadCourseQueryBean();
        userCollectCourseQueryBean.setStatus(Lists.newArrayList(BasePo.Status.NORMAL.getCode()));
        userCollectCourseQueryBean.setUserId(userId);

        List<Long> courseIds = userDownloadCourseMapper.getCourseIdsByUserId(userCollectCourseQueryBean);

        if (isEmptyList(courseIds)) {
            return Lists.newArrayList();
        }

        CoursesQueryBean coursesQueryBean = new CoursesQueryBean();
        coursesQueryBean.setCourseIds(courseIds);

        coursesQueryBean.setStatus(Lists.newArrayList(BasePo.Status.NORMAL.getCode()));
        return coursesMapper.getCoursesByIds(coursesQueryBean).stream().parallel().map(
                courses -> {
                    return makeCourseDto(courses);
                }
        ).collect(toList());
    }

    @Override
    public List<UsersDto> getUsers(PageBean page, UsersQueryBean query) {
        query.setStatus(Lists.newArrayList(BasePo.Status.FORAZEN.getCode(), BasePo.Status.NORMAL.getCode()));
        return usersMapper.getUsers(page, query).stream().parallel().map(users -> {
            return makeBackendUsersDto(users);
        }).collect(toList());
    }

    @Override
    public Long getUsersCount(PageBean page, UsersQueryBean query) {

        query.setStatus(Lists.newArrayList(BasePo.Status.FORAZEN.getCode(), BasePo.Status.NORMAL.getCode()));
        return usersMapper.getUsersCount(page, query);
    }

    @Override
    public void updateUser(UsersDto usersDto) {
        if (null == this.getUserByUserId(usersDto.getUserId(), Lists.newArrayList(BasePo.Status.FORAZEN.getCode(), BasePo.Status.NORMAL.getCode()))) {
            throw new UsersException("updateUser user is not exits !! usersDto is :" + usersDto,
                    UsersException.ErrorCode.USER_IS_NOT_EXITS.getCode(),
                    UsersException.ErrorCode.USER_IS_NOT_EXITS.getMsg());
        }

        if (usersMapper.update(makeUpdateUser(usersDto)) != 1) {
            throw new UsersException("updateUser is fail !! usersDto is :" + usersDto,
                    UsersException.ErrorCode.UPDATE_USER_FAIL.getCode(),
                    UsersException.ErrorCode.UPDATE_USER_FAIL.getMsg());
        }
    }

    @Override
    public Users getUserById(Long userId) {
        return this.getUserByUserId(userId, Lists.newArrayList(BasePo.Status.NORMAL.getCode()));
    }

    private Users makeUpdateUser(UsersDto usersDto) {
        Users users = new Users();
        Integer now = DateUtil.unixTime().intValue();
        users.setUpdateTime(now);
        users.setDescription(usersDto.getDescription());
        users.setSex(usersDto.getSex());
        users.setStatus(usersDto.getStatus());
        users.setId(usersDto.getUserId());
        users.setUpdateTime(DateUtil.unixTime().intValue());
        return users;
    }

    private UsersDto makeBackendUsersDto(Users users) {
        UsersDto usersDto = new UsersDto();
        usersDto.setUserId(users.getId());
        usersDto.setPassword(users.getPassword());
        usersDto.setBirthday(users.getBirthday());
        usersDto.setImgUrl(users.getImgUrl());
        usersDto.setSex(users.getSex());
        usersDto.setUsername(users.getUsername());
        usersDto.setDescription(users.getDescription());
        usersDto.setCreateTime(users.getCreateTime());
        usersDto.setStatus(users.getStatus());
        usersDto.setUpdateTime(users.getUpdateTime());
        return usersDto;
    }

    private CoursesDto makeCourseDto(Courses courses) {

        CoursesDto coursesDto = new CoursesDto();
        coursesDto.setCollectNumber(courses.getCollectNumber());
        coursesDto.setWatchNumber(courses.getWatchNumber());
        coursesDto.setCourseId(courses.getId());
        coursesDto.setDescription(courses.getDescription());
        coursesDto.setImgUrl(courses.getImgUrl());
        coursesDto.setName(courses.getName());
        coursesDto.setDownloadNumber(courses.getDownloadNumber());
        return coursesDto;
    }

    private UserCollectCourse getUserCollectCourseByUserIdAndCourseId(Long userId, Long courseId) {
        UserCollectCourse userCollectCourse = new UserCollectCourse();
        userCollectCourse.setUserId(userId);
        userCollectCourse.setCourseId(courseId);
        userCollectCourse = userCollectCourseMapper.selectOneBy(userCollectCourse);
        return (userCollectCourse == null || !userCollectCourse.getStatus().equals(BasePo.Status.NORMAL.getCode())) ? null : userCollectCourse;
    }

    private UserCollectCourse makeUserCollectCourse(Users users, Courses courses) {
        UserCollectCourse userCollectCourse = new UserCollectCourse();
        Integer now = DateUtil.unixTime().intValue();
        userCollectCourse.setUpdateTime(now);
        userCollectCourse.setCreateTime(now);
        userCollectCourse.setStatus(BasePo.Status.NORMAL.getCode());
        userCollectCourse.setCourseId(courses.getId());
        userCollectCourse.setUserId(users.getId());
        return userCollectCourse;

    }

    private Courses getCourseByCourseId(Long courseId) {

        Courses courses = coursesMapper.select(courseId);
        return (courses == null || !courses.getStatus().equals(BasePo.Status.NORMAL.getCode())) ? null : courses;

    }

    private Users makeUpdateOnlineUser(UsersDto usersDto) {
        Users user = new Users();
        user.setDescription(usersDto.getDescription());
        user.setSex(usersDto.getSex());
        user.setUpdateTime(DateUtil.unixTime().intValue());
        user.setId(usersDto.getUserId());
        return user;
    }

    private Users getUserByUserId(Long userId, List<Byte> status) {
        UsersQueryBean query = new UsersQueryBean();
        query.setStatus(status);
        query.setUserId(userId);
        return usersMapper.getUserByUserId(query);
    }

    private Users getUserByUsername(String username, List<Byte> status) {
        UsersQueryBean query = new UsersQueryBean();
        query.setStatus(status);
        query.setUsername(username);
        return usersMapper.getUserByUsername(query);
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

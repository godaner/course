package com.course.resolver;

import com.course.dao.po.Users;
import com.course.service.UserService;
import com.course.service.dto.UsersDto;
import com.course.service.exception.UsersException;
import com.course.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 增加方法注入，将含有 {@link @OnlineUser} 注解的方法参数注入当前登录用户的实例
 */
@Component
@Order(50)
public class OnlineUserMethodArgumentResolver extends CommonUtil implements HandlerMethodArgumentResolver {

    @Autowired
    private UserService userService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(UsersDto.class)
                && parameter.hasParameterAnnotation(OnlineUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {

        //get session user
        UsersDto sessionUser = (UsersDto) webRequest.getAttribute(Users.KEY_OF_ONLINE_USER_IN_HTTP_SESSION, NativeWebRequest.SCOPE_SESSION);
        if (null == sessionUser || sessionUser.getUserId() == null) {
            return null;
        }
        //get user from db
        Users users = userService.getUserById(sessionUser.getUserId());
        if (null == users) {
            webRequest.setAttribute("msg", "账户已失效，您已离线", NativeWebRequest.SCOPE_REQUEST);
            webRequest.setAttribute("forward", "forward:/courses/list", NativeWebRequest.SCOPE_REQUEST);
            webRequest.removeAttribute(Users.KEY_OF_ONLINE_USER_IN_HTTP_SESSION, NativeWebRequest.SCOPE_SESSION);
            throw new UsersException("OnlineUserMethodArgumentResolver users is null !! users is :" + users);
        }

        //makeUsersDto
        sessionUser = userService.makeUsersDto(users);

        //reset sessionUser
        webRequest.setAttribute(Users.KEY_OF_ONLINE_USER_IN_HTTP_SESSION, sessionUser, NativeWebRequest.SCOPE_SESSION);

        return sessionUser;
    }


}




package com.course.aop;

import com.course.dao.po.Users;
import com.course.service.UserService;
import com.course.service.dto.UsersDto;
import com.course.service.exception.UsersException;
import com.course.service.exception.base.CourseRuntimeException;
import com.course.util.CommonUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


@Component
@Aspect
@Order(2)
public class HttpOnlineUserAop extends CommonUtil {
    @Autowired
    private UserService userService;

    @Pointcut("execution(* com.course.controller..*.*(..))&&@annotation(com.course.aop.RequiredUserLogin)&&!@annotation(org.springframework.web.bind.annotation.ResponseBody)")
    public void declareJoinPointExpression() {
    }


    @Around("declareJoinPointExpression()")
    public Object doAroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = getHttpServletRequest();
        request.setAttribute("currtTab", request.getParameter("currtTab"));
        UsersDto sessionUser = (UsersDto) request.getSession().getAttribute(Users.KEY_OF_ONLINE_USER_IN_HTTP_SESSION);
        if (null == sessionUser) {
            request.setAttribute("msg", "您已离线");
            request.setAttribute("forward", "forward:/courses/list");
            throw new UsersException("HttpOnlineUserAop sessionUser is null !! sessionUser is :" + sessionUser);
        }
        Users users = userService.getUserById(sessionUser.getUserId());
        if (null == users) {
            request.setAttribute("msg", "账户已失效，您已离线");
            request.setAttribute("forward", "forward:/courses/list");
            request.getSession().removeAttribute(Users.KEY_OF_ONLINE_USER_IN_HTTP_SESSION);
            request.getSession().invalidate();
            throw new UsersException("HttpOnlineUserAop users is null !! users is :" + sessionUser);
        }
        //reset sessionUser
        request.getSession().setAttribute(Users.KEY_OF_ONLINE_USER_IN_HTTP_SESSION,userService.makeUsersDto(users));

        return joinPoint.proceed();
    }

    private HttpServletRequest getHttpServletRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpServletRequest = attributes.getRequest();
        return httpServletRequest;
    }


}

package com.course.aop;

import com.course.dao.po.Admins;
import com.course.service.AdminService;
import com.course.service.dto.AdminsDto;
import com.course.service.exception.AdminsException;
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
@Order(4)
public class RequiredAdminLoginAop extends CommonUtil {
    @Autowired
    private AdminService adminService;

    @Pointcut("execution(* com.course.controller..*.*(..))&&@annotation(com.course.aop.RequiredAdminLogin)")
    public void declareJoinPointExpression() {
    }


    @Around("declareJoinPointExpression()")
    public Object doAroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = getHttpServletRequest();
        AdminsDto sessionAdmin = (AdminsDto) request.getSession().getAttribute(Admins.KEY_OF_ONLINE_ADMIN_IN_HTTP_SESSION);
        if (isNullObject(sessionAdmin)) {
            throw new AdminsException("AjaxOnlineAdminAop admin is offline !!",
                    AdminsException.ErrorCode.ADMIN_OFFLINE.getCode(),
                    AdminsException.ErrorCode.ADMIN_OFFLINE.getMsg());
        }
        AdminsDto adminsDto = adminService.getAdminByAdminId(sessionAdmin.getAdminId());
        request.getSession().setAttribute(Admins.KEY_OF_ONLINE_ADMIN_IN_HTTP_SESSION, adminsDto);
        return joinPoint.proceed();
    }

    private HttpServletRequest getHttpServletRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpServletRequest = attributes.getRequest();
        return httpServletRequest;
    }


}

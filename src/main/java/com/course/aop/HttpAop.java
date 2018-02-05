package com.course.aop;

import com.course.service.exception.CourseRuntimeException;
import com.course.util.CommonUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


@Component
@Aspect
public class HttpAop extends CommonUtil {

    @Pointcut("execution(* com.course.*.controller..*.*(..))&&!@annotation(org.springframework.web.bind.annotation.ResponseBody)")
    public void declareJoinPointExpression() {
    }


    @Around("declareJoinPointExpression()")
    public Object doAroundAdvice(ProceedingJoinPoint joinPoint) throws Exception {

        try {
            return joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
            return getHttpServletRequest().getAttribute("forward");
        }

    }

    private HttpServletRequest getHttpServletRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpServletRequest = attributes.getRequest();
        return httpServletRequest;
    }


}

package com.course.aop;

import com.course.service.exception.CourseRuntimeException;
import com.course.util.CommonUtil;
import com.course.util.Response;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@Component
@Aspect
public class AjaxAop extends CommonUtil {

    @Pointcut("execution(* com.course.*.controller..*.*(..))&&@annotation(org.springframework.web.bind.annotation.ResponseBody)")
    public void declareJoinPointExpression() {
    }


    @Around("declareJoinPointExpression()")
    public Object doAroundAdvice(ProceedingJoinPoint joinPoint) throws Exception {
        Response response = new Response();
        Object data = null;
        try {

            //执行方法
            data = joinPoint.proceed();//调用执行目标方法,先執行aop在執行了responsebody

            if (data == null) {//无参数
                return null;
            }


            //如果返回值为Map或者Response的实例，代表采用ajax方式
            if (data instanceof Map) {
                response.setData((Map<Object, Object>) data);
            } else if (data instanceof Response) {
                response = (Response) data;
            }
            return response;
        } catch (CourseRuntimeException e) {//提倡使用
            logger.error(e.getMessage());
            e.printStackTrace();
            response.setCode(e.getErrorCode());
            response.setMsg(e.getErrorMsg());
        } catch (RuntimeException e) {//以下不提倡使用
            logger.error(e.getMessage());
            e.printStackTrace();
            response.setCode(Response.ResponseCode.FAILURE.getCode());
            response.setMsg(Response.ResponseCode.FAILURE.getMsg());
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            response.setCode(Response.ResponseCode.FAILURE.getCode());
            response.setMsg(Response.ResponseCode.FAILURE.getMsg());
        } catch (Throwable e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            response.setCode(Response.ResponseCode.FAILURE.getCode());
            response.setMsg(Response.ResponseCode.FAILURE.getMsg());
        }
        return response;

    }

    private HttpServletRequest getHttpServletRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpServletRequest = attributes.getRequest();
        return httpServletRequest;
    }


}

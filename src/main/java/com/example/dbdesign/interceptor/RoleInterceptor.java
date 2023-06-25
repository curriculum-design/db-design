package com.example.dbdesign.interceptor;

import com.example.dbdesign.annotation.RoleCheck;
import com.example.dbdesign.common.ErrorCode;
import com.example.dbdesign.exception.BusinessException;
import com.example.dbdesign.model.dto.UserDTO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import static com.example.dbdesign.constants.UserConstant.SESSION_KEY;

/**
 * @author zzs
 * @date 2023/6/26 1:34
 */
@Aspect
@Component
public class RoleInterceptor {

    @Around("@annotation(roleCheck)")
    public Object doInterceptor(ProceedingJoinPoint joinPoint, RoleCheck roleCheck) throws Throwable {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        UserDTO loginUser = (UserDTO) request.getSession().getAttribute(SESSION_KEY);
        if (loginUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN);
        }
        Integer userRole = loginUser.getUserRole();
        if (userRole != 1) {
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
        return joinPoint.proceed();
    }

}

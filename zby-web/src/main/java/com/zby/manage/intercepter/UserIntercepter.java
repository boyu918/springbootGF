package com.zby.manage.intercepter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.zby.manage.jwt.PassToken;
import com.zby.manage.jwt.UserLoginToken;
import com.zby.manage.model.UserJwt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author zby
 * @time 2019/5/23 16:26
 */
@Slf4j
public class UserIntercepter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        log.info("拦截器开始");
        String token = httpServletRequest.getHeader("token");
        if (!(o instanceof HandlerMethod)){
            //如果转入的不是方法
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod)o;
        Method method = handlerMethod.getMethod();


        if (method.isAnnotationPresent(PassToken.class)){
            //如果passtoken 则跳过token
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()){
                return true;
            }
        }

        if (method.isAnnotationPresent(UserLoginToken.class)){
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()){
                if (token == null){
                    throw new RuntimeException("无token，请重新登录");
                }
                String userId;
                try {
                    userId = JWT.decode(token).getAudience().get(0);
                } catch (JWTDecodeException j) {
                    throw new RuntimeException("401");
                }
                UserJwt user = new UserJwt();
                user.setPassword("123456");
                if (user == null) {
                    throw new RuntimeException("用户不存在，请重新登录");
                }
                // 验证 token
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
                try {
                    jwtVerifier.verify(token);
                } catch (JWTVerificationException e) {
                    throw new RuntimeException("401");
                }
                return true;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        log.info("拦截器生效");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        log.info("拦截器结束");
    }
}

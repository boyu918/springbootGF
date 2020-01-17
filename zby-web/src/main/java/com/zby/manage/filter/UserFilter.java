package com.zby.manage.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author zby
 * @time 2019/5/23 16:13
 */
@Component
@Slf4j
@WebFilter(urlPatterns = "/user/*",filterName = "userFilter")
@Order(1)
public class UserFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("过滤器开始生效");
        filterChain.doFilter(servletRequest,servletResponse);
        log.info("过滤器结束");
    }

    @Override
    public void destroy() {

    }
}

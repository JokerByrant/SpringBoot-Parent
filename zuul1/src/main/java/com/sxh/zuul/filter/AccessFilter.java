package com.sxh.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 对路由进行过滤
 * @author sxh
 * @date 2020/3/14
 */
@Component
public class AccessFilter extends ZuulFilter {

    private Logger logger = LoggerFactory.getLogger(ZuulFilter.class);

    /**
     * 过滤器的类型，决定过滤器在请求的哪个生命周期执行
     * pre代表会在请求被路由之前执行
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器执行顺序，数值越小优先级越高。
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 当前过滤器是否执行，返回true会执行。
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 自定义的过滤器逻辑，当shouldFilter()返回true时会执行。
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String method = request.getMethod();
        String remoteHost = request.getRemoteHost();
        String requestURI = request.getRequestURI();

        logger.info("Remote host:{},method:{},uri:{}", remoteHost, method, requestURI);
        logger.info("请求方法：" + method);
        logger.info("请求地址：" + requestURI);

        return null;
    }
}

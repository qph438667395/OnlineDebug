package com.spring.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.spring.exception.ULoginException;

public class ULoginInterceptor extends HandlerInterceptorAdapter{

	 private List<String> excludedUrls;

	    public void setExcludedUrls(List<String> excludedUrls) {
	        this.excludedUrls = excludedUrls;
	    }
	
	    @Override
	    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

	        //排除excludedUrls中的url
	        String requestUri = request.getRequestURI();
	        for (String url : excludedUrls) {
	            System.out.println(requestUri);
	            if (requestUri.indexOf(url)>0) {
	                return true;
	            }
	        }
	        //验证用户是否已经登录
	        Object user = request.getSession().getAttribute("user");
	        if (user == null)
	            throw new ULoginException();
	        else
	            return true;
	    }   

}

package com.spring.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.spring.exception.ALoginException;

public class ALoginInterceptor extends HandlerInterceptorAdapter{
	 private List<String> excludedUrls;

	    public void setExcludedUrls(List<String> excludedUrls) {
	        this.excludedUrls = excludedUrls;
	    }
	
	    @Override
	    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

	        //�ų�excludedUrls�е�url
	        String requestUri = request.getRequestURI();
	        for (String url : excludedUrls) {
	            System.out.println(requestUri);
	            if (requestUri.indexOf(url)>0) {
	                return true;
	            }
	        }
	        //��֤�û��Ƿ��Ѿ���¼
	        Object admin= request.getSession().getAttribute("admin");
	        if (admin == null)
	            throw new ALoginException();
	        else
	            return true;
	    }   

}

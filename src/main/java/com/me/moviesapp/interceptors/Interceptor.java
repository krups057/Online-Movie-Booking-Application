package com.me.moviesapp.interceptors;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.me.moviesapp.pojo.Account;

public class Interceptor extends  HandlerInterceptorAdapter {

	 private String logout;
	    
	   
	    
	    public String getLogout() {
		return logout;
	}

	public void setLogout(String logout) {
		this.logout = logout;
	}

		
	    @Override
	    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	        throws Exception {
	        
	        System.out.println("Inside User Interceptor");
	        HttpSession session = request.getSession();
	        if(session != null){
	            
	        
	        
	        Object o = session.getAttribute("user");
	        if(o != null){
	            
	        try{
	            Account user = (Account) o;
	            return true;
	        }
	        catch(Exception e){
	            System.out.println("Exception in Recruiterinterceptor");
	            e.printStackTrace();
	        }}else{
	            response.sendRedirect(logout);
	            return false;
	        }
	        
	        }
	        
	        response.sendRedirect(logout);
	        return false;
	    }

	   @Override
	    public void postHandle(
	            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
	            throws Exception {
	        super.postHandle(request, response, handler, modelAndView);
	    }    
}

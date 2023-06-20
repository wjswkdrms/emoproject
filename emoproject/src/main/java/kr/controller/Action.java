package kr.controller;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
   
public interface Action {
	//異붿긽 硫붿꽌�뱶
	public String execute(HttpServletRequest request, 
			              HttpServletResponse response)
			              throws Exception;
}

package com.me.moviesapp.controllers;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.me.moviesapp.dao.MovieDAO;
import com.me.moviesapp.dao.CartDAO;
import com.me.moviesapp.dao.GenreDAO;
import com.me.moviesapp.dao.DAO;
import com.me.moviesapp.dao.AccountDAO;

@Controller
@RequestMapping("/movie/*")
public class LogoutController {

	@Autowired
	@Qualifier("movieDao")
	MovieDAO movieDao;
	
	@Autowired
	@Qualifier("genreDao")
	GenreDAO genreDao;
	
	@Autowired
	@Qualifier("accountDao")
	AccountDAO accountDao;
	
	@Autowired
	ServletContext servletContext;
	
	@RequestMapping(value = "/movie/logout", method = RequestMethod.POST)
	public String logout(HttpServletRequest request){
        HttpSession httpSession = request.getSession();
        httpSession.invalidate();
        return "logout";
    }
}


package com.me.moviesapp.controllers;


import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.AbstractController;

import com.me.moviesapp.dao.MovieDAO;
import com.me.moviesapp.dao.CartDAO;
import com.me.moviesapp.dao.GenreDAO;
import com.me.moviesapp.dao.DAO;
import com.me.moviesapp.dao.AccountDAO;
import com.me.moviesapp.pojo.Cart;
import com.me.moviesapp.pojo.PDFView;
import com.me.moviesapp.pojo.Account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/cart/*")
public class BillingController extends PDFView{

	//private static final Logger logger = LoggerFactory.getLogger(BillController.class);
	
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
	@Qualifier("cartDao")
	CartDAO cartDao;
	
	@Autowired
	ServletContext servletContext;
	
	
	@RequestMapping(value = "/cart/checkout", method = RequestMethod.POST)
	public ModelAndView showPdfReport(@ModelAttribute("cart") Cart cart,
									  ModelMap model,
			                          BindingResult result, 
			                          HttpServletRequest request) throws Exception
	{
		List<Cart> view=cartDao.list();
	
		model.addAttribute("cartitems", view);
		View v = new PDFView();
		return new ModelAndView(v);
	}
	
}


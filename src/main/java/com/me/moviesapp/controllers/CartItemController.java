package com.me.moviesapp.controllers;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import com.me.moviesapp.dao.MovieDAO;
import com.me.moviesapp.dao.CartDAO;
import com.me.moviesapp.dao.GenreDAO;
import com.me.moviesapp.dao.DAO;
import com.me.moviesapp.dao.AccountDAO;
import com.me.moviesapp.pojo.Cart;
import com.me.moviesapp.pojo.Movie;
import com.me.moviesapp.pojo.PDFView;
import com.me.moviesapp.pojo.Account;

@Controller
@RequestMapping("/cart/*")
public class CartItemController extends DAO{

	@Autowired
	@Qualifier("movieDao")
	MovieDAO movieDao;
	
	@Autowired
	@Qualifier("genreDao")
	GenreDAO categoryDao;
	
	@Autowired
	@Qualifier("accountDao")
	AccountDAO accountDao;
	
	@Autowired
	@Qualifier("cartDao")
	CartDAO cartDao;
	
	@Autowired
	ServletContext servletContext;
	
	
	
	@RequestMapping(value = "/cart/insert", method = RequestMethod.POST)
	public ModelAndView addGenre(@ModelAttribute("cart") Cart cart, BindingResult result, HttpServletRequest request) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		Account a = (Account)session.getAttribute("account");
		
		
		cart.setAccount(a);
		a.setCart(cart);
		Cart cd=null;
		 List<Cart> c=cartDao.list();
		 Movie mov = getSelectedAdvert(request);
		 int i=0;
		 Cart cw = null;
		 
		 for(Cart cc:c){
			 if(a.getPersonID()==cc.getId()){
				 
				 Set<Movie> movieset = cc.getMovies();
				 movieset.add(mov);
				 cart = cartDao.updateCart(cc);
				 i=1;
				 return new ModelAndView("user-cart","c",movieset);
			 }
		 }
		 Set<Movie> movieset = cart.getMovies();
	 if (i==0){
		 movieset.add(mov);
		  cd = cartDao.insert(cart);
	 }
	
		return new ModelAndView("user-cart","c",movieset);
	}
	
	
	private Movie getSelectedAdvert(HttpServletRequest request) {
		int advID = Integer.parseInt(request.getParameter("id"));
		return movieDao.getMovie(advID);
	}
}

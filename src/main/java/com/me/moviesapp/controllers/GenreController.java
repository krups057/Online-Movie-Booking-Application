package com.me.moviesapp.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;


import com.me.moviesapp.dao.MovieDAO;
import com.me.moviesapp.exception.GenreException;
import com.me.moviesapp.dao.CartDAO;
import com.me.moviesapp.dao.GenreDAO;
import com.me.moviesapp.dao.DAO;
import com.me.moviesapp.dao.AccountDAO;
import com.me.moviesapp.pojo.Cart;
import com.me.moviesapp.pojo.Genre;
import com.me.moviesapp.pojo.PDFView;
import com.me.moviesapp.validators.GenreValidator;
import com.me.moviesapp.pojo.Account;


@Controller
@RequestMapping("/genre/*")
public class GenreController {

		@Autowired
		@Qualifier("genreValidator")
		GenreValidator genreValidator;
		
		@Autowired
		@Qualifier("genreDao")
		GenreDAO genreDAO;

		@InitBinder
		private void initBinder(WebDataBinder binder) {
			binder.setValidator(genreValidator);
		}

		@RequestMapping(value = "/genre/add", method = RequestMethod.POST)
		public ModelAndView addgenre(@ModelAttribute("genre") Genre genre, BindingResult result) throws Exception {
			
			genreValidator.validate(genre, result);
			
			if (result.hasErrors()) {
				return new ModelAndView("genre-form", "genre", genre);
			}

			try {				
				genre = genreDAO.create(genre.getTitle());
			} catch (GenreException e) {
				System.out.println(e.getMessage());
				return new ModelAndView("error", "errorMessage", "login error");
			}
			return new ModelAndView("genre-success", "genre", genre);
			
		}

		@RequestMapping(value="/genre/add", method = RequestMethod.GET)
		public ModelAndView initializeForm() throws Exception {			
			return new ModelAndView("genre-form", "genre", new Genre());
		}


}
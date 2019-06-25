package com.me.moviesapp.controllers;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;


import com.me.moviesapp.dao.MovieDAO;
import com.me.moviesapp.exception.MovieException;
import com.me.moviesapp.dao.CartDAO;
import com.me.moviesapp.dao.GenreDAO;
import com.me.moviesapp.dao.DAO;
import com.me.moviesapp.dao.AccountDAO;
import com.me.moviesapp.pojo.Cart;
import com.me.moviesapp.pojo.Genre;
import com.me.moviesapp.pojo.Movie;
import com.me.moviesapp.pojo.PDFView;
import com.me.moviesapp.validators.GenreValidator;
import com.me.moviesapp.pojo.Account;

@Controller
@RequestMapping("/movie/*")
public class MovieController {
		
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

		@RequestMapping(value = "/movie/add", method = RequestMethod.POST)
		public ModelAndView addgenre(@ModelAttribute("movie") Movie movie, BindingResult result) throws Exception {

			try {			
				
				Account a = accountDao.get(movie.getPostedBy());
				movie.setAccount(a);
				movie = movieDao.create(movie);
				
	            
	            for(Genre c: movie.getGenres()){
	            	c = genreDao.get(c.getTitle());
	            	c.getMovies().add(movie);
	            	genreDao.update(c); 
				
	            }
	            if (movie.getFilename().trim() != "" || movie.getFilename() != null) {
					File directory;
					String check = File.separator; 
					String path = null;
					if (check.equalsIgnoreCase("\\")) {
						path = servletContext.getRealPath("").replace("build\\", ""); 
																							}

					if (check.equalsIgnoreCase("/")) {
						path = servletContext.getRealPath("").replace("build/", "");
						path += "/"; 
					}
					directory = new File(path + "\\" + movie.getFilename());
					boolean temp = directory.exists();
					if (!temp) {
						temp = directory.mkdir();
					}
					if (temp) {
						
						CommonsMultipartFile photoInMemory = movie.getPhoto();

						String fileName = photoInMemory.getOriginalFilename();
						

						File localFile = new File(directory.getPath(), fileName);

						
						photoInMemory.transferTo(localFile);
						movie.setFilename(localFile.getPath());
						System.out.println("File is stored at" + localFile.getPath());
						System.out.print("registerNewaccount");
						Movie m = movieDao.create(movie);

					} else {
						System.out.println("Failed to create directory!");
					}
					
				}
				
	            return new ModelAndView("movie-success", "movie", movie);
	            
			} catch (MovieException e) {
				System.out.println(e.getMessage());
				return new ModelAndView("error", "errorMessage", "error while login");
			}
			
			
		}
		
		@RequestMapping(value = "/movie/list", method = RequestMethod.GET)
		public ModelAndView addgenre(HttpServletRequest request) throws Exception {

			ModelAndView mav = new ModelAndView("movie-list");
			List<Movie> movies = movieDao.list();
			mav.addObject("movies", movies);
	        mav.addObject("cart", new Cart());
	        return mav;
			
		}
		
		@RequestMapping(value = "/movie/customerlist", method = RequestMethod.GET)
		public ModelAndView addCategories(HttpServletRequest request) throws Exception {

			try {			
				
				List<Movie> movies = movieDao.list();
				return new ModelAndView("Producer-movie-list", "movies", movies);
				
			} catch (MovieException e) {
				System.out.println(e.getMessage());
				return new ModelAndView("error", "errorMessage", "error while login");
			}
			
			
		}

		@RequestMapping(value="/movie/add", method = RequestMethod.GET)
		public ModelAndView initializeForm(HttpServletRequest request) throws Exception {		
			ModelAndView mv = new ModelAndView();
			mv.addObject("genres", genreDao.list());			
			mv.addObject("movie", new Movie());
			mv.setViewName("movie-form");
			return mv;
		}


}
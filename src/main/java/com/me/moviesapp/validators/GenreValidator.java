package com.me.moviesapp.validators;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.me.moviesapp.dao.GenreDAO;
import com.me.moviesapp.exception.GenreException;
import com.me.moviesapp.pojo.Genre;

@Component
public class GenreValidator implements Validator {

	@Autowired
	@Qualifier("genreDao")
	GenreDAO genreDAO;
	
	public boolean supports(Class aClass) {
		return Genre.class.equals(aClass);
	}

	public void validate(Object obj, Errors errors) {
		Genre genre = (Genre) obj;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "error.invalid.Genre", "Genre Required");
		
		if (errors.hasErrors()) {
            return;
        }
        
	
		try {
			Genre c = genreDAO.get(genre.getTitle());
			if(c !=null)
				errors.rejectValue("title", "error.invalid.Genre", "Genre already Exists");
			
		} catch (GenreException e) {
			System.err.println("Exception in Category Validator");
		}
		
		
		
	
	}
}


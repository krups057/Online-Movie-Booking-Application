package com.me.moviesapp.validators;



import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.me.moviesapp.pojo.Movie;

@Component
public class MovieValidator implements Validator {

	public boolean supports(Class aClass) {
		return aClass.equals(Movie.class);
	}

	private Pattern pattern;  
	 private Matcher matcher;  
	
	 private static final 
	 String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"  
			   + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";    
	 String STRING_PATTERN = "[a-zA-Z]+";
	
	public void validate(Object obj, Errors errors) {
		Movie movie = (Movie) obj;

		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "error.invalid.title", "Title Required");
		 if (!(movie.getTitle() != null && movie.getTitle().isEmpty())) {  
			   pattern = Pattern.compile(STRING_PATTERN);  
			   matcher = pattern.matcher(movie.getTitle());  
			   if (!matcher.matches()) {  
			    errors.rejectValue("title", "title.containNonChar",  
			      "Enter a valid title");  
			   }  
			  }
		 
		 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "filename", "error.invalid.filename", "Filename Required");
		 if (!(movie.getFilename() != null && movie.getFilename().isEmpty())) {  
			   pattern = Pattern.compile(STRING_PATTERN);  
			   matcher = pattern.matcher(movie.getFilename());  
			   if (!matcher.matches()) {  
			    errors.rejectValue("filename", "filename.containNonChar",  
			      "Enter a valid filename");  
			   }  
			  }
		 
		 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "message", "error.invalid.filename", "Product Description Required");
		 if (!(movie.getMessage() != null && movie.getMessage().isEmpty())) {  
			   pattern = Pattern.compile(STRING_PATTERN);  
			   matcher = pattern.matcher(movie.getMessage());  
			   if (!matcher.matches()) {  
			    errors.rejectValue("message", "message.containNonChar",  
			      "Enter a valid product description");  
			   }  
			  }
	}
}


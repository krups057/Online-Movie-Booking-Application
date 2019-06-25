package com.me.moviesapp.validators;



import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.me.moviesapp.pojo.Account;;

public class AccountValidator implements Validator {

	public boolean supports(Class aClass) {
		return aClass.equals(Account.class);
	}

	 private Pattern pattern;  
	 private Matcher matcher;  
	
	 private static final 
	 String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"  
			   + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";    
	 String STRING_PATTERN = "[a-zA-Z]+";  
	
			 
			 
	public void validate(Object obj, Errors errors) {
		Account account = (Account) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "error.invalid.user", "First Name Required");
		 if (!(account.getFirstName() != null && account.getFirstName().isEmpty())) {  
			   pattern = Pattern.compile(STRING_PATTERN);  
			   matcher = pattern.matcher(account.getFirstName());  
			   if (!matcher.matches()) {  
			    errors.rejectValue("firstName", "firstName.containNonChar",  
			      "Enter a valid first name");  
			   }  
			  }  
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "error.invalid.user", "Last Name Required");
		if (!(account.getLastName() != null && account.getLastName().isEmpty())) {  
			   pattern = Pattern.compile(STRING_PATTERN);  
			   matcher = pattern.matcher(account.getLastName());  
			   if (!matcher.matches()) {  
			    errors.rejectValue("lastName", "lastName.containNonChar",  
			      "Enter a valid last name");  
			   }  
			  } 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "error.invalid.user", "User Name Required");
		if (!(account.getUsername() != null && account.getUsername().isEmpty())) {  
			   pattern = Pattern.compile(STRING_PATTERN);  
			   matcher = pattern.matcher(account.getUsername());  
			   if (!matcher.matches()) {  
			    errors.rejectValue("username", "username.containNonChar",  
			      "Enter a valid username");  
			   }  
			  }
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.invalid.password", "Password Required");
		if (!(account.getPassword() != null && account.getPassword().isEmpty())) {  
			   pattern = Pattern.compile(STRING_PATTERN);  
			   matcher = pattern.matcher(account.getPassword());  
			   if (!matcher.matches()) {  
			    errors.rejectValue("password", "password.containNonChar",  
			      "Enter a valid password of strings");  
			   }  
			  }
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email.emailAddress", "error.invalid.email.emailAddress",
				"Email Required");
		 
		if("NONE".equals(account.getUsertype())){
			errors.rejectValue("usertype", "error.invalid.usertype","User type is required");
		}
		
		
	}
}

package com.me.moviesapp.controllers;



import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.me.moviesapp.dao.AccountDAO;
import com.me.moviesapp.exception.AccountException;
import com.me.moviesapp.pojo.Account;
import com.me.moviesapp.validators.AccountValidator;

@Controller
@RequestMapping("/account/*")
public class AccountController {

	
	@Autowired
	@Qualifier("accountDao")
	AccountDAO accountDao;

	@Autowired
	@Qualifier("accountValidator")
	AccountValidator validator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	protected String goToUserHome(HttpServletRequest request) throws Exception {
		return "user-home";
	}
	
	
	@RequestMapping(value = "/account/login", method = RequestMethod.POST)
	protected String loginUser(HttpServletRequest request) throws Exception {

		HttpSession session = (HttpSession) request.getSession();
		
		try {

			System.out.print("loginUser");

			Account a = accountDao.get(request.getParameter("username"), request.getParameter("password"));
			
			if(a == null){
				System.out.println("Error incorrect username and password");
				session.setAttribute("errorMessage", "UseerName/Password does not exist");
				return "error";
			}
			
			else if(a.getUsertype().equals("customer")){
				session.setAttribute("account", a);
				return "customer-homepage";
			}
			
			else if(!(a.getUsertype().equals("customer"))&&!(a.getUsertype().equals("producer"))){
				session.setAttribute("errorMessage", "UsserName/Password does not exist");
				return "error";
			}
			
			else{
				session.setAttribute("account", a);
				return "user-home";
			}
			
		
		} catch (AccountException e) {
			System.out.println("Exception: " + e.getMessage());
			session.setAttribute("errorMessage", "error while login");
			return "error";
		}

	}
	
	@RequestMapping(value = "/account/register", method = RequestMethod.GET)
	protected ModelAndView registerUser() throws Exception {
		System.out.print("registerUser");

		ModelAndView mav = new ModelAndView("register-user");
		Map<String,String> usertype = new LinkedHashMap<String,String>();
		usertype.put("customer", "customer");
		usertype.put("producer", "producer");
		
		mav.addObject("usertype", usertype);
        mav.addObject("account", new Account());
        return mav;
		

	}
	
	@RequestMapping(value = "/account/register", method = RequestMethod.POST)
	protected ModelAndView registerNewUser(HttpServletRequest request,  @ModelAttribute("account") Account account, BindingResult result) throws Exception {

		validator.validate(account, result);

		if (result.hasErrors()) {
			ModelAndView mav = new ModelAndView("register-user");
			Map<String,String> usertype = new LinkedHashMap<String,String>();
			usertype.put("Customer", "Customer");
			usertype.put("Producer", "Producer");
			
			mav.addObject("usertype", usertype);
	        mav.addObject("account", account);
	        return mav;
			
		}

		try {

			System.out.print("registerNewUser");

			Account a = accountDao.register(account);
			
			request.getSession().setAttribute("account", a);
			Email email= new SimpleEmail();
	           email.setHostName("smtp.googlemail.com");
	           email.setSmtpPort(465);
	           email.setAuthentication("krups057@gmail.com", "05071995");
	           email.setSSLOnConnect(true);
	           email.setFrom(account.getEmail().getEmailAddress());
	           email.setSubject("Sign Up Successful");
	           email.setMsg("krupa cinemas. Acount created successfully");
	           email.addTo(account.getEmail().getEmailAddress());
	           email.send();
			
			return new ModelAndView("account-success", "user", a);

		} catch (AccountException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
	}

		protected Map referenceData(HttpServletRequest request) throws Exception {

		Map referenceData = new HashMap();

		Map<String,String> usertype = new LinkedHashMap<String,String>();
		usertype.put("Customer", "Customer");
		usertype.put("Producer", "Producer");
		referenceData.put("usertype", usertype);
		
		return referenceData;
	}
}

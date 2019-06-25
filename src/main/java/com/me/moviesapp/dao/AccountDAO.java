package com.me.moviesapp.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import com.me.moviesapp.exception.AccountException;
import com.me.moviesapp.pojo.Email;
import com.me.moviesapp.pojo.Account;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
public class AccountDAO extends DAO {

	public AccountDAO() {
	}

	public Account get(String username, String password) throws AccountException {
		try {
			begin();
			Query q = getSession().createQuery("from account_table where username = :username and password = :password");
			System.out.println("hi");
			q.setString("username", username);
			q.setString("password", password);
			Account account = (Account) q.uniqueResult();
			
			commit();
			return account;
		} catch (HibernateException e) {
			rollback();
			System.out.println(e.getMessage());
			throw new AccountException("Could not get user " + username, e);
		}
	}
	
	public Account get(int userId) throws AccountException {
		try {
			begin();
			Query q = getSession().createQuery("from account_table where personID= :personID");
			q.setInteger("personID", userId);		
			Account account = (Account) q.uniqueResult();
			commit();
			return account;
		} catch (HibernateException e) {
			rollback();
			throw new AccountException("Could not get user " + userId, e);
		}
	}

	public Account register(Account u)
			throws AccountException {
		try {
			begin();
			System.out.println("inside DAO");

			Email email = new Email(u.getEmail().getEmailAddress());
			Account account = new Account(u.getUsername(), u.getPassword(), u.getUsertype());

			account.setFirstName(u.getFirstName());
			account.setLastName(u.getLastName());
			account.setEmail(email);
			email.setUser(account);
			getSession().save(account);
			commit();
			return account;

		} catch (HibernateException e) {
			rollback();
			throw new AccountException("Exception while creating user: " + e.getMessage());
		}
	}

	public void delete(Account user) throws AccountException {
		try {
			begin();
			getSession().delete(user);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new AccountException("Could not delete user " + user.getUsername(), e);
		}
	}
}

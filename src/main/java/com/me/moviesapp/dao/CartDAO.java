package com.me.moviesapp.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.me.moviesapp.exception.AccountException;
import com.me.moviesapp.exception.CartException;
import com.me.moviesapp.exception.GenreException;
import com.me.moviesapp.pojo.Movie;
import com.me.moviesapp.pojo.Cart;
import com.me.moviesapp.pojo.Genre;
import com.me.moviesapp.pojo.Account;

public class CartDAO extends DAO {
	
	public CartDAO(){
	
	}

	public Cart insert(Cart cart) throws CartException {
		try{
			begin();   
			System.out.println(cart.getTitle()+cart.getGenre()+cart.getFilename()+cart.getTotalprice());
			Cart c=new Cart(cart.getTitle(),cart.getGenre(),cart.getFilename(),cart.getTotalprice());
			getSession().save(c);     
            commit();
            return cart;
		} catch (HibernateException e){
			rollback();
            throw new CartException("Could not save the cartg", e);
		}
	}
	
	public void update(Cart cart) throws GenreException {
        try {
            begin();
            getSession().update(cart);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new GenreException("Could not save the carttt", e);
        }
    }
	
	public Account update(Account account) throws GenreException {
        try {
            begin();
            getSession().update(account);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new GenreException("Could not save the user", e);
        }
        return account;
    }
	
	public List<Cart> list(){
		begin();
		Query q = getSession().createQuery("from cart_table");
		List<Cart> cart1 = q.list();
		commit();
		return cart1;
	}
	
	public Cart updateCart(Cart cart) throws AccountException {
        try {
            begin();
            getSession().update(cart);
            commit();
            return cart;
        } catch (HibernateException e) {
            rollback();
            throw new AccountException("Could not save the cart", e);
        }
    }
	

}

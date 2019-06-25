package com.me.moviesapp.dao;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.me.moviesapp.exception.MovieException;
import com.me.moviesapp.pojo.Movie;
import com.me.moviesapp.pojo.Genre;

public class MovieDAO extends DAO {

    public Movie create(Movie movie)
            throws MovieException {
        try {
            begin();            
            getSession().save(movie);     
            commit();
            return movie;
        } catch (HibernateException e) {
            rollback();
            
            throw new MovieException("Movie Exception " + e.getMessage());
        }
    }

    public void delete(Movie movie)
            throws MovieException {
        try {
            begin();
            getSession().delete(movie);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new MovieException("Couldnt delete", e);
        }
    }
    
    public List<Movie> list() throws MovieException{
    	
    	try {
            begin();
            Query q = getSession().createQuery("from movie_table");
            List<Movie> list = q.list();
            commit();
            return list;
        } catch (HibernateException e) {
            rollback();
            throw new MovieException("Couldntawd delete", e);
        }
    	
    }
    
    public Movie getMovie(int advIDno) {
    	try {
			begin();
			Query q = getSession().createQuery("from movie_table where movieID=:advID");
			q.setLong("advID", advIDno);

			Movie mov = (Movie) q.uniqueResult();
			//System.out.println(user.getUsertype());
			commit();
			return mov;
		} catch (HibernateException e) {
			rollback();
			try {
				throw new MovieException("Could not get Advert " + advIDno, e);
			} catch (MovieException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
    }
}
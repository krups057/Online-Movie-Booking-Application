package com.me.moviesapp.dao;



import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import com.me.moviesapp.exception.GenreException;
import com.me.moviesapp.pojo.Genre;

public class GenreDAO extends DAO {

    public Genre get(String title) throws GenreException {
        try {
            begin();
            Query q=getSession().createQuery("from genre_table where title=:title");
            q.setString("title",title);
            Genre genre=(Genre)q.uniqueResult();
            commit();
            return genre;
        } catch (HibernateException e) {
            rollback();
            throw new GenreException("Couldnt get the named genre " + title + " " + e.getMessage());
        }
    }

    public List<Genre> list() throws GenreException {
        try {
            begin();
            Query q = getSession().createQuery("from genre_table");
            List<Genre> list = q.list();
            commit();
            return list;
        } catch (HibernateException e) {
            rollback();
            throw new GenreException("coudnt list the genre", e);
        }
    }

    public Genre create(String title) throws GenreException {
        try {
            begin();
            Genre cat = new Genre(title);
            getSession().save(cat);
            commit();
            return cat;
        } catch (HibernateException e) {
            rollback();
            
            throw new GenreException("Exception: " + e.getMessage());
        }
    }

    public void update(Genre genre) throws GenreException {
        try {
            begin();
            getSession().update(genre);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new GenreException("Couldnt save the genre", e);
        }
    }

    public void delete(Genre genre) throws GenreException {
        try {
            begin();
            getSession().delete(genre);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new GenreException("Couldnt delete the genre", e);
        }
    }
}

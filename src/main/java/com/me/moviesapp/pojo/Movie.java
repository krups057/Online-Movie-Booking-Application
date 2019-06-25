package com.me.moviesapp.pojo;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Entity(name="movie_table")
@Table(name="movie_table")
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="movieID", unique = true, nullable = false)
    private long id;
	
	@Column(name="title")
    private String title;
	
	@Column(name="message")
    private String message;
	
	@Column(name = "filename")
	private String filename; 
	
	@Column(name = "price")
	private Float price;
	
	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	@ManyToOne
	private Account Account;
	
	@ManyToMany(mappedBy="movies")
	private Set<Genre> genres = new HashSet<Genre>();
	
	@ManyToMany(mappedBy="movies")
	private Set<Cart> cart = new HashSet<Cart>();
	
	@Transient
	int postedBy;
	
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public CommonsMultipartFile getPhoto() {
		return photo;
	}

	public void setPhoto(CommonsMultipartFile photo) {
		this.photo = photo;
	}

	@Transient
	private CommonsMultipartFile photo;

    public Movie(String title, String message, Account Account, Genre genre, Cart cart) {
        this.title = title;
        this.message = message;
        this.Account = Account;      
        this.genres.add(genre); 
        this.cart.add(cart);
    }

    public Movie() {
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Account getAccount() {
		return Account;
	}

	public void setAccount(Account Account) {
		this.Account = Account;
	}

	public Set<Genre> getGenres() {
		return genres;
	}

	public void setGenres(Set<Genre> genres) {
		this.genres = genres;
	}

	public int getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(int postedBy) {
		this.postedBy = postedBy;
	}

    
  

   
}
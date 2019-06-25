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
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


@Entity(name="cart_table")
@Table(name="cart_table")
@PrimaryKeyJoinColumn(name = "personID")
public class Cart{

	@Id
	@GeneratedValue(generator = "generator")
	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "account"))
	@Column(name = "cartID", unique = true, nullable = false)
	private long id;
	
	@OneToOne
	@PrimaryKeyJoinColumn
	private Account account;
	
	@ManyToMany
    @JoinTable (
       name="cart_movie_table",
       joinColumns = {@JoinColumn(name="cartID", nullable = false, updatable = false, referencedColumnName="cartID")},
       inverseJoinColumns = {@JoinColumn(name="movieID", referencedColumnName="movieID" )}
    )
	private Set<Movie> movies = new HashSet<Movie>();
	
	@Column(name="title")
	private String title;
	
	@Column(name="genre")
	private String genre;
	
	@Column(name="filename")
	private String filename;
	
	@Column(name="totalprice")
	private String totalprice;
	


	public Cart(){
		
	}
	
	public Cart(String title, String genre, String filename, String totalprice) {
		this.title = title;
		this.genre = genre;
		this.filename = filename;
		this.totalprice = totalprice;
		
	}
	

	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Set<Movie> getMovies() {
		return movies;
	}

	public void setAdverts(Set<Movie> movies) {
		this.movies = movies;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(String totalprice) {
		this.totalprice = totalprice;
	}
	
	
	
	
	
}

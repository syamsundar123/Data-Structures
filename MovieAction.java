package com.highradius.Action;


import java.sql.SQLException;

import com.highradius.Manager.*;
import com.higradius.modal.Movies;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import com.opensymphony.xwork2.ActionSupport;



public class MovieAction extends Movies {

	private static final long serialVersionUID = -4489869496353870219L;

	/*
	 * String title; String description; String releaseYear; int language; String
	 * directorName; String rating; String specialFeatures; String filmId;
	 */
	

	


	ApplicationContext context = new  ClassPathXmlApplicationContext("applicationContext.xml"); 
	 manager Manager = (manager)context.getBean("manager");
	

	 public String addFilm() throws SQLException
	 { 
		System.out.println("Connecting to the database to add the new Film");
		Movies movie = new Movies(); 
		movie.setTitle(getTitle()); 
		movie.setDescription(getDescription());
		movie.setDirectorName(getDirectorName()); 
		movie.setRating(getRating());
		movie.setLanguage(getLanguage()); 
		movie.setReleaseYear(getReleaseYear());
		movie.setSpecialFeatures(getSpecialFeatures());
	  Manager.addMovie(movie);
	  return "success";
	 }
	 
	 public String updateFilm() throws SQLException
	 {
		 
		 Movies movie = new Movies(); 
			movie.setTitle(getTitle()); 
			movie.setDescription(getDescription());
			movie.setDirectorName(getDirectorName()); 
			movie.setRating(getRating());
			movie.setLanguage(getLanguage()); 
			movie.setReleaseYear(getReleaseYear());
			movie.setSpecialFeatures(getSpecialFeatures());
			System.out.println("Movie Object created Sending Object to Update");
			Manager.updateMovie(movie);
		 return "success";
	 }
	 public String deleteFilm() throws SQLException
	 {
		 
		 
		Manager.deleteMovie(getFilmId());
		 return "success";
	 }
	 
	 HashMap<String, Object> movieList = new HashMap<>();
	 

	public HashMap<String, Object> getMovieList() {
		
		return movieList;
	}

	public void setMovieList(HashMap<String, Object> movieList) {
		
		this.movieList = movieList;
		
	}

	public String fetchFilm() throws SQLException
	 {
		System.out.println("Ready to fetch");
			 movieList =  Manager.getAllMovies(0,10);
			 System.out.println("hdvvf"+movieList.get("movieList"));
			 System.out.println("Data Mining COmpleted,Ready to Extract");
		
		
		
		 return "success";
	 }
	
	 
}

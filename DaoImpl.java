package com.highradius.DaoImpl;


import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import com.highradius.DAO.Dao;
import com.higradius.hibernate.Util.hibernateUtil;
import com.higradius.modal.Movies;


public class DaoImpl implements Dao{
	
	SessionFactory sessionFactory = null;
	
	private HashMap<String,Object> movieList = new HashMap<>();
	private ArrayList<Movies> movielist = new ArrayList<>();
	
	@SuppressWarnings("rawtypes")
	public  HashMap<String, Object> getAllMovies(Integer start,Integer limit)
	{
		sessionFactory = hibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		 session.beginTransaction();
		  System.out.println("Session Completed");
		  List movies =  session.createQuery("from Movies").list();
		  
		  for(Iterator iterator = movies.iterator();iterator.hasNext();)
		  {
			  Movies movieiterator = (Movies)iterator.next();
			  Movies movie = new Movies();
			  movie.setDescription(movieiterator.getDescription());
			  movie.setDirectorName(movieiterator.getDirectorName());
			  movie.setFilmId(movieiterator.getFilmId());
			  movie.setLanguage(movieiterator.getLanguage());
			  movie.setRating(movieiterator.getRating());
			  movie.setReleaseYear(movieiterator.getReleaseYear());
			  movie.setTitle(movieiterator.getTitle());
			  movielist.add(movie);
		  }
		  System.out.println("Fetched Successfully");
		  System.out.println("Movie List is:"+ movielist.get(0).getTitle());
		  session.getTransaction().commit();
		  session.close();
		  sessionFactory.close();
		  System.out.println(this.movieList);
		  this.movieList.put("AllMovieData", movielist);
		  this.movieList.put("total",1000);
		  System.out.println("cdvdsycvsv"+this.movieList.get("AllMovieData"));
		  return this.movieList;
	}
	public  HashMap<String,Object>  addMovie(Movies movie) 
		{
		
			/*
			 * System.out.println(title); System.out.println(language); Movies movie = new
			 * Movies(); movie.setTitle(title); movie.setDescription(description);
			 * movie.setDirectorName(directorName); movie.setRating(rating);
			 * movie.setLanguage(language); movie.setReleaseYear(releaseYear);
			 * movie.setSpecialFeatures(specialFeatures);
			 * System.out.println("Data base is trying to open a session");
			 */
		
		try {
			sessionFactory = hibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			 session.beginTransaction();
			  System.out.println("Session Completed");
			  session.save(movie);
			  System.out.println("Inserted Successfully");
			  session.getTransaction().commit();
			  session.close();
			  sessionFactory.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("An Exception Occured" + e);
			//e.printStackTrace();
		}
		
		
		return  this.movieList;
		 
		
	}
	
	
	public HashMap<String,Object>  updateMovie(Movies movie)  
	{
	try {
		sessionFactory = hibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		/*
		 * Movies movie = (Movies)session.get(Movies.class, filmId);
		 * movie.setTitle(title); movie.setDescription(description);
		 * movie.setDirectorName(directorName); movie.setRating(rating);
		 * movie.setLanguage(language); movie.setReleaseYear(releaseYear);
		 * movie.setSpecialFeatures(specialFeatures);
		 */
		 session.update(movie);
		 
;		  System.out.println("updated Successfully");
		  session.getTransaction().commit();
		  System.out.println("Commited");
		  session.close();
		  sessionFactory.close();
		
		
	} 
	catch (Exception e) 
	{
		System.out.println("An exception Occured" + e);
	} 
	
	return this.movieList;
}
	
	public   HashMap<String,Object>  deleteMovie(int filmIds) 
	{
		System.out.println(filmIds);
		try {
			sessionFactory = hibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Movies movie = (Movies)session.get(Movies.class, filmIds);
			System.out.println(filmIds);
			Query query = session.createQuery("UPDATE Movies SET isDeleted = 1 WHERE film_id = :filmId");
			query.setInteger("filmId", filmIds);
			
			query.executeUpdate();
	;		  
			  session.getTransaction().commit();
			  session.close();
			  sessionFactory.close();
			  System.out.println("Deleted Successfully");
		} catch (Exception e) {
			e.printStackTrace();
			
		} 
		
		
		return this.movieList;
	}
	
}

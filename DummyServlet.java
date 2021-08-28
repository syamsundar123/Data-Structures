package com.higradius;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class dummyServlet
 */
@WebServlet("/dummyServlet")
public class DummyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DummyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		Connection con=Database.getCon();
		 Statement st = null;
		 ResultSet rs = null;
		String title = null;
		String description = null;
		String releaseYear = null;
		int language = 0;
		String director = null;
		String rating = null;
		String specialFeatures = null;
		 int filmId = -1;
		int start = Integer.parseInt(request.getParameter("start"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		System.out.println(start+"  "+limit);
		List<Response> responseList = new ArrayList<>();
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from film WHERE isDeleted = 0 LIMIT "+limit+" OFFSET "+start+";");// LIMIT "//+limit+" OFFSET "+start+";");
			
			
			System.out.println("Data Fetched Succesfully");
			
			while(rs.next()){
				Response movieResponse = new Response();
				
				
				
				title = rs.getString("title");
				//System.out.println(title);
				description = rs.getString("description");
				releaseYear = rs.getString("release_year");
				language = rs.getInt("language_id");
				director = rs.getString("director");
				rating = rs.getString("rating");
				specialFeatures = rs.getString("special_features");	
				filmId = rs.getInt("film_id");
				movieResponse.setTitle(title);
				movieResponse.setDescription(description);
				movieResponse.setReleaseYear(releaseYear);
				movieResponse.setLanguage(language);
				movieResponse.setSpecialFeatures(specialFeatures);
				movieResponse.setDirectorName(director);
				movieResponse.setRating(rating);
				movieResponse.setFilmId(filmId);
				responseList.add(movieResponse);
				
				
			}
			
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(responseList);
			json = "{'total':1001"+",'Items':"+json+"}";
			//response.setContentType("application/json");
			response.getWriter().write(json);
		
			
		
			}
		catch(Exception e)
		{
			System.out.println(e);
		}
	
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		String title=request.getParameter("title");
		String description=request.getParameter("description");
		String releaseYear= request.getParameter("releaseYear");
		int language = Integer.parseInt(request.getParameter("language"));
		String directorName=request.getParameter("directorName");
		String rating=request.getParameter("rating");
		String specialFeatures= request.getParameter("specialFeatures");
		
		Connection con = Database.getCon();
		Statement st = null;
		
		
		try{
			st = con.createStatement();
		
			//String Query = "INSERT INTO film(title,description,release_year,director,language_id) VALUES(" + "'"+  title + "'" +",'"+description+"','"+releaseYear+"','"+directorName+"',"+ 1 +" );";
			String Query = "INSERT INTO film(title,description,release_year,director,rating,special_features,language_id) VALUES(" + "'"+  title + "'" +",'"+description+"','"+releaseYear+"','"+directorName+"','"+rating+"','"+specialFeatures+ "',"+ language +" );";

			
			System.out.println(Query);
			
			st.execute(Query);
			
			System.out.print("Posted Succesfully");
		}
		catch(Exception e){
			System.out.print(e);
		}
		
	}
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		String title=request.getParameter("title");
		String description=request.getParameter("description");
		String releaseYear= request.getParameter("releaseYear");
		int language = 1;//Integer.parseInt(request.getParameter("language"));
		String directorName=request.getParameter("directorName");
		String rating="R";//request.getParameter("rating");
		String specialFeatures= "Deleted Scenes";//request.getParameter("specialFeatures");
		System.out.println(request.getParameter("filmId"));
		int filmId = Integer.parseInt(request.getParameter("filmId"));
		Connection con = Database.getCon();
		Statement st = null;
		
		
		try{
			st = con.createStatement();
			String Query = "UPDATE film SET title = " + "'" + title + "'," +"description = "+ "'"+ description + "',"+ "release_year = "+"'"+releaseYear+"',"+"language_id = " + language+ ","+"director = "+"'"+directorName+"',"+"rating = "+"'"+rating+"',"+"special_features ="+"'"+specialFeatures+"'"+"WHERE film_id = "+filmId+";";
			System.out.println(Query);
			
			st.execute(Query);
			
			System.out.print("Data Updated Succesfully");
		}
		catch(Exception e){
			System.out.print(e);
		}
		
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Connection con = Database.getCon();
		Statement st = null;
		String filmIds = request.getParameter("filmIds");
		
		
		
		try{
			st = con.createStatement();
		
			String Query = "UPDATE film SET isDeleted = 1 WHERE film_id in ("+filmIds+");";

			
			System.out.println(Query);
			
			st.execute(Query);
			
			System.out.print("Data Deleted Succesfully");
		}
		catch(Exception e){
			System.out.print(e);
		}
	}
	}



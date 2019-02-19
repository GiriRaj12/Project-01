package com.giri;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.*;

public class Check extends HttpServlet{
         
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
		 String userc="",passc="";
		 PrintWriter out=res.getWriter();
		 boolean flag=true;
		 HttpSession ses=req.getSession(false);
		 userc=(String) ses.getAttribute("User");
		 passc=(String) ses.getAttribute("Pass");
		 DatastoreService ds=DatastoreServiceFactory.getDatastoreService();
		 Query q=new Query("User").setFilter(CompositeFilterOperator.and(
				 new FilterPredicate("Username",FilterOperator.EQUAL,userc),
				 new FilterPredicate("Password",FilterOperator.EQUAL,passc)
				 ));
		 PreparedQuery pq=ds.prepare(q);
		 for(Entity et:pq.asIterable()) {
			 if(et.getProperty("Username").equals(userc)&&et.getProperty("Password").equals(passc)) {
				 flag=false;
			 }
		 }
		 if(flag) {
			 req.getRequestDispatcher("Validate.jsp").include(req,res);
			 out.println("<p align=\"center\">"+"Wrong"+" " +"Credentials"+"</p>");
		 }
		 else {
			 res.sendRedirect("Products.jsp");
		 }
		 
	}
}

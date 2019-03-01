package com.giri;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;

import com.google.appengine.api.datastore.*;
public class Register extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
		String username=req.getParameter("email_id");
	    String password =req.getParameter("password");
	    String name=req.getParameter("user_name");
	    PrintWriter out = res.getWriter();
	    boolean fl=Check_Null(out,username,password,name,req,res);
	    boolean flg=true;
	    DataStore ds=new DataStore();
	    if(fl) {
	      flg=ds.Check_DataStore(username,req,res,out);
	      }
	    if(flg) {
	    	ds.Add_in_DataStore(name,username,password);
            req.getRequestDispatcher("Validate.jsp").include(req, res);
		    out.println("Registration Successfull now login");
	    }
	}
	      	
	//This method is to check the input
     boolean Check_Null(PrintWriter out,String username,String password,String name,HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
		boolean fl=true;
		if(username.equals("")) {
	    	req.getRequestDispatcher("Register.jsp").include(req,res);
	    	out.println("<p align="+"\"center\">"+"User name not entered"+"<p>");
	    	fl=false;
	    }
	    if(password.contentEquals("")) {
	    	req.getRequestDispatcher("Register.jsp").include(req,res);
	    	out.println("<p align="+"\"center\">"+"Password cannot be blanck"+"<p>");
	    	fl=false;
	    }
	    if(name.contentEquals("")) {
	    	req.getRequestDispatcher("Register.jsp").include(req,res);
	    	out.println("<p align="+"\"center\">"+"Name cannot be blacnk"+"<p>");
	    	fl=false;
	    }
	    return fl;
	}
}
 //This class is to communicate with the DataStore
class DataStore{
	private static DatastoreService dt=DatastoreServiceFactory.getDatastoreService();
	//To check weather data is present or not
	boolean Check_DataStore(String username,HttpServletRequest req,HttpServletResponse res,PrintWriter out) throws ServletException, IOException {
	    Query q=new Query("User");
	    PreparedQuery pq=dt.prepare(q);
	    boolean flg=true;
	    for(Entity et:pq.asIterable()) {
	    	String user=(String) et.getProperty("Username");
	    	if(user.equals(username)) {
	    		req.getRequestDispatcher("Register.jsp").include(req,res);
	    		out.println("<p align=\"center\">"+"User"+" " +"Aldready"+" "+"exist"+"</p>");
	    		flg=false;
	    	}
         }
	    return flg;
	    }
	//Data Not present so adding this in Data Store
	void Add_in_DataStore(String name,String username,String password) {
	    Entity data=new Entity("User");
	    data.setUnindexedProperty("Name",name);
	    data.setProperty("Username", username);
	    data.setProperty("Password", password);
	    dt.put(data);
	       }
}

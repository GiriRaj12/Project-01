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
	    if(fl) {
	    DatastoreService dt=DatastoreServiceFactory.getDatastoreService();
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
	    if(flg) {
	    Entity data=new Entity("User");
	    data.setUnindexedProperty("Name",name);
	    data.setProperty("Username", username);
	    data.setProperty("Password", password);
	    dt.put(data);
	    req.getRequestDispatcher("Validate.jsp").include(req, res);
	    out.println("Registration Successfull now login");
	       }
	    }
	}
}

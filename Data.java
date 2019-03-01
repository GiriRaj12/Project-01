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
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;

public class Data extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
		HttpSession ses=req.getSession(false);
		boolean fl=Check_Null(req,res);
		if(fl) {
			String user=(String)ses.getAttribute("User");	
	    Checkout_plus_queue(user,res);
	        }
   }
	boolean Check_Null(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
		String Name=req.getParameter("Name")+"";
	    String Phone=req.getParameter("Phone")+"";
	    String Address=req.getParameter("Address")+"";
	    PrintWriter out = res.getWriter();
        boolean fl=true;
	    if(Name.equals("")) {
	    	req.getRequestDispatcher("Checout.html").include(req,res);
	    	out.println("<p align="+"\"center\">"+" Name not entered"+"<p>");
	    	fl=false;
	    }
	    if(Phone.contentEquals("")) {
	    	req.getRequestDispatcher("Checout.html").include(req,res);
	    	out.println("<p align="+"\"center\">"+"Phone Number cannot be blanck"+"<p>");
	    	fl=false;
	    }
	    if(Address.contentEquals("")) {
	    	req.getRequestDispatcher("Checout.html").include(req,res);
	    	out.println("<p align="+"\"center\">"+"Address cannot be blacnk"+"<p>");
	    	fl=false;
	    }
	    return fl;
	}
	void Checkout_plus_queue(String user,HttpServletResponse res) throws IOException {
		DatastoreService dt=DatastoreServiceFactory.getDatastoreService();
		for(int i=0;i<Add.list.size();i++) {
		Entity et=new Entity(user);
		et.setProperty("Product", Add.list.get(i));
		et.setProperty("Quantity",Add.quant.get(i));
		et.setProperty("Price",Add.prc.get(i));
		dt.put(et);
		Queue q=QueueFactory.getDefaultQueue();
	    q.add(TaskOptions.Builder.withUrl("/email").clearParams());
	    res.sendRedirect("Thankyou.jsp");
		  }
	}
}

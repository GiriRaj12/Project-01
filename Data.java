package com.giri;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.mail.MailService.Message;

public class Data extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException {
		String Name=req.getParameter("Name")+"";
	    String Phone=req.getParameter("Phone")+"";
	    String Address=req.getParameter("Address")+"";
	    PrintWriter out = res.getWriter();
        HttpSession ses=req.getSession(false);
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
	    if(fl) {
	    	Properties props = new Properties();
	    	Session session = Session.getDefaultInstance(props, null);

	    	try {
	    	  MimeMessage msg = new MimeMessage(session);
	    	  msg.setFrom(new InternetAddress("girirajsekar50@gmail.com", "Admin"));
	    	  msg.addRecipient(Message.RecipientType.TO,
	    	                   new InternetAddress((String)ses.getAttribute("User"), "User"));
	    	  msg.setSubject("Test");
	    	  msg.setText("Test");
	    	  Transport.send(msg);
	    	}
	    	catch(Exception e){
	    		e.printStackTrace();
	    	}
	String user=(String)ses.getAttribute("User");
	DatastoreService dt=DatastoreServiceFactory.getDatastoreService();
	for(int i=0;i<Add.list.size();i++) {
	Entity et=new Entity(user);
	et.setProperty("Product", Add.list.get(i));
	et.setProperty("Quantity",Add.quant.get(i));
	et.setProperty("Price",Add.prc.get(i));
	dt.put(et);
	Add.list.remove(i);
	Add.quant.remove(i);
	Add.prc.remove(i);
	} 
	Add.list.clear();
	Add.quant.clear();
	Add.prc.clear();
	
	res.sendRedirect("Thankyou.jsp");
	}
   }
}

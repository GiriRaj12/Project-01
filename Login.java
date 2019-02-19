package com.giri;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.Collections;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheFactory;
import javax.cache.CacheManager;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class Login extends HttpServlet {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		PrintWriter out=res.getWriter();
		 String user=req.getParameter("username");
		 String pass=req.getParameter("password");
		 boolean fl=true;
		 if(user.contentEquals("")) {
			 req.getRequestDispatcher("Validate.jsp").include(req, res);
			 out.println("<p align=\"center\">"+"Email ID"+" "+"Not"+" "+"given"+"</p>");
			 fl=false;
		 }
		 if(pass.contentEquals("")) {
			 req.getRequestDispatcher("Validate.jsp").include(req, res);
	        out.println("<p align=\"center\">"+"Password Not given"+"</p>");
	        fl=false;
		 }
		 Cache cac;
		 try {
			 CacheFactory cf=CacheManager.getInstance().getCacheFactory();
			 cac=cf.createCache(Collections.emptyMap());
			 cac.put("User", user);
			 cac.put("Pass",pass);
			 String us=(String)cac.get("User");
			 System.out.println(us);
		 }
		 catch(CacheException e) {
			 e.printStackTrace();
		 }
		 boolean flg=false;
		 if(fl) {
		 HttpSession ses=req.getSession();
		 ses.setAttribute("User", user);
		 ses.setAttribute("Pass", pass);
		 res.sendRedirect("Check");
		 flg=true;
		 }
		 if(!flg) {
			 req.getRequestDispatcher("Validate.jsp").include(req, res);
			 out.println("<p align=\"center\">"+"Wrong"+" " +"Credentials"+" "+"</p>");
		 }
	 }
}

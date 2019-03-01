package com.giri;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login extends HttpServlet {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		PrintWriter out = res.getWriter();
		String user = req.getParameter("username");
		String pass = req.getParameter("password");
		boolean fl =Check_Null(user,pass,out,req,res);
		if (fl) {
			HttpSession ses = req.getSession();
			ses.setAttribute("User", user);
			ses.setAttribute("Pass", pass);
			res.sendRedirect("Check");
		}
	}
	     
	boolean Check_Null(String user,String pass,PrintWriter out,HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
        boolean fl = true;
		if (user.contentEquals("")) {
			req.getRequestDispatcher("Validate.jsp").include(req, res);
			out.println("<p align=\"center\">" + "Email ID" + " " + "Not" + " " + "given" + "</p>");
			fl = false;
		}
		if (pass.contentEquals("")) {
			req.getRequestDispatcher("Validate.jsp").include(req, res);
			out.println("<p align=\"center\">" + "Password Not given" + "</p>");
			fl = false;
		}
		return fl;
	}
}

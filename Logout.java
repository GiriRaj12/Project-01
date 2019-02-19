package com.giri;

import java.io.IOException;

import javax.servlet.http.*;

public class Logout extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException {
		HttpSession ses=req.getSession();
		ses.invalidate();
		res.sendRedirect("Validate.jsp");
	}
}

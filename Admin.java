package com.giri;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

public class Admin extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost (HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
		String Admin_name=req.getParameter("admin_name");
		PrintWriter out= res.getWriter();
		if(Admin_name.equals("Giri")||Admin_name.equals("Lokesh")||Admin_name.equals("Saraswathy")) {
			DatastoreService dt=DatastoreServiceFactory.getDatastoreService();
			String product=req.getParameter("product_name");
			String quantity=req.getParameter("quantity");
			boolean flag=true;
			if(product.contentEquals("")) {
				req.getRequestDispatcher("Admin.html").include(req, res);
				out.println("Product Name cannot be empty");
				flag=false;
			}
			if(quantity.contentEquals("")) {
				req.getRequestDispatcher("Admin.html").include(req, res);
				out.println("Quantity is empty");
				flag=false;
			}
			if(flag) {
			Entity et =new Entity("Product");
			et.setProperty("AdminName",Admin_name);
			et.setProperty("ProductName", product);
			et.setProperty("Quantity", quantity);
			dt.put(et);
			req.getRequestDispatcher("Admin.html").include(req, res);
			out.println("Product Added Success fully");
			}
		}
		else {
			req.getRequestDispatcher("Admin.html").include(req, res);
			out.println("Sorry you dont have access !");
		}
	}
}

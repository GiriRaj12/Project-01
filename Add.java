package com.giri;

import java.util.*;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class Add extends HttpServlet {
	static List<String> list=new ArrayList<>(); 
    static List<Integer> quant=new ArrayList<>();
    static List<Integer> prc=new ArrayList<>();
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {   
		String quantity=req.getParameter("quantity");
		   //HttpSession ses=req.getSession(false);  
		   Cookie c[]=req.getCookies();
		      String item="";
		      for(Cookie ck:c) {
		    	  if(ck.getName().contentEquals("item")) {
		    		  item=ck.getValue();
		    	  }
		      }
		      boolean flag=true;
		      if(list.size()!=0) {
		    	  for(int i=0;i<list.size();i++) {
		    		  if(item.contentEquals(list.get(i))) {
		    			  int x=(quant.get(i)+Integer.parseInt(quantity));
		    		    quant.set(i,x);
		    		    flag=false;
		    		  }
		    	  }
		      }
		      String price="";
		      switch (item){
		      case "Apple":price="60";
		      break;
		      case "Orange":price="100";
		      break;
		      case "Banana":price="30";
		      break;
		      case "Kiwi":price="80";
		      break;
		      case "Mango":price="50";
		      break;
		      case "Cabbage":price="70";
		      break;
		      case "Carrot":price="50";
		      break;
		      case "Beetroot":price="40";
		      break;
		      case "Broccoli":price="40";
		      break;
		      case "Brinjal":price="30";
		      break;
		      case "Tulsi":price="20";
		      break;
		      case  "Barks":price="100";
		      break;
		      }
		      PrintWriter out=res.getWriter();
		      int sum=0;
		      if(flag) {
               list.add(item);
               quant.add(Integer.parseInt(quantity));
               prc.add(Integer.parseInt(price));
                }
//		      ses.setAttribute("item",item);
//		      ses.setAttribute("quantity", quantity);
//		      ses.setAttribute("price", price);
		      out.println("<p align="+"center"+">"+"Prduct"+"--------"+"Quantity"+"-------"+"Price"+"</p>");
               for(int i=0;i<list.size();i++) {
            	   out.println("<p align="+"center"+">"+list.get(i)+"--------"+quant.get(i)+"-------"+(quant.get(i)*(prc.get(i)))+"</p>");
                     sum=sum+(quant.get(i)*(prc.get(i)));
               }
               
			   out.println("<p align="+"center"+">"+"----------------------------------------------"+"</p>");
			   out.println("<p align="+"center"+">"+"-----------------------------"+"|Total: "+sum+"</p>");
			   out.println("<p align="+"center"+"><a href="+"\"Checout.html\""+">"+"Checkout"+"</a></p>");
		       out.println("<p align="+"center"+"><a href="+"\"Products.jsp\""+">"+" Addmore"+"</a></p>");
		 		
	}
}

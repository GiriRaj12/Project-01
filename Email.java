package com.giri;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Email extends HttpServlet {
   static String order_day="";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    String Creation_of_String() {
    	StringBuffer bf=new StringBuffer();
		bf.append("---------- Organic Store Bill  ------------");
		bf.append(System.getProperty("line.separator"));
		bf.append("Products ---------Quantity---------- Price");
		for(int i=0;i<Add.list.size();i++) {
			bf.append(System.getProperty("line.separator"));
			bf.append(Add.list.get(i)+"          "+Add.quant.get(i)+"kg            Rs:"+Add.prc.get(i));	
		}
		bf.append(System.getProperty("line.separator"));
		bf.append("------------------------------------------");
		bf.append(System.getProperty("line.separator"));
		bf.append("--------------------------- Totoal: Rs:"+Add.total+"/-");
		return bf.toString();
    }
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Properties prop=new Properties();
		Session session=Session.getDefaultInstance(prop,null);
		PrintWriter out=res.getWriter();
		String bill=Creation_of_String();
        Date dt=new Date();
        order_day=dt.toString();
		try {
			Message msg=new MimeMessage(session);
			msg.setFrom(new InternetAddress("giriraj.sekar@anywhere.co","Giriraj"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress("girirajsekar50@gmail.com","Coustomer"));
			msg.setSubject("Bill - Orgaic Store");
			msg.setText(bill);
			Transport.send(msg);
     		Add.list.clear();
			Add.prc.clear();
			Add.quant.clear();
			Add.total=0;
		}
		catch(Exception e) {
		 out.println("<p align="+"center"+">"+"Email ID is not proper"+"</p>");
		 out.println("<p align="+"center"+"><a href="+"\"Products.jsp\""+">"+"Home"+"</a></p>");
		}
		
	}
}

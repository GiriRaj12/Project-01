package com.giri;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
interface X{
static void method() {
	
}
}
public class Schedule_Email extends HttpServlet{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws UnsupportedEncodingException {
    	String ordered_day[]=Email.order_day.split("");
    	String dt=new Date().toString();
    	String present_day[]=dt.split(" ");
    	String str=(Integer.parseInt(present_day[2])-3)+"";
    	if((str.contentEquals(ordered_day[2]))&&(present_day[1].contentEquals(ordered_day[1]))){
    		try {	
    		Properties prop=new Properties();
    			Session session=Session.getDefaultInstance(prop,null);
    			Message msg=new MimeMessage(session);
    			msg.setFrom(new InternetAddress("giriraj.sekar@anywhere.co","Admin"));
    		msg.setRecipient(Message.RecipientType.TO, new InternetAddress("girirajsekar50@gmail.com","Coustomer"));
    		msg.setSubject("Organic Store- Delivey Scheduled");
    		msg.setText("Thank you for shopping with us Your order will be delivered Today"+present_day[1]+present_day[2]);
           Transport.send(msg);
    		} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    	 }
   }
}

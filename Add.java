package com.giri;

import java.util.*;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Add extends HttpServlet {
	static List<String> list = new ArrayList<>();
	static List<Integer> quant = new ArrayList<>();
	static List<Integer> prc = new ArrayList<>();
	static int total = 0;
	boolean flag;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession ses = req.getSession(false);
		String quantity = req.getParameter("quantity");
		String item = (String) ses.getAttribute("item");
		flag = Check_items_present(item, quantity);
		String price = Price_List(item);
		if (flag) {
			list.add(item);
			quant.add(Integer.parseInt(quantity));
			prc.add(Integer.parseInt(price));
		}
		display(res);
	}

	String Price_List(String item) {
		String price = "";
		switch (item) {
		case "Apple":
			price = "60";
			break;
		case "Orange":
			price = "100";
			break;
		case "Banana":
			price = "30";
			break;
		case "Kiwi":
			price = "80";
			break;
		case "Mango":
			price = "50";
			break;
		case "Cabbage":
			price = "70";
			break;
		case "Carrot":
			price = "50";
			break;
		case "Beetroot":
			price = "40";
			break;
		case "Broccoli":
			price = "40";
			break;
		case "Brinjal":
			price = "30";
			break;
		case "Tulsi":
			price = "20";
			break;
		case "Barks":
			price = "100";
			break;
		}
		return price;
	}

	boolean Check_items_present(String item, String quantity) {
		flag = true;
		if (list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				if (item.contentEquals(list.get(i))) {
					int x = (quant.get(i) + Integer.parseInt(quantity));
					quant.set(i, x);
					flag = false;
				}
			}
		}
		return flag;
	}

	void display(HttpServletResponse res) throws IOException {
		int sum = 0;
		PrintWriter out = res.getWriter();
		out.println("<p align=" + "center" + ">" + "Prduct" + "--------" + "Quantity" + "-------" + "Price" + "</p>");
		for (int i = 0; i < list.size(); i++) {
			out.println("<p align=" + "center" + ">" + list.get(i) + "--------" + quant.get(i) + "kg-------Rs:"
					+ (quant.get(i) * (prc.get(i))) + "</p>");
			sum = sum + (quant.get(i) * (prc.get(i)));
		}
		total = sum;
		out.println("<p align=" + "center" + ">" + "----------------------------------------------" + "</p>");
		out.println("<p align=" + "center" + ">" + "-----------------------------" + "|Total: Rs:" + sum + "</p>");
		out.println("<p align=" + "center" + "><a href=" + "\"Checout.html\"" + ">" + "Checkout" + "</a></p>");
		out.println("<p align=" + "center" + "><a href=" + "\"Products.jsp\"" + ">" + " Addmore" + "</a></p>");

	}
}

package com.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.couponDAO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import oracle.jdbc.driver.DBConversion;

/**
 * Servlet implementation class CouponCon
 */
@WebServlet("/CouponCon")
public class CouponCon extends HttpServlet {
 
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   request.setCharacterEncoding("UTF-8");
		   
		   String returns = "";
		   String type = request.getParameter("type");
		   String vision = request.getParameter("vision_write");
		   response.setContentType("text/html; charset=utf-8");
		   PrintWriter out = response.getWriter();
		   
		   if (type == null) {
			      return;
			   }else if (type.equals("vision_write")) {
			      System.out.println("값을받았습니다."+vision);
			      out.println("값을 돌려줍니다"+vision);
			      
			    }
		
		
	}

}

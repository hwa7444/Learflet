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

import com.dao.AnalDAO;
import com.dao.AnalVO;
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
		   String id = request.getParameter("id");
		   String age = request.getParameter("age");
		   String gender = request.getParameter("gender");
		   String interest1 = request.getParameter("interest1");
		   String interest2 = request.getParameter("interest2");
		   String interest3 = request.getParameter("interest3");
		   
		   AnalVO vo = new AnalVO(id,age,gender,interest1,interest2,interest3);
		   AnalDAO dao = AnalDAO.getInstance();
		   
		   
		   //b = URLDecoder.decode(b,"utf-8");
		   
		   response.setContentType("text/html; charset=utf-8");
		   PrintWriter out = response.getWriter();
		   
		   
		   
		   try {
			   if (id!=null) {
				   int cnt = dao.insertData(id, age, gender, interest1, interest2, interest3);
				   
				   if(cnt>0) {
					   System.out.println("데이터 삽입 성공");
				   }else {
					   System.out.println("데이터 삽입 실패");
				   }
			}
			   
			   
		   }catch(Exception e) {
			   e.printStackTrace();
		   }

		  
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   if (type == null) {
			      return;
			   }else if (type.equals("vision_write")) {
			    //  System.out.println("값을받았습니다."+vision);
			      out.println("값을 돌려줍니다"+vision);
			      
			    }
		
		
	}

}

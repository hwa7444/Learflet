package com.service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.couponDAO;

/**
 * Servlet implementation class couponCon
 */
@WebServlet("/couponCon")
public class couponCon extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("text/html); charset=utf-8");
		
		
		String vision = request.getParameter("vision_write");
		String coupon1 = request.getParameter("coupon1");
		String coupon2 = request.getParameter("coupon2");
		String coupon3 = request.getParameter("coupon3");
		
		//CouponVO vo = new CouponVO();
		couponDAO dao = couponDAO.getInstance();
		
		
		
	}

}

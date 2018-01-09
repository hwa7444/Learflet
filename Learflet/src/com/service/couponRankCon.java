package com.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.couponDAO;
import com.dao.couponrankVO;

/**
 * Servlet implementation class analysisdata
 */
@WebServlet("/couponRankCon")
public class couponRankCon extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("text/html; charset=utf-8");
		//PrintWriter out = response.getWriter();
		couponDAO dao = couponDAO.getInstance();
		ArrayList<couponrankVO> list;
		try {
			
			list = dao.selectAll();
			System.out.println(list.get(0).getCoupon1());//쿠폰순위불러오기
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

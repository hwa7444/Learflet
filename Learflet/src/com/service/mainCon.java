package com.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.AnalDAO;
import com.dao.AnalVO;
import com.dao.couponDAO;
import com.dao.couponrankVO;

import pythonread.pythonRun2;

/**
 * Servlet implementation class mainCon
 */
@WebServlet("/mainCon")
public class mainCon extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		pythonRun2 py = new pythonRun2();
		py.run();
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("text/html; charset=utf-8");
		String log = request.getParameter("join");
		System.out.println("로그값"+log);
		String login = request.getParameter("login");
		System.out.println("로그인아이디"+login);
		
		
		//if (login!=null) {
			if(login!=null) {
				String id = request.getParameter("id");
				System.out.println("분석페이지");
				//request.getParameter("log");
				couponDAO dao = couponDAO.getInstance();
				ArrayList<couponrankVO> list;
				
				
				try {
					list = dao.selectAll();
					
					System.out.println(list.get(0).getCoupon1());
					System.out.println(list.get(0).getCoupon2());
					out.println(list.get(0).getCoupon1() + "," + list.get(0).getCoupon2() + ","
							+ list.get(0).getCoupon3() + "," + list.get(0).getCoupon4());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// 로그인 끝
			}
		//}
		
		
		
		
		if (log != null) {
			if (log.equals("join")) {// 회원가입 시작
				String id = request.getParameter("joinid");
				String age = request.getParameter("age");
				String gender = request.getParameter("gender");
				String interest1 = request.getParameter("interest1");
				String interest2 = request.getParameter("interest2");
				String interest3 = request.getParameter("interest3");
				AnalVO vo = new AnalVO(id, age, gender, interest1, interest2, interest3);
				AnalDAO dao = AnalDAO.getInstance();

				try {
					if (id != null) {

						int cnt = dao.insertData(id, age, gender, interest1, interest2, interest3);
						System.out.println(age);
						if (cnt > 0) {
							System.out.println("데이터 삽입 성공");

						} else {
							System.out.println("데이터 삽입 실패");
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			} // 회원가입 끝
			else if (log.equals("analysis")) {// 분석시작
				

			} // 분석 끝
			

		}

	}

}

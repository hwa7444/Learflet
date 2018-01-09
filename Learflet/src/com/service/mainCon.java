package com.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.AnalDAO;
import com.dao.AnalVO;

/**
 * Servlet implementation class mainCon
 */
@WebServlet("/mainCon")
public class mainCon extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("text/html; charset=utf-8");
		
		String log = request.getParameter("log");
		if (log!=null) {
			if (log.equals("login")) {//�α��� ����
				String id = request.getParameter("id");
				
				//�α��� ��
			}else if(log.equals("join")) {//ȸ������ ����
				
				String id = request.getParameter("id");
				String age = request.getParameter("age");
				String gender = request.getParameter("gender");
				String interest1 = request.getParameter("interest1");
				String interest2 = request.getParameter("interest2");
				String interest3 = request.getParameter("interest3");
				AnalVO vo = new AnalVO(id, age, gender, interest1, interest2, interest3);
				AnalDAO dao = AnalDAO.getInstance();
				
				try {
					   if (id!=null) {

						   int cnt = dao.insertData(id, age, gender, interest1, interest2, interest3);
						   System.out.println(age);
						   if(cnt>0) {
							   System.out.println("������ ���� ����");
							   
						   }else {
							   System.out.println("������ ���� ����");
						   }
					}
					   
					   
				   }catch(Exception e) {
					   e.printStackTrace();
				   }

				 
			}//ȸ������ ��
			
		}
		
		
	}

}

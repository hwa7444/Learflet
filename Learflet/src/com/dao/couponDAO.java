package com.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

public class couponDAO {
	private Connection conn;
	private ResultSet rs;
	private PreparedStatement pst;
	private int cnt;

	private static couponDAO instance = new couponDAO();

	public static couponDAO getInstance() {
		return instance;
	}

	public void getConn() throws Exception {

		// 1.JDBC 업로드
		InputStream in = (this.getClass().getResourceAsStream("../../../../db.properties"));
		// 현재 작업하고 있는 자바파일의 클래스파일을 기준으로 db.properties를 읽어오겠다.

		Properties p = new Properties();
		p.load(in);

		String url = p.getProperty("dburl");
		String dbid = p.getProperty("dbid");
		String dbpw = p.getProperty("dbpw");
		Class.forName(p.getProperty("dbclass"));

		// 동적로딩

		conn = DriverManager.getConnection(url, dbid, dbpw);
		System.out.println("DB연결완료");

	}// 연결기능

	public void close() throws Exception {
		if (rs != null)
			rs.close(); // rs, pst, conn .close예외 처리 해줘야 함
		if (pst != null)
			pst.close();
		if (conn != null)
			conn.close();
	}// 종료기능

	public int uploadFile(String title, String nick, String fileName, String content, int checkD) throws Exception {

		getConn();
		System.out.println("DAO 동작");

		// sql작성
		pst = conn.prepareStatement("insert into bulletin values(b_num.nextval,?,?,?,?,to_char(sysdate,'YYYY-MM-DD'),?)");
		System.out.println("DAO 동작 upload동작");
		pst.setString(1, title);
		System.out.println("DAO 동작 upload동작이름");
		pst.setString(2, nick);
		System.out.println("DAO 동작 upload동작아이디");
		pst.setString(3, fileName);
		System.out.println("DAO 동작 upload동작파일");
		pst.setString(4, content);
		System.out.println("DAO 동작 upload내용");
		pst.setInt(5, checkD);
		System.out.println("DAO 동작 upload체크");

		cnt = pst.executeUpdate();
		close();
		return cnt;
	}

	public void insert() {
		
		
	}
}


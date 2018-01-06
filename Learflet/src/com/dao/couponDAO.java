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

		// 1.JDBC ���ε�
		InputStream in = (this.getClass().getResourceAsStream("../../../../db.properties"));
		// ���� �۾��ϰ� �ִ� �ڹ������� Ŭ���������� �������� db.properties�� �о���ڴ�.

		Properties p = new Properties();
		p.load(in);

		String url = p.getProperty("dburl");
		String dbid = p.getProperty("dbid");
		String dbpw = p.getProperty("dbpw");
		Class.forName(p.getProperty("dbclass"));

		// �����ε�

		conn = DriverManager.getConnection(url, dbid, dbpw);
		System.out.println("DB����Ϸ�");

	}// ������

	public void close() throws Exception {
		if (rs != null)
			rs.close(); // rs, pst, conn .close���� ó�� ����� ��
		if (pst != null)
			pst.close();
		if (conn != null)
			conn.close();
	}// ������

	public int uploadFile(String title, String nick, String fileName, String content, int checkD) throws Exception {

		getConn();
		System.out.println("DAO ����");

		// sql�ۼ�
		pst = conn.prepareStatement("insert into bulletin values(b_num.nextval,?,?,?,?,to_char(sysdate,'YYYY-MM-DD'),?)");
		System.out.println("DAO ���� upload����");
		pst.setString(1, title);
		System.out.println("DAO ���� upload�����̸�");
		pst.setString(2, nick);
		System.out.println("DAO ���� upload���۾��̵�");
		pst.setString(3, fileName);
		System.out.println("DAO ���� upload��������");
		pst.setString(4, content);
		System.out.println("DAO ���� upload����");
		pst.setInt(5, checkD);
		System.out.println("DAO ���� uploadüũ");

		cnt = pst.executeUpdate();
		close();
		return cnt;
	}

	public void insert() {
		
		
	}
}


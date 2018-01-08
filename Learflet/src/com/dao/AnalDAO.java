package com.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

public class AnalDAO {

	private Connection conn;
	private ResultSet rs;
	private PreparedStatement pst;
	private int cnt;

	private static couponDAO instance = new couponDAO();

	public static couponDAO getInstance() {
		return instance;
	}

	public void getConn() throws Exception {

		InputStream in = (this.getClass().getResourceAsStream("db.properties"));

		Properties p = new Properties();
		p.load(in);

		String url = p.getProperty("dburl");
		String dbid = p.getProperty("dbid");
		String dbpw = p.getProperty("dbpw");
		Class.forName(p.getProperty("dbclass"));

		// �����ε�

		conn = DriverManager.getConnection(url, dbid, dbpw);
		System.out.println("DB����Ϸ�");

	}

	public void close() throws Exception {
		if (rs != null)
			rs.close();
		if (pst != null)
			pst.close();
		if (conn != null)
			conn.close();
	}

	public int insertData(String id, String age, String gender, String interest1, String interest2, String interest3)
			throws Exception {
		getConn();
		pst = conn.prepareStatement("insert into analdata values(?,?,?,?,?,?,?,?,?)");
		pst.setString(1, id);
		pst.setString(2, age);
		pst.setString(3, gender);
		pst.setString(4, interest1);
		pst.setString(5, interest2);
		pst.setString(6, interest3);
		pst.setString(7, "None");
		pst.setString(8, "None");
		pst.setString(9, "None");

		cnt = pst.executeUpdate();
		close();
		return cnt;
	}

	public int updateData(String id, String usedCoupon) throws Exception {// ����� ���� ����ֱ�
		getConn();

		pst = conn.prepareStatement("select coupon1 from analdata where id = ?");
		pst.setString(1, id);
		rs = pst.executeQuery();

		String c1 = "";

		if (rs.next()) {
			c1 = rs.getString(1);
			System.out.println("b" + c1);
		} else {
			System.out.println("����");
		}

		// ���� ����� �� ������ coupon1 �÷��� �� ����ְ�
		// ���� ��� ���� ������ coupon2�� �� ����ֱ�
		if (c1 == null) {// ���� ��� ������
			pst = conn.prepareStatement("update analdata set coupon1 = ? where id = ?");

			pst.setString(2, id);
			pst.setString(1, usedCoupon);
			cnt = pst.executeUpdate();
			close();
			return cnt;

		} else {// coupon1�� �� ������

			// �ٽ� coupon2�� �� �ִ��� Ȯ��
			// coupon2�� �� ������ ����ְ�
			// coupon2�� �� �̹� ������ coupon3�� �� ����
			pst = conn.prepareStatement("select coupon2 from analdata where id = ?");
			pst.setString(1, id);
			rs = pst.executeQuery();

			String c2 = "";

			if (rs.next()) {
				c2 = rs.getString(1);
			}

			if (c2 == null) {// coupon2�� �� ���ٸ�

				pst = conn.prepareStatement("update analdata set coupon2 = ? where id = ?");
				pst.setString(2, id);
				pst.setString(1, usedCoupon);

				cnt = pst.executeUpdate();
				close();
				return cnt;
			} else {// coupon2�� �� �ִٸ� �ٽ� coupon3�� �� �ִ��� Ȯ��
					// coupon3�� �� ������ �� ����ְ�
					// coupon3�� �� ���� �� 3 ->2, 2 ->1���� ������Ʈ ��Ų��
				pst = conn.prepareStatement("select coupon3 from analdata where id = ?");
				pst.setString(1, id);
				rs = pst.executeQuery();
				String c3 = "";
				if (rs.next()) {
					c3 = rs.getString(1);
				}

				if (c3 == null) {
					pst = conn.prepareStatement("update analdata set coupon3 = ? where id = ?");
					pst.setString(2, id);
					pst.setString(1, usedCoupon);

					cnt = pst.executeUpdate();
					close();
					return cnt;
				} else {
					pst = conn
							.prepareStatement("update analdata set coupon1 = ?, coupon2 =?, coupon3 = ? where id = ?");
					pst.setString(1, c2);
					pst.setString(2, c3);
					pst.setString(3, usedCoupon);
					pst.setString(4, id);

					cnt = pst.executeUpdate();
					close();
					return cnt;

				}

			}

		}

	}

}

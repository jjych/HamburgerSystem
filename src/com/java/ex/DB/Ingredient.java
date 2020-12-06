package com.java.ex.DB;

import java.sql.*;

import javax.swing.JLabel;

import com.java.ex.DB.*;

public class Ingredient {
	static String driver = "org.mariadb.jdbc.Driver";
	static String url = "jdbc:mariadb://Localhost:3307/gimal2020";          // lecture 대신 자신의 데이터베이스 테이블이름 넣을것. 포트번호 설정한값넣기
	static String uid = "root";                                        // 마리아DB 아이디
	static String pwd = "1234";                                        // 마리아DB 비밀번호

	
	public static String Customer() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		
		String query1 = "select * from ingredient";
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,uid,pwd);
			stmt = con.createStatement();
			rs = stmt.executeQuery(query1);
			rsmd = rs.getMetaData();

			while(rs.next()) {
				String c = rs.getString(3);
				JLabel w = new JLabel(c + "개");
				w.setBounds(300,32,60,20);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(stmt != null) {
					stmt.close();
				}
				if(con != null) {
					con.close();
				}
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return null;

	}
	
	public Ingredient2 getstock(){//서버에서 재고데이터 가져오기
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		String sql = "select * from ingredient" ;
		Connection con = null;
		Ingredient2 stock=null;
		try {
			con = DriverManager.getConnection(url,uid,pwd);
			pstmt = con.prepareStatement(sql) ; 
			
			rs = pstmt.executeQuery() ;
			
			while(rs.next()){
				stock = new Ingredient2() ;
				
				stock.setBread(rs.getInt("참깨빵"));
				stock.setBulgogi(rs.getInt("불고기패티"));
				stock.setYangsangchu(rs.getInt("양상추"));
				stock.setPicle(rs.getInt("피클"));
				stock.setSoce(rs.getInt("특별한소스"));
				stock.setGamja(rs.getInt("감자"));
				stock.setYangpa(rs.getInt("양파"));
				stock.setChiken(rs.getInt("치킨패티"));
				stock.setOjing(rs.getInt("오징어패티"));
				stock.setEgg(rs.getInt("달걀"));
				stock.setBaken(rs.getInt("베이컨"));
				stock.setSicyounyu(rs.getInt("식용유"));
				stock.setCheeze(rs.getInt("치즈"));
				stock.setMilk(rs.getInt("우유"));
				stock.setSider(rs.getInt("사이다"));
				stock.setCola(rs.getInt("콜라"));
				stock.setIce(rs.getInt("얼음"));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally{
			try {
				if(rs != null) {rs.close(); }
				if(pstmt != null) {pstmt.close(); }
				if(con != null) {con.close();}
			} catch (Exception e2) {
				e2.printStackTrace(); 
			}
		}
		return stock ;
	}//getstock

}

	
//	public static void Customer(int Count) {
//		try {
//			Connection con = getConnection();
//			String query1 = "update into ingredient (Count) VALUE" + "'" + Count + "'";
//			PreparedStatement update = con.prepareStatement(query1);
//			update.executeUpdate();
//		}catch(Exception e) {
//			System.out.println(e.getMessage());
//		}
//	}
//	
//	public static void CountTable(int Count) {
//		try {
//			Connection con = getConnection();
//			Statement stmt = null;
//			ResultSet rs = null;
//			ResultSetMetaData rsmd = null;
//			String query1 = "select * from ingredient";
//			stmt = con.createStatement();
//			rs = stmt.executeQuery(query1);
//			rsmd = rs.getMetaData();
//			
//			while(rs.next()) {
//				String c = rs.getString("Count");
//				
//				System.out.println(c + "개");
//			}
//		}catch(Exception e) {
//			System.out.println(e.getMessage());
//		}
//	}
//	
//	public static Connection getConnection() {
//		try {
//			String driver = "org.mariadb.jdbc.Driver";
//			String url = "jdbc:mariadb://Localhost:3307/test";          // lecture 대신 자신의 데이터베이스 테이블이름 넣을것. 포트번호 설정한값넣기
//			String uid = "root";                                        // 마리아DB 아이디
//			String pwd = "1234";                                        // 마리아DB 비밀번호
//			
//			Class.forName(driver);
//			Connection con = DriverManager.getConnection(url,uid,pwd);
//			return con;
//		}catch(Exception e) {
//			System.out.println(e.getMessage());
//			return null;
//		}
//	}
//
//}

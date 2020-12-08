package com.java.ex;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import com.java.ex.*;
import com.java.ex.Event.*;
import com.java.ex.main.MainClass;

public class ForthWindow extends JFrame{
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	ResultSetMetaData rsmd = null; // resultset 에 대한 컬럼의 속성이나 타입 정보를 사용한다.
	
	public static Connection getConnection() {                // 식품 DB연동
		try {
			String driver = "org.mariadb.jdbc.Driver";
			String url = "jdbc:mariadb://Localhost:3307/gimal2020";
			String uid = "root";
			String upw = "1234";
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url,uid,upw);
			return con;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		
	}
	
	public static String[][] getCustomers(){                  // 식품DB 불러오기
		try {
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("Select * From food");
			ResultSet results = statement.executeQuery();
			ArrayList<String[]> list = new ArrayList<String[]>();
			while(results.next()) {
				list.add(new String[]{
					results.getString("SellFood"),
					results.getString("SellCount")
				});
			}
			String[][] arr = new String[list.size()][3];
			return list.toArray(arr);
			}catch(Exception e) {
				System.out.println(e.getMessage());
				return null;
		   }
			
	}
	
	public void conn() {                       // 잔액 DB연동
		// 오라클 드라이버 선언
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			// 오라클 DB연결
			con = DriverManager.getConnection("jdbc:mariadb://Localhost:3307/gimal2020",
					"root", "1234");
		} catch (SQLException ee) {
			ee.printStackTrace();
		}
	}
	public ForthWindow() {
		conn();
		Container ct = getContentPane();
		setTitle("잔액조회 및 매출품목");
		setSize(400,500);
		
		JLabel title = new JLabel("잔액조회 및 매출품목");
		title.setHorizontalAlignment(JLabel.CENTER);
		
		JPanel jp1 = new JPanel();
		jp1.setLayout(null);
		
		JLabel janAct = new JLabel("잔액 : ");
		janAct.setBounds(100,1,50,50);
		jp1.add(janAct);
		
		JLabel lb = new JLabel();
		lb.setBounds(180,1,50,50);
		lb.setFont(new Font("", Font.BOLD, 15));
		jp1.add(lb);
		try {
			pstmt = con.prepareStatement("select * from bank"); // SQL 질의
			rs = pstmt.executeQuery(); // 쿼리 실행결과를 rs에 저장한다.
	
			while (rs.next()) {
				lb.setText(rs.getString(1));
					
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		JLabel lbone = new JLabel("원");
		lbone.setBounds(250,1,50,50);
		jp1.add(lbone);
		
		JLabel macul = new JLabel("매출 품목");
		macul.setBounds(170,30,100,50);
		jp1.add(macul);
		
		JLabel food = new JLabel("상품목록");
		food.setBounds(100,60,100,50);
		jp1.add(food);
		
		JLabel pamac = new JLabel("판매량");
		pamac.setBounds(240,60,100,50);
		jp1.add(pamac);
		
		String[][] data = ForthWindow.getCustomers();        // DB연동된 table
		
		String[] headers = new String[] {"매출 품목","판매량"};
		JTable table = new JTable(data,headers);
		table.setBounds(70,100,250,190);
		jp1.add(table);
		
		JButton Lobi = new JButton("로비창가기");
		Lobi.setBounds(220,350,150,50);
		jp1.add(Lobi);
		
		Lobi.addActionListener(new MainClass(){
			public void actionPerformed(ActionEvent e) {         // 로비창으로 이동
				FirstWindow W1 = new FirstWindow();
		         W1.setVisible(true);
		         dispose();
				}
			});
		
		ct.add(title, BorderLayout.NORTH);
		ct.add(jp1);
		
		setBounds(1,1,400,500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}

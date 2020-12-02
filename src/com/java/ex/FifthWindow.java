package com.java.ex;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

import com.java.ex.DB.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.java.ex.main.MainClass;


public class FifthWindow extends JFrame{
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	ResultSetMetaData rsmd = null; // resultset 에 대한 컬럼의 속성이나 타입 정보를 사용한다.
	
	public static Connection getIngre() {                // 재료 DB연동
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
	
	public static String[][] getIngredient(){                  // 재료DB 불러오기
		try {
			Connection con = getIngre();
			PreparedStatement statement = con.prepareStatement("Select * From ingredient");
			ResultSet results = statement.executeQuery();
			ArrayList<String[]> list = new ArrayList<String[]>();
			while(results.next()) {
				list.add(new String[]{
					results.getString("IngredientName"),
					results.getString("Count")
				});
			}
			String[][] arr = new String[list.size()][3];
			return list.toArray(arr);
			}catch(Exception e) {
				System.out.println(e.getMessage());
				return null;
		   }
			
	}
	
	public FifthWindow() {
		setTitle("식재료 창고");
		setSize(450,600);
		Container ct = getContentPane();
		JLabel title = new JLabel("식재료 창고");
		title.setHorizontalAlignment(JLabel.CENTER);
		
		JPanel jp1 = new JPanel();
		jp1.setLayout(null);
		
		JLabel sickjaro = new JLabel("식재료가격표");
		sickjaro.setBounds(50,1,100,20);
		jp1.add(sickjaro);
		
		JLabel mocloc = new JLabel("<html>참깨빵 - 500원<br><br>"
				                   + "불고기패티 - 800원<br><br>"
				                   + "양상추 - 300원<br><br>"
				                   + "피클 - 400원<br><br>"
				                   + "특별한소스 - 200원<br><br>"
				                   + "감자 - 600원<br><br>"
				                   + "양파 - 500원<br><br>"
				                   + "치킨패티 - 1000원<br><br>"
				                   + "오징어패티 - 900원<br><br>"
				                   + "달걀 - 300원<br><br>"
				                   + "베이컨 - 800원<br><br>"
				                   + "식용유 - 1200원<br><br>"
				                   + "치즈 - 500원<br><br>"
				                   + "우유 - 600원<br><br>"
				                   + "사이다 - 500원<br><br>"
				                   + "콜라 - 500원<br><br>"
				                   + "얼음 - 300원<br></html>");
		mocloc.setFont(new Font("굴림",Font.BOLD,12));
		mocloc.setBounds(40,30,300,500);
		jp1.add(mocloc);
		
		JLabel Panmarang = new JLabel("식재료갯수");
		Panmarang.setBounds(320,100,80,20);
		jp1.add(Panmarang);
		
        String[][] dataJaro = FifthWindow.getIngredient();            // DB연동된 테이블
		String[] ColName = new String[] {"재료","갯수"};
		DefaultTableModel model = new DefaultTableModel(dataJaro,ColName);
		JTable ta = new JTable(model);
		ta.setFont(new Font("Sanserif", Font.BOLD,12));
		ta.setBounds(280,120,150,270);
		jp1.add(ta);
		
		JButton btn1 = new JButton("추가");                      // 참깨빵 버튼
		btn1.setFont(new Font("굴림",Font.BOLD,10));
		btn1.setBounds(200,32,60,20);
		jp1.add(btn1);
		
		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model2 = (DefaultTableModel)ta.getModel();
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("Update ingredient set Count = Count + 1 where IngredientName = '참깨빵'");
					ResultSet results = statement.executeQuery();
				
					}catch(Exception e) {
						System.out.println(e.getMessage());
				   }
				try {
					Connection con = getIngre();
					PreparedStatement statement2 = con.prepareStatement("Update bank set Money = Money - 500");
					ResultSet results2 = statement2.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
				model2.setRowCount(0);                           // 테이블을 싹다 지움
				try {                                                                                      // 다시 입력받음
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("Select * From ingredient");
					ResultSet results = statement.executeQuery();
					ArrayList<String[]> list = new ArrayList<String[]>();
					while(results.next()) {
						model.addRow(new Object[] {results.getString("IngredientName"),results.getString("Count")});
					}
					}catch(Exception e) {
						System.out.println(e.getMessage());
				   }
				
			}
		});
		
		JButton btn2 = new JButton("추가");                      // 불고기패티 버튼
		btn2.setFont(new Font("굴림",Font.BOLD,10));
		btn2.setBounds(200,62,60,20);
		jp1.add(btn2);
		
		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model2 = (DefaultTableModel)ta.getModel();
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("Update ingredient set Count = Count + 1 where IngredientName = '불고기패티'");
					ResultSet results = statement.executeQuery();
					
					}catch(Exception e) {
						System.out.println(e.getMessage());
				   }
				try {
					Connection con = getIngre();
					PreparedStatement statement2 = con.prepareStatement("Update bank set Money = Money - 800");
					ResultSet results2 = statement2.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
				model2.setRowCount(0);                                  // 테이블을 싹다 지움
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("Select * From ingredient");
					ResultSet results = statement.executeQuery();
					ArrayList<String[]> list = new ArrayList<String[]>();
					while(results.next()) {
						model.addRow(new Object[] {results.getString("IngredientName"),results.getString("Count")});
					}
					}catch(Exception e) {
						System.out.println(e.getMessage());
				   }
			}
		});
		
		JButton btn3 = new JButton("추가");                      // 양상추 버튼
		btn3.setFont(new Font("굴림",Font.BOLD,10));
		btn3.setBounds(200,92,60,20);
		jp1.add(btn3);
		
		btn3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model2 = (DefaultTableModel)ta.getModel();
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("Update ingredient set Count = Count + 1 where IngredientName = '양상추'");
					ResultSet results = statement.executeQuery();
				
					}catch(Exception e) {
						System.out.println(e.getMessage());
				   }
				try {
					Connection con = getIngre();
					PreparedStatement statement2 = con.prepareStatement("Update bank set Money = Money - 300");
					ResultSet results2 = statement2.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
				model2.setRowCount(0);                                // 테이블을 싹다 지움
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("Select * From ingredient");
					ResultSet results = statement.executeQuery();
					ArrayList<String[]> list = new ArrayList<String[]>();
					while(results.next()) {
						model.addRow(new Object[] {results.getString("IngredientName"),results.getString("Count")});
					}
					}catch(Exception e) {
						System.out.println(e.getMessage());
				   }
			}
		});
		
		JButton btn4 = new JButton("추가");                      // 피클 버튼
		btn4.setFont(new Font("굴림",Font.BOLD,10));
		btn4.setBounds(200,122,60,20);
		jp1.add(btn4);
		
		btn4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model2 = (DefaultTableModel)ta.getModel();
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("Update ingredient set Count = Count + 1 where IngredientName = '피클'");
					ResultSet results = statement.executeQuery();
					
					}catch(Exception e) {
						System.out.println(e.getMessage());
				   }
				try {
					Connection con = getIngre();
					PreparedStatement statement2 = con.prepareStatement("Update bank set Money = Money - 400");
					ResultSet results2 = statement2.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
				model2.setRowCount(0);                                  // 테이블을 싹다 지움
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("Select * From ingredient");
					ResultSet results = statement.executeQuery();
					ArrayList<String[]> list = new ArrayList<String[]>();
					while(results.next()) {
						model.addRow(new Object[] {results.getString("IngredientName"),results.getString("Count")});
					}
					}catch(Exception e) {
						System.out.println(e.getMessage());
				   }
			}
		});
		
		JButton btn5 = new JButton("추가");                      // 특별한소스 버튼
		btn5.setFont(new Font("굴림",Font.BOLD,10));
		btn5.setBounds(200,152,60,20);
		jp1.add(btn5);
		
		btn5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model2 = (DefaultTableModel)ta.getModel();
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("Update ingredient set Count = Count + 1 where IngredientName = '특별한소스'");
					ResultSet results = statement.executeQuery();
					
					}catch(Exception e) {
						System.out.println(e.getMessage());
				   }
				try {
					Connection con = getIngre();
					PreparedStatement statement2 = con.prepareStatement("Update bank set Money = Money - 200");
					ResultSet results2 = statement2.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
				model2.setRowCount(0);                                    // 테이블을 싹다 지움
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("Select * From ingredient");
					ResultSet results = statement.executeQuery();
					ArrayList<String[]> list = new ArrayList<String[]>();
					while(results.next()) {
						model.addRow(new Object[] {results.getString("IngredientName"),results.getString("Count")});
					}
					}catch(Exception e) {
						System.out.println(e.getMessage());
				   }
			}
		});
		
		JButton btn6 = new JButton("추가");                      // 감자 버튼
		btn6.setFont(new Font("굴림",Font.BOLD,10));
		btn6.setBounds(200,182,60,20);
		jp1.add(btn6);
		
		btn6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model2 = (DefaultTableModel)ta.getModel();
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("Update ingredient set Count = Count + 1 where IngredientName = '감자'");
					ResultSet results = statement.executeQuery();
					
					}catch(Exception e) {
						System.out.println(e.getMessage());
				   }
				try {
					Connection con = getIngre();
					PreparedStatement statement2 = con.prepareStatement("Update bank set Money = Money - 600");
					ResultSet results2 = statement2.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
				model2.setRowCount(0);                           // 테이블을 싹다 지움
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("Select * From ingredient");
					ResultSet results = statement.executeQuery();
					ArrayList<String[]> list = new ArrayList<String[]>();
					while(results.next()) {
						model.addRow(new Object[] {results.getString("IngredientName"),results.getString("Count")});
					}
					}catch(Exception e) {
						System.out.println(e.getMessage());
				   }
			}
		});
		
		JButton btn7 = new JButton("추가");                      // 양파 버튼
		btn7.setFont(new Font("굴림",Font.BOLD,10));
		btn7.setBounds(200,212,60,20);
		jp1.add(btn7);
		
		btn7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model2 = (DefaultTableModel)ta.getModel();
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("Update ingredient set Count = Count + 1 where IngredientName = '양파'");
					ResultSet results = statement.executeQuery();
					
					}catch(Exception e) {
						System.out.println(e.getMessage());
				   }
				try {
					Connection con = getIngre();
					PreparedStatement statement2 = con.prepareStatement("Update bank set Money = Money - 500");
					ResultSet results2 = statement2.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
				model2.setRowCount(0);                           // 테이블을 싹다 지움
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("Select * From ingredient");
					ResultSet results = statement.executeQuery();
					ArrayList<String[]> list = new ArrayList<String[]>();
					while(results.next()) {
						model.addRow(new Object[] {results.getString("IngredientName"),results.getString("Count")});
					}
					}catch(Exception e) {
						System.out.println(e.getMessage());
				   }
			}
		});
		
		JButton btn8 = new JButton("추가");                      // 치킨패티 버튼
		btn8.setFont(new Font("굴림",Font.BOLD,10));
		btn8.setBounds(200,242,60,20);
		jp1.add(btn8);
		
		btn8.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model2 = (DefaultTableModel)ta.getModel();
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("Update ingredient set Count = Count + 1 where IngredientName = '치킨패티'");
					ResultSet results = statement.executeQuery();
					
					}catch(Exception e) {
						System.out.println(e.getMessage());
				   }
				try {
					Connection con = getIngre();
					PreparedStatement statement2 = con.prepareStatement("Update bank set Money = Money - 1000");
					ResultSet results2 = statement2.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
				model2.setRowCount(0);                           // 테이블을 싹다 지움
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("Select * From ingredient");
					ResultSet results = statement.executeQuery();
					ArrayList<String[]> list = new ArrayList<String[]>();
					while(results.next()) {
						model.addRow(new Object[] {results.getString("IngredientName"),results.getString("Count")});
					}
					}catch(Exception e) {
						System.out.println(e.getMessage());
				   }
			}
		});
		
		JButton btn9 = new JButton("추가");                      // 오징어패티 버튼
		btn9.setFont(new Font("굴림",Font.BOLD,10));
		btn9.setBounds(200,272,60,20);
		jp1.add(btn9);
		
		btn9.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model2 = (DefaultTableModel)ta.getModel();
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("Update ingredient set Count = Count + 1 where IngredientName = '오징어패티'");
					ResultSet results = statement.executeQuery();
					
					}catch(Exception e) {
						System.out.println(e.getMessage());
				   }
				try {
					Connection con = getIngre();
					PreparedStatement statement2 = con.prepareStatement("Update bank set Money = Money - 900");
					ResultSet results2 = statement2.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
				model2.setRowCount(0);                           // 테이블을 싹다 지움
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("Select * From ingredient");
					ResultSet results = statement.executeQuery();
					ArrayList<String[]> list = new ArrayList<String[]>();
					while(results.next()) {
						model.addRow(new Object[] {results.getString("IngredientName"),results.getString("Count")});
					}
					}catch(Exception e) {
						System.out.println(e.getMessage());
				   }
			}
		});
		
		JButton btn10 = new JButton("추가");                      // 달걀 버튼
		btn10.setFont(new Font("굴림",Font.BOLD,10));
		btn10.setBounds(200,302,60,20);
		jp1.add(btn10);
		
		btn10.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model2 = (DefaultTableModel)ta.getModel();
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("Update ingredient set Count = Count + 1 where IngredientName = '달걀'");
					ResultSet results = statement.executeQuery();
					
					}catch(Exception e) {
						System.out.println(e.getMessage());
				   }
				try {
					Connection con = getIngre();
					PreparedStatement statement2 = con.prepareStatement("Update bank set Money = Money - 300");
					ResultSet results2 = statement2.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
				model2.setRowCount(0);                           // 테이블을 싹다 지움
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("Select * From ingredient");
					ResultSet results = statement.executeQuery();
					ArrayList<String[]> list = new ArrayList<String[]>();
					while(results.next()) {
						model.addRow(new Object[] {results.getString("IngredientName"),results.getString("Count")});
					}
					}catch(Exception e) {
						System.out.println(e.getMessage());
				   }
			}
		});
		
		JButton btn11 = new JButton("추가");                      // 베이컨 버튼
		btn11.setFont(new Font("굴림",Font.BOLD,10));
		btn11.setBounds(200,332,60,20);
		jp1.add(btn11);
		
		btn11.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model2 = (DefaultTableModel)ta.getModel();
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("Update ingredient set Count = Count + 1 where IngredientName = '베이컨'");
					ResultSet results = statement.executeQuery();
					
					}catch(Exception e) {
						System.out.println(e.getMessage());
				   }
				try {
					Connection con = getIngre();
					PreparedStatement statement2 = con.prepareStatement("Update bank set Money = Money - 800");
					ResultSet results2 = statement2.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
				model2.setRowCount(0);                           // 테이블을 싹다 지움
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("Select * From ingredient");
					ResultSet results = statement.executeQuery();
					ArrayList<String[]> list = new ArrayList<String[]>();
					while(results.next()) {
						model.addRow(new Object[] {results.getString("IngredientName"),results.getString("Count")});
					}
					}catch(Exception e) {
						System.out.println(e.getMessage());
				   }
			}
		});
		
		JButton btn12 = new JButton("추가");                      // 식용유 버튼
		btn12.setFont(new Font("굴림",Font.BOLD,10));
		btn12.setBounds(200,362,60,20);
		jp1.add(btn12);
		
		btn12.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model2 = (DefaultTableModel)ta.getModel();
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("Update ingredient set Count = Count + 1 where IngredientName = '식용유'");
					ResultSet results = statement.executeQuery();
					
					}catch(Exception e) {
						System.out.println(e.getMessage());
				   }
				try {
					Connection con = getIngre();
					PreparedStatement statement2 = con.prepareStatement("Update bank set Money = Money - 1200");
					ResultSet results2 = statement2.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
				model2.setRowCount(0);                           // 테이블을 싹다 지움
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("Select * From ingredient");
					ResultSet results = statement.executeQuery();
					ArrayList<String[]> list = new ArrayList<String[]>();
					while(results.next()) {
						model.addRow(new Object[] {results.getString("IngredientName"),results.getString("Count")});
					}
					}catch(Exception e) {
						System.out.println(e.getMessage());
				   }
			}
		});
		
		JButton btn13 = new JButton("추가");                      // 치즈 버튼
		btn13.setFont(new Font("굴림",Font.BOLD,10));
		btn13.setBounds(200,392,60,20);
		jp1.add(btn13);
		
		btn13.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model2 = (DefaultTableModel)ta.getModel();
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("Update ingredient set Count = Count + 1 where IngredientName = '치즈'");
					ResultSet results = statement.executeQuery();
					
					}catch(Exception e) {
						System.out.println(e.getMessage());
				   }
				try {
					Connection con = getIngre();
					PreparedStatement statement2 = con.prepareStatement("Update bank set Money = Money - 500");
					ResultSet results2 = statement2.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
				model2.setRowCount(0);                           // 테이블을 싹다 지움
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("Select * From ingredient");
					ResultSet results = statement.executeQuery();
					ArrayList<String[]> list = new ArrayList<String[]>();
					while(results.next()) {
						model.addRow(new Object[] {results.getString("IngredientName"),results.getString("Count")});
					}
					}catch(Exception e) {
						System.out.println(e.getMessage());
				   }
			}
		});
		
		JButton btn14 = new JButton("추가");                      // 우유 버튼
		btn14.setFont(new Font("굴림",Font.BOLD,10));
		btn14.setBounds(200,422,60,20);
		jp1.add(btn14);
		
		btn14.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model2 = (DefaultTableModel)ta.getModel();
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("Update ingredient set Count = Count + 1 where IngredientName = '우유'");
					ResultSet results = statement.executeQuery();
					
					}catch(Exception e) {
						System.out.println(e.getMessage());
				   }
				try {
					Connection con = getIngre();
					PreparedStatement statement2 = con.prepareStatement("Update bank set Money = Money - 600");
					ResultSet results2 = statement2.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
				model2.setRowCount(0);                           // 테이블을 싹다 지움
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("Select * From ingredient");
					ResultSet results = statement.executeQuery();
					ArrayList<String[]> list = new ArrayList<String[]>();
					while(results.next()) {
						model.addRow(new Object[] {results.getString("IngredientName"),results.getString("Count")});
					}
					}catch(Exception e) {
						System.out.println(e.getMessage());
				   }
			}
		});
		
		JButton btn15 = new JButton("추가");                      // 사이다 버튼
		btn15.setFont(new Font("굴림",Font.BOLD,10));
		btn15.setBounds(200,452,60,20);
		jp1.add(btn15);
		
		btn15.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model2 = (DefaultTableModel)ta.getModel();
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("Update ingredient set Count = Count + 1 where IngredientName = '사이다'");
					ResultSet results = statement.executeQuery();
					
					}catch(Exception e) {
						System.out.println(e.getMessage());
				   }
				try {
					Connection con = getIngre();
					PreparedStatement statement2 = con.prepareStatement("Update bank set Money = Money - 500");
					ResultSet results2 = statement2.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
				model2.setRowCount(0);                           // 테이블을 싹다 지움
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("Select * From ingredient");
					ResultSet results = statement.executeQuery();
					ArrayList<String[]> list = new ArrayList<String[]>();
					while(results.next()) {
						model.addRow(new Object[] {results.getString("IngredientName"),results.getString("Count")});
					}
					}catch(Exception e) {
						System.out.println(e.getMessage());
				   }
			}
		});
		
		JButton btn16 = new JButton("추가");                      // 콜라 버튼
		btn16.setFont(new Font("굴림",Font.BOLD,10));
		btn16.setBounds(200,482,60,20);
		jp1.add(btn16);
		
		btn16.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model2 = (DefaultTableModel)ta.getModel();
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("Update ingredient set Count = Count + 1 where IngredientName = '콜라'");
					ResultSet results = statement.executeQuery();
					
					}catch(Exception e) {
						System.out.println(e.getMessage());
				   }
				try {
					Connection con = getIngre();
					PreparedStatement statement2 = con.prepareStatement("Update bank set Money = Money - 500");
					ResultSet results2 = statement2.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
				model2.setRowCount(0);                           // 테이블을 싹다 지움
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("Select * From ingredient");
					ResultSet results = statement.executeQuery();
					ArrayList<String[]> list = new ArrayList<String[]>();
					while(results.next()) {
						model.addRow(new Object[] {results.getString("IngredientName"),results.getString("Count")});
					}
					}catch(Exception e) {
						System.out.println(e.getMessage());
				   }
			}
		});
		
		JButton btn17 = new JButton("추가");                      // 얼음 버튼
		btn17.setFont(new Font("굴림",Font.BOLD,10));
		btn17.setBounds(200,512,60,20);
		jp1.add(btn17);
		
		btn17.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model2 = (DefaultTableModel)ta.getModel();
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("Update ingredient set Count = Count + 1 where IngredientName = '얼음'");
					ResultSet results = statement.executeQuery();
					
					}catch(Exception e) {
						System.out.println(e.getMessage());
				   }
				try {
					Connection con = getIngre();
					PreparedStatement statement2 = con.prepareStatement("Update bank set Money = Money - 300");
					ResultSet results2 = statement2.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
				model2.setRowCount(0);                           // 테이블을 싹다 지움
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("Select * From ingredient");
					ResultSet results = statement.executeQuery();
					ArrayList<String[]> list = new ArrayList<String[]>();
					while(results.next()) {
						model.addRow(new Object[] {results.getString("IngredientName"),results.getString("Count")});
					}
					}catch(Exception e) {
						System.out.println(e.getMessage());
				   }
			}
		});
		
		JButton lobi = new JButton("로비창가기");
		lobi.setBounds(320,480,100,40);
		jp1.add(lobi);
		
		lobi.addActionListener(new MainClass(){
			public void actionPerformed(ActionEvent e) {         // 로비창으로 이동
				FirstWindow W1 = new FirstWindow();
		         W1.setVisible(true);
		         dispose();
				}
			});
		
		ct.add(title, BorderLayout.NORTH);
		ct.add(jp1);
		
		setBounds(1,1,450,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}

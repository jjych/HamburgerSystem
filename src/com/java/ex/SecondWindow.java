package com.java.ex;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.java.ex.Event.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class SecondWindow extends JFrame{
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	
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
	public SecondWindow() {
		setTitle("메뉴판");
		setSize(500,500);
		
		Container ct = getContentPane();
		
		JLabel jl1 = new JLabel("메뉴판");
		jl1.setHorizontalAlignment(JLabel.CENTER);
		
		JPanel jp1 = new JPanel();
		jp1.setLayout(null);
		
		int[] price = {
				2000,2200,4900,3300,
				5300,4300,4400,3500,
				1000,1300,1300,500};
		
		String [] ColName = {"상품명","금액"};
		String [][] Data = null;
		DefaultTableModel model = new DefaultTableModel(Data,ColName);
		JTable table = new JTable(model);
		table.setBounds(15,290,150,150);                // 테이블 추가
		jp1.add(table);
		
		
		JButton bulgogi = new JButton("불고기버거");       // 불고기버거 버튼
		bulgogi.setBounds(10,1,100,50);                 
		jp1.add(bulgogi);
		JLabel billBul = new JLabel("2000원");           // 불고기버거 가격라벨
		billBul.setBounds(40,40,100,50);
		jp1.add(billBul);
		
		bulgogi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel m = (DefaultTableModel)table.getModel();
				m.addRow(new Object[]{"불고기버거",price[0]});
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("insert into jumun values ('불고기버거')");  // 불고기버거 DB 저장
					ResultSet results = statement.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
			}
		});
		
		JButton cheezeB = new JButton("치즈버거");         // 치즈버거 버튼
		cheezeB.setBounds(120,1,100,50);
		jp1.add(cheezeB);
		JLabel billCheeze = new JLabel("2200원");        // 치즈버거 가격라벨
		billCheeze.setBounds(150,40,100,50);
		jp1.add(billCheeze);
		
		cheezeB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel m = (DefaultTableModel)table.getModel();
				m.addRow(new Object[]{"치즈버거",price[1]});
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("insert into jumun values ('치즈버거')");  // 치즈버거 DB 저장
					ResultSet results = statement.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
			}
		});
		
		JButton bigmag = new JButton("빅맥");             // 빅맥 버튼
		bigmag.setBounds(230,1,100,50);
		jp1.add(bigmag);
		JLabel billBig = new JLabel("4900원");           // 빅맥 가격라벨
		billBig.setBounds(260,40,100,50);
		jp1.add(billBig);
		
		bigmag.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel m = (DefaultTableModel)table.getModel();
				m.addRow(new Object[]{"빅맥",price[2]});
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("insert into jumun values ('빅맥')");  // 빅맥 DB 저장
					ResultSet results = statement.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
			}
		});
		
		JButton crispy = new JButton("크리스피버거");       // 크리스피버거 버튼
		crispy.setBounds(340,1,130,50);
		jp1.add(crispy);
		JLabel billCris = new JLabel("3300원");          // 크리스피버거 가격라벨
		billCris.setBounds(380,40,100,50);
		jp1.add(billCris);
		
		crispy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel m = (DefaultTableModel)table.getModel();
				m.addRow(new Object[]{"크리스피버거",price[3]});
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("insert into jumun values ('크리스피버거')");  // 크리스피버거 DB 저장
					ResultSet results = statement.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
			}
		});
		
		JButton baken = new JButton("베이컨버거");         // 베이컨버거 버튼
		baken.setBounds(10,80,100,50);
		jp1.add(baken);
		JLabel billBaken = new JLabel("5300원");         // 베이컨버거 가격라벨
		billBaken.setBounds(40,120,100,50);
		jp1.add(billBaken);
		
		baken.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel m = (DefaultTableModel)table.getModel();
				m.addRow(new Object[]{"베이컨버거",price[4]});
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("insert into jumun values ('베이컨버거')");  // 베이컨버거 DB 저장
					ResultSet results = statement.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
			}
		});
		
		JButton ojing = new JButton("오징어버거");         // 오징어버거 버튼
		ojing.setBounds(120,80,110,50);
		jp1.add(ojing);
		JLabel billOjing = new JLabel("4300원");         // 오징어버거 가격라벨
		billOjing.setBounds(150,120,100,50);
		jp1.add(billOjing);
		
		ojing.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel m = (DefaultTableModel)table.getModel();
				m.addRow(new Object[]{"오징어버거",price[5]});
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("insert into jumun values ('오징어버거')");  // 오징어버거 DB 저장
					ResultSet results = statement.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
			}
		});
		
		JButton doublebul = new JButton("더블버거");       // 더블버거 버튼
		doublebul.setBounds(240,80,100,50);
		jp1.add(doublebul);
		JLabel billDoublebul = new JLabel("4400원");     // 더블버거 가격라벨
		billDoublebul.setBounds(270,120,100,50);
		jp1.add(billDoublebul);
		
		doublebul.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel m = (DefaultTableModel)table.getModel();
				m.addRow(new Object[]{"더블버거",price[6]});
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("insert into jumun values ('더블버거')");  // 더블버거 DB 저장
					ResultSet results = statement.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
			}
		});
		
		JButton eggbul = new JButton("에그치즈버거");       // 에그치즈버거 버튼
		eggbul.setBounds(350,80,130,50);
		jp1.add(eggbul);
		JLabel billEggbul = new JLabel("3500원");        // 에그치즈버거 가격라벨
		billEggbul.setBounds(390,120,100,50);
		jp1.add(billEggbul);
		
		eggbul.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel m = (DefaultTableModel)table.getModel();
				m.addRow(new Object[]{"에그치즈버거",price[7]});
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("insert into jumun values ('에그치즈버거')");  // 에그치즈버거 DB 저장
					ResultSet results = statement.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
			}
		});
		
		JButton gamti = new JButton("감자튀김");           // 감자튀김 버튼
		gamti.setBounds(10,160,100,50);
		jp1.add(gamti);
		JLabel billGamti = new JLabel("1000원");         // 감자튀김 가격라벨
		billGamti.setBounds(40,200,100,50);
		jp1.add(billGamti);
		
		gamti.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel m = (DefaultTableModel)table.getModel();
				m.addRow(new Object[]{"감자튀김",price[8]});
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("insert into jumun values ('감자튀김')");  // 감자튀김 DB 저장
					ResultSet results = statement.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
			}
		});
		
		JButton cola = new JButton("콜라");               // 콜라 버튼
		cola.setBounds(120,160,100,50);
		jp1.add(cola);
		JLabel billCola = new JLabel("1300원");          // 콜라 가격라벨
		billCola.setBounds(150,200,100,50);
		jp1.add(billCola);
		
		cola.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel m = (DefaultTableModel)table.getModel();
				m.addRow(new Object[]{"콜라",price[9]});
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("insert into jumun values ('콜라')");  // 콜라 DB 저장
					ResultSet results = statement.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
			}
		});
		
		JButton sider = new JButton("사이다");             // 사이다 버튼
		sider.setBounds(230,160,100,50);
		jp1.add(sider);
		JLabel billSider = new JLabel("1300원");          // 사이다 가격라벨
		billSider.setBounds(260,200,100,50);
		jp1.add(billSider);
		
		sider.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel m = (DefaultTableModel)table.getModel();
				m.addRow(new Object[]{"사이다",price[10]});
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("insert into jumun values ('사이다')");  // 사이다 DB 저장
					ResultSet results = statement.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
			}
		});
		
		JButton icecream = new JButton("아이스크림");        // 아이스크림 버튼
		icecream.setBounds(340,160,130,50);
		jp1.add(icecream);
		JLabel billIcecream = new JLabel("500원");         // 아이스크림 가격라벨
		billIcecream.setBounds(390,200,100,50);
		jp1.add(billIcecream);
		
		icecream.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel m = (DefaultTableModel)table.getModel();
				m.addRow(new Object[]{"아이스크림",price[11]});
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("insert into jumun values ('아이스크림')");  // 아이스크림 DB 저장
					ResultSet results = statement.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
			}
		});
		
		JLabel jumun = new JLabel("주문 내역");              // 주문내역 라벨
		jumun.setBounds(60,240,100,50);
		jp1.add(jumun);
		
		JButton jumuncancel = new JButton("주문취소");      // 주문취소 버튼
		jumuncancel.setBounds(320,240,150,50);
		jp1.add(jumuncancel);
		
		jumuncancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel m = (DefaultTableModel)table.getModel();
//				m.removeRow(table.getSelectedRow());
				m.setRowCount(0);
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("delete from jumun");
					ResultSet results = statement.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
			}
		});
		
		JButton jumunsucess = new JButton("주문하기");          // 주문하기 버튼
		jumunsucess.setBounds(320,380,150,50);
		jp1.add(jumunsucess);
		
		jumunsucess.addActionListener(new Jubang() {             // 주방으로 이동
		public void actionPerformed(ActionEvent e) {
			ThirdWindow W3 = new ThirdWindow();
	         W3.setVisible(true);
	         dispose();
			}
		});
		
		JLabel gmack = new JLabel("가격  : 0원");            // 결제금액 추가
		gmack.setBounds(320,310,150,50);
		gmack.setFont(new Font("가격", Font.BOLD, 20));
		jp1.add(gmack);
		
		JButton gelje = new JButton("결제하기");
		gelje.setBounds(180,380,120,50);
		jp1.add(gelje);
		
		gelje.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int rowCont = table.getRowCount();
				int sum =0;
				for(int i=0;i<rowCont;i++) {
					sum += (int)table.getValueAt(i,1);
				}
				gmack.setText(String.valueOf("가격  : "+sum+"원"));
				gmack.setFont(new Font("가격", Font.BOLD, 20));
			}
		});
		
		ct.add(jl1, BorderLayout.NORTH);
		ct.add(jp1);
		
		setBounds(1,1,500,500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}

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
	ResultSetMetaData rsmd = null; // resultset �� ���� �÷��� �Ӽ��̳� Ÿ�� ������ ����Ѵ�.
	
	public static Connection getConnection() {                // ��ǰ DB����
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
	
	public static String[][] getCustomers(){                  // ��ǰDB �ҷ�����
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
	
	public void conn() {                       // �ܾ� DB����
		// ����Ŭ ����̹� ����
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			// ����Ŭ DB����
			con = DriverManager.getConnection("jdbc:mariadb://Localhost:3307/gimal2020",
					"root", "1234");
		} catch (SQLException ee) {
			ee.printStackTrace();
		}
	}
	public ForthWindow() {
		conn();
		Container ct = getContentPane();
		setTitle("�ܾ���ȸ �� ����ǰ��");
		setSize(400,500);
		
		JLabel title = new JLabel("�ܾ���ȸ �� ����ǰ��");
		title.setHorizontalAlignment(JLabel.CENTER);
		
		JPanel jp1 = new JPanel();
		jp1.setLayout(null);
		
		JLabel janAct = new JLabel("�ܾ� : ");
		janAct.setBounds(100,1,50,50);
		jp1.add(janAct);
		
		JLabel lb = new JLabel();
		lb.setBounds(180,1,50,50);
		lb.setFont(new Font("", Font.BOLD, 15));
		jp1.add(lb);
		try {
			pstmt = con.prepareStatement("select * from bank"); // SQL ����
			rs = pstmt.executeQuery(); // ���� �������� rs�� �����Ѵ�.
	
			while (rs.next()) {
				lb.setText(rs.getString(1));
					
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		JLabel lbone = new JLabel("��");
		lbone.setBounds(250,1,50,50);
		jp1.add(lbone);
		
		JLabel macul = new JLabel("���� ǰ��");
		macul.setBounds(170,30,100,50);
		jp1.add(macul);
		
		JLabel food = new JLabel("��ǰ���");
		food.setBounds(100,60,100,50);
		jp1.add(food);
		
		JLabel pamac = new JLabel("�Ǹŷ�");
		pamac.setBounds(240,60,100,50);
		jp1.add(pamac);
		
		String[][] data = ForthWindow.getCustomers();        // DB������ table
		
		String[] headers = new String[] {"���� ǰ��","�Ǹŷ�"};
		JTable table = new JTable(data,headers);
		table.setBounds(70,100,250,190);
		jp1.add(table);
		
		JButton Lobi = new JButton("�κ�â����");
		Lobi.setBounds(220,350,150,50);
		jp1.add(Lobi);
		
		Lobi.addActionListener(new MainClass(){
			public void actionPerformed(ActionEvent e) {         // �κ�â���� �̵�
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

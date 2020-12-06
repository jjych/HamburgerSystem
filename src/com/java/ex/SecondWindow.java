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
	
	public static Connection getIngre() {                // ��� DB����
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
		setTitle("�޴���");
		setSize(500,500);
		
		Container ct = getContentPane();
		
		JLabel jl1 = new JLabel("�޴���");
		jl1.setHorizontalAlignment(JLabel.CENTER);
		
		JPanel jp1 = new JPanel();
		jp1.setLayout(null);
		
		int[] price = {
				2000,2200,4900,3300,
				5300,4300,4400,3500,
				1000,1300,1300,500};
		
		String [] ColName = {"��ǰ��","�ݾ�"};
		String [][] Data = null;
		DefaultTableModel model = new DefaultTableModel(Data,ColName);
		JTable table = new JTable(model);
		table.setBounds(15,290,150,150);                // ���̺� �߰�
		jp1.add(table);
		
		
		JButton bulgogi = new JButton("�Ұ�����");       // �Ұ����� ��ư
		bulgogi.setBounds(10,1,100,50);                 
		jp1.add(bulgogi);
		JLabel billBul = new JLabel("2000��");           // �Ұ����� ���ݶ�
		billBul.setBounds(40,40,100,50);
		jp1.add(billBul);
		
		bulgogi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel m = (DefaultTableModel)table.getModel();
				m.addRow(new Object[]{"�Ұ�����",price[0]});
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("insert into jumun values ('�Ұ�����')");  // �Ұ����� DB ����
					ResultSet results = statement.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
			}
		});
		
		JButton cheezeB = new JButton("ġ�����");         // ġ����� ��ư
		cheezeB.setBounds(120,1,100,50);
		jp1.add(cheezeB);
		JLabel billCheeze = new JLabel("2200��");        // ġ����� ���ݶ�
		billCheeze.setBounds(150,40,100,50);
		jp1.add(billCheeze);
		
		cheezeB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel m = (DefaultTableModel)table.getModel();
				m.addRow(new Object[]{"ġ�����",price[1]});
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("insert into jumun values ('ġ�����')");  // ġ����� DB ����
					ResultSet results = statement.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
			}
		});
		
		JButton bigmag = new JButton("���");             // ��� ��ư
		bigmag.setBounds(230,1,100,50);
		jp1.add(bigmag);
		JLabel billBig = new JLabel("4900��");           // ��� ���ݶ�
		billBig.setBounds(260,40,100,50);
		jp1.add(billBig);
		
		bigmag.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel m = (DefaultTableModel)table.getModel();
				m.addRow(new Object[]{"���",price[2]});
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("insert into jumun values ('���')");  // ��� DB ����
					ResultSet results = statement.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
			}
		});
		
		JButton crispy = new JButton("ũ�����ǹ���");       // ũ�����ǹ��� ��ư
		crispy.setBounds(340,1,130,50);
		jp1.add(crispy);
		JLabel billCris = new JLabel("3300��");          // ũ�����ǹ��� ���ݶ�
		billCris.setBounds(380,40,100,50);
		jp1.add(billCris);
		
		crispy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel m = (DefaultTableModel)table.getModel();
				m.addRow(new Object[]{"ũ�����ǹ���",price[3]});
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("insert into jumun values ('ũ�����ǹ���')");  // ũ�����ǹ��� DB ����
					ResultSet results = statement.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
			}
		});
		
		JButton baken = new JButton("����������");         // ���������� ��ư
		baken.setBounds(10,80,100,50);
		jp1.add(baken);
		JLabel billBaken = new JLabel("5300��");         // ���������� ���ݶ�
		billBaken.setBounds(40,120,100,50);
		jp1.add(billBaken);
		
		baken.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel m = (DefaultTableModel)table.getModel();
				m.addRow(new Object[]{"����������",price[4]});
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("insert into jumun values ('����������')");  // ���������� DB ����
					ResultSet results = statement.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
			}
		});
		
		JButton ojing = new JButton("��¡�����");         // ��¡����� ��ư
		ojing.setBounds(120,80,110,50);
		jp1.add(ojing);
		JLabel billOjing = new JLabel("4300��");         // ��¡����� ���ݶ�
		billOjing.setBounds(150,120,100,50);
		jp1.add(billOjing);
		
		ojing.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel m = (DefaultTableModel)table.getModel();
				m.addRow(new Object[]{"��¡�����",price[5]});
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("insert into jumun values ('��¡�����')");  // ��¡����� DB ����
					ResultSet results = statement.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
			}
		});
		
		JButton doublebul = new JButton("�������");       // ������� ��ư
		doublebul.setBounds(240,80,100,50);
		jp1.add(doublebul);
		JLabel billDoublebul = new JLabel("4400��");     // ������� ���ݶ�
		billDoublebul.setBounds(270,120,100,50);
		jp1.add(billDoublebul);
		
		doublebul.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel m = (DefaultTableModel)table.getModel();
				m.addRow(new Object[]{"�������",price[6]});
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("insert into jumun values ('�������')");  // ������� DB ����
					ResultSet results = statement.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
			}
		});
		
		JButton eggbul = new JButton("����ġ�����");       // ����ġ����� ��ư
		eggbul.setBounds(350,80,130,50);
		jp1.add(eggbul);
		JLabel billEggbul = new JLabel("3500��");        // ����ġ����� ���ݶ�
		billEggbul.setBounds(390,120,100,50);
		jp1.add(billEggbul);
		
		eggbul.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel m = (DefaultTableModel)table.getModel();
				m.addRow(new Object[]{"����ġ�����",price[7]});
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("insert into jumun values ('����ġ�����')");  // ����ġ����� DB ����
					ResultSet results = statement.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
			}
		});
		
		JButton gamti = new JButton("����Ƣ��");           // ����Ƣ�� ��ư
		gamti.setBounds(10,160,100,50);
		jp1.add(gamti);
		JLabel billGamti = new JLabel("1000��");         // ����Ƣ�� ���ݶ�
		billGamti.setBounds(40,200,100,50);
		jp1.add(billGamti);
		
		gamti.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel m = (DefaultTableModel)table.getModel();
				m.addRow(new Object[]{"����Ƣ��",price[8]});
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("insert into jumun values ('����Ƣ��')");  // ����Ƣ�� DB ����
					ResultSet results = statement.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
			}
		});
		
		JButton cola = new JButton("�ݶ�");               // �ݶ� ��ư
		cola.setBounds(120,160,100,50);
		jp1.add(cola);
		JLabel billCola = new JLabel("1300��");          // �ݶ� ���ݶ�
		billCola.setBounds(150,200,100,50);
		jp1.add(billCola);
		
		cola.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel m = (DefaultTableModel)table.getModel();
				m.addRow(new Object[]{"�ݶ�",price[9]});
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("insert into jumun values ('�ݶ�')");  // �ݶ� DB ����
					ResultSet results = statement.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
			}
		});
		
		JButton sider = new JButton("���̴�");             // ���̴� ��ư
		sider.setBounds(230,160,100,50);
		jp1.add(sider);
		JLabel billSider = new JLabel("1300��");          // ���̴� ���ݶ�
		billSider.setBounds(260,200,100,50);
		jp1.add(billSider);
		
		sider.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel m = (DefaultTableModel)table.getModel();
				m.addRow(new Object[]{"���̴�",price[10]});
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("insert into jumun values ('���̴�')");  // ���̴� DB ����
					ResultSet results = statement.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
			}
		});
		
		JButton icecream = new JButton("���̽�ũ��");        // ���̽�ũ�� ��ư
		icecream.setBounds(340,160,130,50);
		jp1.add(icecream);
		JLabel billIcecream = new JLabel("500��");         // ���̽�ũ�� ���ݶ�
		billIcecream.setBounds(390,200,100,50);
		jp1.add(billIcecream);
		
		icecream.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel m = (DefaultTableModel)table.getModel();
				m.addRow(new Object[]{"���̽�ũ��",price[11]});
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("insert into jumun values ('���̽�ũ��')");  // ���̽�ũ�� DB ����
					ResultSet results = statement.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
			}
		});
		
		JLabel jumun = new JLabel("�ֹ� ����");              // �ֹ����� ��
		jumun.setBounds(60,240,100,50);
		jp1.add(jumun);
		
		JButton jumuncancel = new JButton("�ֹ����");      // �ֹ���� ��ư
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
		
		JButton jumunsucess = new JButton("�ֹ��ϱ�");          // �ֹ��ϱ� ��ư
		jumunsucess.setBounds(320,380,150,50);
		jp1.add(jumunsucess);
		
		jumunsucess.addActionListener(new Jubang() {             // �ֹ����� �̵�
		public void actionPerformed(ActionEvent e) {
			ThirdWindow W3 = new ThirdWindow();
	         W3.setVisible(true);
	         dispose();
			}
		});
		
		JLabel gmack = new JLabel("����  : 0��");            // �����ݾ� �߰�
		gmack.setBounds(320,310,150,50);
		gmack.setFont(new Font("����", Font.BOLD, 20));
		jp1.add(gmack);
		
		JButton gelje = new JButton("�����ϱ�");
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
				gmack.setText(String.valueOf("����  : "+sum+"��"));
				gmack.setFont(new Font("����", Font.BOLD, 20));
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

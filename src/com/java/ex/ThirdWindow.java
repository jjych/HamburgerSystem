package com.java.ex;

import com.java.ex.*;
import com.java.ex.DB.*;
import com.java.ex.Event.*;
import com.java.ex.main.MainClass;

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
import javax.swing.table.DefaultTableModel;

public class ThirdWindow extends JFrame{
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	ResultSetMetaData rsmd = null; // resultset �� ���� �÷��� �Ӽ��̳� Ÿ�� ������ ����Ѵ�.
//	Ingredient2 stock =null;
//	Ingredient I = null;
	
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
	public static String[][] getJumun(){                  // �ֹ�DB �ҷ�����
		try {
			Connection con = getIngre();
			PreparedStatement statement = con.prepareStatement("Select * From jumun");
			ResultSet results = statement.executeQuery();
			ArrayList<String[]> list = new ArrayList<String[]>();
			while(results.next()) {
				list.add(new String[]{
					results.getString("JumunName")
				});
			}
			String[][] arr = new String[list.size()][3];
			return list.toArray(arr);
			}catch(Exception e) {
				System.out.println(e.getMessage());
				return null;
		   }
			
	}
	
	public void con2() {                       // food DB����
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
	
//	public int checkOrder(){
//		int result=0;
//		if(	stock.getBread() == -1 || 
//			stock.getBulgogi() == -1 || 
//			stock.getYangsangchu() == -1 ||
//			stock.getPicle() == -1 ||
//			stock.getSoce() == -1 ||
//			stock.getGamja() == -1 ||
//			stock.getYangpa() == -1 ||
//			stock.getChiken() == -1 ||
//			stock.getOjing() == -1 ||
//		    stock.getEgg() == -1 ||
//		    stock.getBaken() == -1 ||
//		    stock.getSicyounyu() == -1 ||
//		    stock.getCheeze() == -1 ||
//		    stock.getMilk() == -1 ||
//		    stock.getSider() == -1 ||
//		    stock.getCola() == -1 ||
//		    stock.getIce() == -1 
//		){
//			result=-1;	
//		}	
//			return result;
//	}//checkOrder
	
	public ThirdWindow() {
		con2();
//		I = new Ingredient();
//		stock = new Ingredient2();
//		stock = I.getstock();	//�������� ��� ������ �־��ֱ�
		Container ct = getContentPane();
		setTitle("�ֹ�");
		setSize(800,600);
		
		String[] menu = {
				"������","�Ұ����Ƽ","�����","��Ŭ",
				"Ư���Ѽҽ�","����","����","ġŲ��Ƽ",
				"��¡����Ƽ","�ް�","������","�Ŀ���",
				"ġ��","����","���̴�","�ݶ�","����"};
		
		JLabel jl1 = new JLabel("�ֹ�");
		jl1.setHorizontalAlignment(JLabel.CENTER);
		
		JPanel jp1 = new JPanel();
		jp1.setLayout(null);
		
		JLabel jlist = new JLabel("�ֹ�����");             // �ֹ����� �߰�
		jlist.setBounds(50,5,100,20);
		jp1.add(jlist);
		
		String[][] dataJaro = ThirdWindow.getJumun();            // DB������ ���̺�
		String[] ColName3 = new String[] {"�ֹ��޴�"};
		DefaultTableModel model3 = new DefaultTableModel(dataJaro,ColName3);
		JTable list = new JTable(model3);                      // �ֹ���������Ʈ �߰�
		list.setFont(new Font("Sanserif", Font.BOLD,12));
		list.setBounds(1,30,150,150);
		jp1.add(list);
		
		String [] ColName = {"���"};
		String [][] Data = null;
		DefaultTableModel model = new DefaultTableModel(Data,ColName);
		JTable listResult = new JTable(model);                  // ����� ����Ʈ
		listResult.setFont(new Font("Sanserif", Font.BOLD,12));
		listResult.setBounds(400,320,230,220);
		jp1.add(listResult);
		
		JLabel rasipy = new JLabel("������");              // ������ ��
		rasipy.setBounds(60,200,100,20);
		jp1.add(rasipy);
		
		// ������ �� ����
		JLabel sulmeng = new JLabel("<html>�Ұ����� : ������ + �Ұ����Ƽ + ����� + Ư���Ѽҽ� <br>"
				                    + "ġ����� : ������ + Ư���Ѽҽ� + ġ��<br>"
				                    + "��� : ������ + �Ұ����Ƽ + �Ұ����Ƽ + ����� + ���� + ��Ŭ + Ư���Ѽҽ�<br>"
				                    + "ũ�����ǹ��� : ������ + ġŲ��Ƽ + ġ�� + Ư���Ѽҽ�<br>"
				                    + "���������� : ������ + ����� + ��Ŭ + Ư���Ѽҽ� + ������<br>"
				                    + "��¡����� : ������ + ����� + Ư���Ѽҽ� + ��¡����Ƽ<br>"
				                    + "������� : ������ + ����� + ��Ŭ + ���� + �Ұ����Ƽ + ġŲ��Ƽ<br>"
				                    + "����ġ����� : ������ + ��Ŭ + �ް� + ġ��<br>"
				                    + "����Ƣ�� : ���� + �Ŀ���<br>"
				                    + "�ݶ� : �ݶ� + ����<br>"
				                    + "���̴� : ���̴� + ����<br>"
				                    + "���̽�ũ�� : ���� + ����</html>");
		sulmeng.setBounds(10,240,300,300);
		sulmeng.setFont(new Font("����",Font.BOLD,12));
		jp1.add(sulmeng);
		
		JButton Bread = new JButton("������");             // ������
		Bread.setBounds(180,30,100,40);
		jp1.add(Bread);
		
		JLabel lb1 = new JLabel();                         // ������ ���� ��ᰳ��
		lb1.setBounds(210,60,50,50);
		lb1.setFont(new Font("", Font.BOLD, 12));
		jp1.add(lb1);
		
		JLabel lb1_1 = new JLabel("��");
		lb1_1.setBounds(240,60,50,50);
		lb1_1.setFont(new Font("",Font.BOLD,12));
		jp1.add(lb1_1);
		try {
			pstmt = con.prepareStatement("select * from ingredient where IngredientName = '������'"); // SQL ����
			rs = pstmt.executeQuery(); // ���� �������� rs�� �����Ѵ�.
	
			while (rs.next()) {
				lb1.setText(rs.getString(3));
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Bread.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel m = (DefaultTableModel)listResult.getModel();
				m.addRow(new Object[]{menu[0]});
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("Update ingredient set Count = Count - 1 where IngredientName = '������'");
					ResultSet results = statement.executeQuery();
					
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
				try {
					pstmt = con.prepareStatement("select * from ingredient where IngredientName = '������'"); // SQL ����
					rs = pstmt.executeQuery(); // ���� �������� rs�� �����Ѵ�.
			
					while (rs.next()) {
						lb1.setText(rs.getString(3));
						if(rs.getInt(3) <= 0 ) {
							JOptionPane.showMessageDialog(null, "��ᰡ ���������ϴ�.");
						}
					}
				
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
		JButton BulgogiPatti = new JButton("�Ұ����Ƽ");        // �Ұ����Ƽ
		BulgogiPatti.setBounds(300,30,100,40);
		jp1.add(BulgogiPatti);
		
		JLabel lb2 = new JLabel();                         // �Ұ����Ƽ ���� ��ᰳ��
		lb2.setBounds(330,60,50,50);
		lb2.setFont(new Font("", Font.BOLD, 12));
		jp1.add(lb2);
		
		JLabel lb2_1 = new JLabel("��");
		lb2_1.setBounds(360,60,50,50);
		lb2_1.setFont(new Font("",Font.BOLD,12));
		jp1.add(lb2_1);
		try {
			pstmt = con.prepareStatement("select * from ingredient where IngredientName = '�Ұ����Ƽ'"); // SQL ����
			rs = pstmt.executeQuery(); // ���� �������� rs�� �����Ѵ�.
	
			while (rs.next()) {
				lb2.setText(rs.getString(3));
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		BulgogiPatti.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel m = (DefaultTableModel)listResult.getModel();
				m.addRow(new Object[]{menu[1]});
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("Update ingredient set Count = Count - 1 where IngredientName = '�Ұ����Ƽ'");
					ResultSet results = statement.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
				try {
					pstmt = con.prepareStatement("select * from ingredient where IngredientName = '�Ұ����Ƽ'"); // SQL ����
					rs = pstmt.executeQuery(); // ���� �������� rs�� �����Ѵ�.
			
					while (rs.next()) {
						lb2.setText(rs.getString(3));
						if(rs.getInt(3) <= 0 ) {
							JOptionPane.showMessageDialog(null, "��ᰡ ���������ϴ�.");
						}
					}
				
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
		JButton YangSangChu = new JButton("�����");           // �����
		YangSangChu.setBounds(420,30,100,40);
		jp1.add(YangSangChu);
		
		JLabel lb3 = new JLabel();                         // ����� ���� ��ᰳ��
		lb3.setBounds(450,60,50,50);
		lb3.setFont(new Font("", Font.BOLD, 12));
		jp1.add(lb3);
		
		JLabel lb3_1 = new JLabel("��");
		lb3_1.setBounds(480,60,50,50);
		lb3_1.setFont(new Font("",Font.BOLD,12));
		jp1.add(lb3_1);
		try {
			pstmt = con.prepareStatement("select * from ingredient where IngredientName = '�����'"); // SQL ����
			rs = pstmt.executeQuery(); // ���� �������� rs�� �����Ѵ�.
	
			while (rs.next()) {
				lb3.setText(rs.getString(3));
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		YangSangChu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel m = (DefaultTableModel)listResult.getModel();
				m.addRow(new Object[]{menu[2]});
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("Update ingredient set Count = Count - 1 where IngredientName = '�����'");
					ResultSet results = statement.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
				try {
					pstmt = con.prepareStatement("select * from ingredient where IngredientName = '�����'"); // SQL ����
					rs = pstmt.executeQuery(); // ���� �������� rs�� �����Ѵ�.
			
					while (rs.next()) {
						lb3.setText(rs.getString(3));
						if(rs.getInt(3) <= 0 ) {
							JOptionPane.showMessageDialog(null, "��ᰡ ���������ϴ�.");
						}
					}
				
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
		JButton Picle = new JButton("��Ŭ");                // ��Ŭ
		Picle.setBounds(540,30,100,40);
		jp1.add(Picle);
		
		JLabel lb4 = new JLabel();                         // ��Ŭ ���� ��ᰳ��
		lb4.setBounds(570,60,50,50);
		lb4.setFont(new Font("", Font.BOLD, 12));
		jp1.add(lb4);
		
		JLabel lb4_1 = new JLabel("��");
		lb4_1.setBounds(600,60,50,50);
		lb4_1.setFont(new Font("",Font.BOLD,12));
		jp1.add(lb4_1);
		try {
			pstmt = con.prepareStatement("select * from ingredient where IngredientName = '��Ŭ'"); // SQL ����
			rs = pstmt.executeQuery(); // ���� �������� rs�� �����Ѵ�.
	
			while (rs.next()) {
				lb4.setText(rs.getString(3));
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Picle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel m = (DefaultTableModel)listResult.getModel();
				m.addRow(new Object[]{menu[3]});
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("Update ingredient set Count = Count - 1 where IngredientName = '��Ŭ'");
					ResultSet results = statement.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
				try {
					pstmt = con.prepareStatement("select * from ingredient where IngredientName = '��Ŭ'"); // SQL ����
					rs = pstmt.executeQuery(); // ���� �������� rs�� �����Ѵ�.
			
					while (rs.next()) {
						lb4.setText(rs.getString(3));
						if(rs.getInt(3) <= 0 ) {
							JOptionPane.showMessageDialog(null, "��ᰡ ���������ϴ�.");
						}
					}
				
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
		JButton SpecialSoce = new JButton("Ư���Ѽҽ�");       // Ư���Ѽҽ�
		SpecialSoce.setBounds(660,30,100,40);
		jp1.add(SpecialSoce);
		
		JLabel lb5 = new JLabel();                         // Ư���Ѽҽ� ���� ��ᰳ��
		lb5.setBounds(690,60,50,50);
		lb5.setFont(new Font("", Font.BOLD, 12));
		jp1.add(lb5);
		
		JLabel lb5_1 = new JLabel("��");
		lb5_1.setBounds(720,60,50,50);
		lb5_1.setFont(new Font("",Font.BOLD,12));
		jp1.add(lb5_1);
		try {
			pstmt = con.prepareStatement("select * from ingredient where IngredientName = 'Ư���Ѽҽ�'"); // SQL ����
			rs = pstmt.executeQuery(); // ���� �������� rs�� �����Ѵ�.
	
			while (rs.next()) {
				lb5.setText(rs.getString(3));
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		SpecialSoce.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel m = (DefaultTableModel)listResult.getModel();
				m.addRow(new Object[]{menu[4]});
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("Update ingredient set Count = Count - 1 where IngredientName = 'Ư���Ѽҽ�'");
					ResultSet results = statement.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
				try {
					pstmt = con.prepareStatement("select * from ingredient where IngredientName = 'Ư���Ѽҽ�'"); // SQL ����
					rs = pstmt.executeQuery(); // ���� �������� rs�� �����Ѵ�.
			
					while (rs.next()) {
						lb5.setText(rs.getString(3));
						if(rs.getInt(3) <= 0 ) {
							JOptionPane.showMessageDialog(null, "��ᰡ ���������ϴ�.");
						}
					}
				
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
		JButton Gamja = new JButton("����");               // ���� 
		Gamja.setBounds(180,100,100,40);
		jp1.add(Gamja);
		
		JLabel lb6 = new JLabel();                         // ���� ���� ��ᰳ��
		lb6.setBounds(210,130,50,50);
		lb6.setFont(new Font("", Font.BOLD, 12));
		jp1.add(lb6);
		
		JLabel lb6_1 = new JLabel("��");
		lb6_1.setBounds(240,130,50,50);
		lb6_1.setFont(new Font("",Font.BOLD,12));
		jp1.add(lb6_1);
		try {
			pstmt = con.prepareStatement("select * from ingredient where IngredientName = '����'"); // SQL ����
			rs = pstmt.executeQuery(); // ���� �������� rs�� �����Ѵ�.
	
			while (rs.next()) {
				lb6.setText(rs.getString(3));
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Gamja.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel m = (DefaultTableModel)listResult.getModel();
				m.addRow(new Object[]{menu[5]});
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("Update ingredient set Count = Count - 1 where IngredientName = '����'");
					ResultSet results = statement.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
				try {
					pstmt = con.prepareStatement("select * from ingredient where IngredientName = '����'"); // SQL ����
					rs = pstmt.executeQuery(); // ���� �������� rs�� �����Ѵ�.
			
					while (rs.next()) {
						lb6.setText(rs.getString(3));	
						if(rs.getInt(3) <= 0 ) {
							JOptionPane.showMessageDialog(null, "��ᰡ ���������ϴ�.");
						}
					}
				
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
		JButton YangPa = new JButton("����");             // ����
		YangPa.setBounds(300,100,100,40);
		jp1.add(YangPa);
		
		JLabel lb7 = new JLabel();                         // ���� ���� ��ᰳ��
		lb7.setBounds(330,130,50,50);
		lb7.setFont(new Font("", Font.BOLD, 12));
		jp1.add(lb7);
		
		JLabel lb7_1 = new JLabel("��");
		lb7_1.setBounds(360,130,50,50);
		lb7_1.setFont(new Font("",Font.BOLD,12));
		jp1.add(lb7_1);
		try {
			pstmt = con.prepareStatement("select * from ingredient where IngredientName = '����'"); // SQL ����
			rs = pstmt.executeQuery(); // ���� �������� rs�� �����Ѵ�.
	
			while (rs.next()) {
				lb7.setText(rs.getString(3));
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		YangPa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel m = (DefaultTableModel)listResult.getModel();
				m.addRow(new Object[]{menu[6]});
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("Update ingredient set Count = Count - 1 where IngredientName = '����'");
					ResultSet results = statement.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
				try {
					pstmt = con.prepareStatement("select * from ingredient where IngredientName = '����'"); // SQL ����
					rs = pstmt.executeQuery(); // ���� �������� rs�� �����Ѵ�.
			
					while (rs.next()) {
						lb7.setText(rs.getString(3));	
						if(rs.getInt(3) <= 0 ) {
							JOptionPane.showMessageDialog(null, "��ᰡ ���������ϴ�.");
						}
					}
				
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
		JButton ChikenPatti = new JButton("ġŲ��Ƽ");      // ġŲ��Ƽ
		ChikenPatti.setBounds(420,100,100,40);
		jp1.add(ChikenPatti);
		
		JLabel lb8 = new JLabel();                         // ġŲ��Ƽ ���� ��ᰳ��
		lb8.setBounds(450,130,50,50);
		lb8.setFont(new Font("", Font.BOLD, 12));
		jp1.add(lb8);
		
		JLabel lb8_1 = new JLabel("��");
		lb8_1.setBounds(480,130,50,50);
		lb8_1.setFont(new Font("",Font.BOLD,12));
		jp1.add(lb8_1);
		try {
			pstmt = con.prepareStatement("select * from ingredient where IngredientName = 'ġŲ��Ƽ'"); // SQL ����
			rs = pstmt.executeQuery(); // ���� �������� rs�� �����Ѵ�.
	
			while (rs.next()) {
				lb8.setText(rs.getString(3));
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ChikenPatti.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel m = (DefaultTableModel)listResult.getModel();
				m.addRow(new Object[]{menu[7]});
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("Update ingredient set Count = Count - 1 where IngredientName = 'ġŲ��Ƽ'");
					ResultSet results = statement.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
				try {
					pstmt = con.prepareStatement("select * from ingredient where IngredientName = 'ġŲ��Ƽ'"); // SQL ����
					rs = pstmt.executeQuery(); // ���� �������� rs�� �����Ѵ�.
			
					while (rs.next()) {
						lb8.setText(rs.getString(3));
						if(rs.getInt(3) <= 0 ) {
							JOptionPane.showMessageDialog(null, "��ᰡ ���������ϴ�.");
						}
					}
				
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
		JButton OjingPatti = new JButton("��¡����Ƽ");     // ��¡����Ƽ
		OjingPatti.setBounds(540,100,100,40);
		jp1.add(OjingPatti);
		
		JLabel lb9 = new JLabel();                         // ��¡����Ƽ ���� ��ᰳ��
		lb9.setBounds(570,130,50,50);
		lb9.setFont(new Font("", Font.BOLD, 12));
		jp1.add(lb9);
		
		JLabel lb9_1 = new JLabel("��");
		lb9_1.setBounds(600,130,50,50);
		lb9_1.setFont(new Font("",Font.BOLD,12));
		jp1.add(lb9_1);
		try {
			pstmt = con.prepareStatement("select * from ingredient where IngredientName = '��¡����Ƽ'"); // SQL ����
			rs = pstmt.executeQuery(); // ���� �������� rs�� �����Ѵ�.
	
			while (rs.next()) {
				lb9.setText(rs.getString(3));
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		OjingPatti.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel m = (DefaultTableModel)listResult.getModel();
				m.addRow(new Object[]{menu[8]});
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("Update ingredient set Count = Count - 1 where IngredientName = '��¡����Ƽ'");
					ResultSet results = statement.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
				try {
					pstmt = con.prepareStatement("select * from ingredient where IngredientName = '��¡����Ƽ'"); // SQL ����
					rs = pstmt.executeQuery(); // ���� �������� rs�� �����Ѵ�.
			
					while (rs.next()) {
						lb9.setText(rs.getString(3));	
						if(rs.getInt(3) <= 0 ) {
							JOptionPane.showMessageDialog(null, "��ᰡ ���������ϴ�.");
						}
					}
				
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
		JButton Egg = new JButton("�ް�");                // �ް�
		Egg.setBounds(660,100,100,40);
		jp1.add(Egg);
		
		JLabel lb10 = new JLabel();                         // �ް� ���� ��ᰳ��
		lb10.setBounds(690,130,50,50);
		lb10.setFont(new Font("", Font.BOLD, 12));
		jp1.add(lb10);
		
		JLabel lb10_1 = new JLabel("��");
		lb10_1.setBounds(720,130,50,50);
		lb10_1.setFont(new Font("",Font.BOLD,12));
		jp1.add(lb10_1);
		try {
			pstmt = con.prepareStatement("select * from ingredient where IngredientName = '�ް�'"); // SQL ����
			rs = pstmt.executeQuery(); // ���� �������� rs�� �����Ѵ�.
	
			while (rs.next()) {
				lb10.setText(rs.getString(3));
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Egg.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel m = (DefaultTableModel)listResult.getModel();
				m.addRow(new Object[]{menu[9]});
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("Update ingredient set Count = Count - 1 where IngredientName = '�ް�'");
					ResultSet results = statement.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
				try {
					pstmt = con.prepareStatement("select * from ingredient where IngredientName = '�ް�'"); // SQL ����
					rs = pstmt.executeQuery(); // ���� �������� rs�� �����Ѵ�.
			
					while (rs.next()) {
						lb10.setText(rs.getString(3));
						if(rs.getInt(3) <= 0 ) {
							JOptionPane.showMessageDialog(null, "��ᰡ ���������ϴ�.");
						}
					}
				
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
		JButton Beken = new JButton("������");              // ������
		Beken.setBounds(180,170,100,40);
		jp1.add(Beken);
		
		JLabel lb11 = new JLabel();                         // ������ ���� ��ᰳ��
		lb11.setBounds(210,200,50,50);
		lb11.setFont(new Font("", Font.BOLD, 12));
		jp1.add(lb11);
		
		JLabel lb11_1 = new JLabel("��");
		lb11_1.setBounds(240,200,50,50);
		lb11_1.setFont(new Font("",Font.BOLD,12));
		jp1.add(lb11_1);
		try {
			pstmt = con.prepareStatement("select * from ingredient where IngredientName = '������'"); // SQL ����
			rs = pstmt.executeQuery(); // ���� �������� rs�� �����Ѵ�.
	
			while (rs.next()) {
				lb11.setText(rs.getString(3));
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Beken.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel m = (DefaultTableModel)listResult.getModel();
				m.addRow(new Object[]{menu[10]});
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("Update ingredient set Count = Count - 1 where IngredientName = '������'");
					ResultSet results = statement.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
				try {
					pstmt = con.prepareStatement("select * from ingredient where IngredientName = '������'"); // SQL ����
					rs = pstmt.executeQuery(); // ���� �������� rs�� �����Ѵ�.
			
					while (rs.next()) {
						lb11.setText(rs.getString(3));	
						if(rs.getInt(3) <= 0 ) {
							JOptionPane.showMessageDialog(null, "��ᰡ ���������ϴ�.");
						}
					}
				
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
		JButton SingYoungYu = new JButton("�Ŀ���");        // �Ŀ���
		SingYoungYu.setBounds(300,170,100,40);
		jp1.add(SingYoungYu);
		
		JLabel lb12 = new JLabel();                         // �Ŀ��� ���� ��ᰳ��
		lb12.setBounds(330,200,50,50);
		lb12.setFont(new Font("", Font.BOLD, 12));
		jp1.add(lb12);
		
		JLabel lb12_1 = new JLabel("��");
		lb12_1.setBounds(360,200,50,50);
		lb12_1.setFont(new Font("",Font.BOLD,12));
		jp1.add(lb12_1);
		try {
			pstmt = con.prepareStatement("select * from ingredient where IngredientName = '�Ŀ���'"); // SQL ����
			rs = pstmt.executeQuery(); // ���� �������� rs�� �����Ѵ�.
	
			while (rs.next()) {
				lb12.setText(rs.getString(3));
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		SingYoungYu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel m = (DefaultTableModel)listResult.getModel();
				m.addRow(new Object[]{menu[11]});
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("Update ingredient set Count = Count - 1 where IngredientName = '�Ŀ���'");
					ResultSet results = statement.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
				try {
					pstmt = con.prepareStatement("select * from ingredient where IngredientName = '�Ŀ���'"); // SQL ����
					rs = pstmt.executeQuery(); // ���� �������� rs�� �����Ѵ�.
			
					while (rs.next()) {
						lb12.setText(rs.getString(3));
						if(rs.getInt(3) <= 0 ) {
							JOptionPane.showMessageDialog(null, "��ᰡ ���������ϴ�.");
						}
					}
				
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
		JButton Cheeze = new JButton("ġ��");             // ġ��
		Cheeze.setBounds(420,170,100,40);
		jp1.add(Cheeze);
		
		JLabel lb13 = new JLabel();                         // ġ�� ���� ��ᰳ��
		lb13.setBounds(450,200,50,50);
		lb13.setFont(new Font("", Font.BOLD, 12));
		jp1.add(lb13);
		
		JLabel lb13_1 = new JLabel("��");
		lb13_1.setBounds(480,200,50,50);
		lb13_1.setFont(new Font("",Font.BOLD,12));
		jp1.add(lb13_1);
		try {
			pstmt = con.prepareStatement("select * from ingredient where IngredientName = 'ġ��'"); // SQL ����
			rs = pstmt.executeQuery(); // ���� �������� rs�� �����Ѵ�.
	
			while (rs.next()) {
				lb13.setText(rs.getString(3));
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Cheeze.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel m = (DefaultTableModel)listResult.getModel();
				m.addRow(new Object[]{menu[12]});
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("Update ingredient set Count = Count - 1 where IngredientName = 'ġ��'");
					ResultSet results = statement.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
				try {
					pstmt = con.prepareStatement("select * from ingredient where IngredientName = 'ġ��'"); // SQL ����
					rs = pstmt.executeQuery(); // ���� �������� rs�� �����Ѵ�.
			
					while (rs.next()) {
						lb13.setText(rs.getString(3));
						if(rs.getInt(3) <= 0 ) {
							JOptionPane.showMessageDialog(null, "��ᰡ ���������ϴ�.");
						}
					}
				
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
		JButton Milk = new JButton("����");            // ����
		Milk.setBounds(540,170,100,40);
		jp1.add(Milk);
		
		JLabel lb14 = new JLabel();                         // ���� ���� ��ᰳ��
		lb14.setBounds(570,200,50,50);
		lb14.setFont(new Font("", Font.BOLD, 12));
		jp1.add(lb14);
		
		JLabel lb14_1 = new JLabel("��");
		lb14_1.setBounds(600,200,50,50);
		lb14_1.setFont(new Font("",Font.BOLD,12));
		jp1.add(lb14_1);
		try {
			pstmt = con.prepareStatement("select * from ingredient where IngredientName = '����'"); // SQL ����
			rs = pstmt.executeQuery(); // ���� �������� rs�� �����Ѵ�.
	
			while (rs.next()) {
				lb14.setText(rs.getString(3));
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Milk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel m = (DefaultTableModel)listResult.getModel();
				m.addRow(new Object[]{menu[13]});
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("Update ingredient set Count = Count - 1 where IngredientName = '����'");
					ResultSet results = statement.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
				try {
					pstmt = con.prepareStatement("select * from ingredient where IngredientName = '����'"); // SQL ����
					rs = pstmt.executeQuery(); // ���� �������� rs�� �����Ѵ�.
			
					while (rs.next()) {
						lb14.setText(rs.getString(3));
						if(rs.getInt(3) <= 0 ) {
							JOptionPane.showMessageDialog(null, "��ᰡ ���������ϴ�.");
						}
					}
				
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
		JButton Sider = new JButton("���̴�");           // ���̴�
		Sider.setBounds(660,170,100,40);
		jp1.add(Sider);
		
		JLabel lb15 = new JLabel();                         // ���̴� ���� ��ᰳ��
		lb15.setBounds(690,200,50,50);
		lb15.setFont(new Font("", Font.BOLD, 12));
		jp1.add(lb15);
		
		JLabel lb15_1 = new JLabel("��");
		lb15_1.setBounds(720,200,50,50);
		lb15_1.setFont(new Font("",Font.BOLD,12));
		jp1.add(lb15_1);
		try {
			pstmt = con.prepareStatement("select * from ingredient where IngredientName = '���̴�'"); // SQL ����
			rs = pstmt.executeQuery(); // ���� �������� rs�� �����Ѵ�.
	
			while (rs.next()) {
				lb15.setText(rs.getString(3));
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Sider.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel m = (DefaultTableModel)listResult.getModel();
				m.addRow(new Object[]{menu[14]});
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("Update ingredient set Count = Count - 1 where IngredientName = '���̴�'");
					ResultSet results = statement.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
				try {
					pstmt = con.prepareStatement("select * from ingredient where IngredientName = '���̴�'"); // SQL ����
					rs = pstmt.executeQuery(); // ���� �������� rs�� �����Ѵ�.
			
					while (rs.next()) {
						lb15.setText(rs.getString(3));
						if(rs.getInt(3) <= 0 ) {
							JOptionPane.showMessageDialog(null, "��ᰡ ���������ϴ�.");
						}
					}
				
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
		JButton Cola = new JButton("�ݶ�");            // �ݶ�
		Cola.setBounds(410,240,100,40);
		jp1.add(Cola);
		
		JLabel lb16 = new JLabel();                         // �ݶ� ���� ��ᰳ��
		lb16.setBounds(440,270,50,50);
		lb16.setFont(new Font("", Font.BOLD, 12));
		jp1.add(lb16);
		
		JLabel lb16_1 = new JLabel("��");
		lb16_1.setBounds(470,270,50,50);
		lb16_1.setFont(new Font("",Font.BOLD,12));
		jp1.add(lb16_1);
		try {
			pstmt = con.prepareStatement("select * from ingredient where IngredientName = '�ݶ�'"); // SQL ����
			rs = pstmt.executeQuery(); // ���� �������� rs�� �����Ѵ�.
	
			while (rs.next()) {
				lb16.setText(rs.getString(3));
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Cola.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel m = (DefaultTableModel)listResult.getModel();
				m.addRow(new Object[]{menu[15]});
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("Update ingredient set Count = Count - 1 where IngredientName = '�ݶ�'");
					ResultSet results = statement.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
				try {
					pstmt = con.prepareStatement("select * from ingredient where IngredientName = '�ݶ�'"); // SQL ����
					rs = pstmt.executeQuery(); // ���� �������� rs�� �����Ѵ�.
			
					while (rs.next()) {
						lb16.setText(rs.getString(3));	
						if(rs.getInt(3) <= 0 ) {
							JOptionPane.showMessageDialog(null, "��ᰡ ���������ϴ�.");
						}
					}
				
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
		JButton Ice = new JButton("����");              // ����
		Ice.setBounds(530,240,100,40);
		jp1.add(Ice);
		
		JLabel lb17 = new JLabel();                         // ���� ���� ��ᰳ��
		lb17.setBounds(560,270,50,50);
		lb17.setFont(new Font("", Font.BOLD, 12));
		jp1.add(lb17);
		
		JLabel lb17_1 = new JLabel("��");
		lb17_1.setBounds(590,270,50,50);
		lb17_1.setFont(new Font("",Font.BOLD,12));
		jp1.add(lb17_1);
		try {
			pstmt = con.prepareStatement("select * from ingredient where IngredientName = '����'"); // SQL ����
			rs = pstmt.executeQuery(); // ���� �������� rs�� �����Ѵ�.
	
			while (rs.next()) {
				lb17.setText(rs.getString(3));
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Ice.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel m = (DefaultTableModel)listResult.getModel();
				m.addRow(new Object[]{menu[16]});
				try {
					Connection con = getIngre();
					PreparedStatement statement = con.prepareStatement("Update ingredient set Count = Count - 1 where IngredientName = '����'");
					ResultSet results = statement.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
				try {
					pstmt = con.prepareStatement("select * from ingredient where IngredientName = '����'"); // SQL ����
					rs = pstmt.executeQuery(); // ���� �������� rs�� �����Ѵ�.
			
					while (rs.next()) {
						lb17.setText(rs.getString(3));	
						if(rs.getInt(3) <= 0 ) {
							JOptionPane.showMessageDialog(null, "��ᰡ ���������ϴ�.");
						}
					}
				
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
		JLabel text = new JLabel("������");        // �ٲ� ������
		text.setBounds(690,410,120,50);
		jp1.add(text);
		
		JButton Retry = new JButton("�ٽø����");   // �ٽø���� ��ư
		Retry.setBounds(650,270,120,50);
		jp1.add(Retry);
		
		Retry.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel m = (DefaultTableModel)listResult.getModel();
				m.setRowCount(0);
				text.setBounds(690,410,120,50);
				text.setText(String.valueOf("������"));
			}
		});
		
		JButton Finish = new JButton("�ϼ�");      // �ϼ� ��ư
		Finish.setBounds(650,340,120,50);
		jp1.add(Finish);
		
		Finish.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int rowCont = listResult.getRowCount();
				text.setBounds(660,410,120,50);
				text.setText(String.valueOf("�߸���������."));
				for(int i=0;i<rowCont;i++) {
					if((String)listResult.getValueAt(i, 0) == "������" && (String)listResult.getValueAt(i+1, 0) == "�Ұ����Ƽ" && (String)listResult.getValueAt(i+2, 0) == "�����" && (String)listResult.getValueAt(i+3, 0) == "Ư���Ѽҽ�") {
						text.setBounds(660,410,120,50);
						text.setText(String.valueOf("�Ұ����� �ϼ�"));
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("Update bank set Money = Money + 2000");    // �Ұ����� �ϼ��� �ݾ��߰�
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("Update food set SellCount = SellCount + 1 where SellFood = '�Ұ�����'");  // �Ұ����� �ϼ��� �Ǹŷ� 1����
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("delete from jumun where JumunName = '�Ұ�����'");
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						DefaultTableModel m = (DefaultTableModel)listResult.getModel();            // �����ĭ ��� �ʱ�ȭ
						m.setRowCount(0);
						DefaultTableModel s = (DefaultTableModel)list.getModel();                  // �ֹ����� ��� �ʱ�ȭ
						s.setRowCount(0);
						try {                                                                                      // �ٽ� �Է¹���
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("Select * From jumun");
							ResultSet results = statement.executeQuery();
							ArrayList<String[]> list = new ArrayList<String[]>();
							while(results.next()) {
								model3.addRow(new Object[] {results.getString("JumunName")});
							}
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
					}
					else if((String)listResult.getValueAt(i, 0) == "������" && (String)listResult.getValueAt(i+1, 0) == "Ư���Ѽҽ�" && (String)listResult.getValueAt(i+2, 0) == "ġ��") {
						text.setBounds(670,410,120,50);
						text.setText(String.valueOf("ġ����� �ϼ�"));
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("Update bank set Money = Money + 2200");    // ġ����� �ϼ��� �ݾ��߰�
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("Update food set SellCount = SellCount + 1 where SellFood = 'ġ�����'");  // ġ����� �ϼ��� �Ǹŷ� 1����
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("delete from jumun where JumunName = 'ġ�����'");
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						DefaultTableModel m = (DefaultTableModel)listResult.getModel();            // �����ĭ ��� �ʱ�ȭ
						m.setRowCount(0);
						DefaultTableModel s = (DefaultTableModel)list.getModel();                  // �ֹ����� ��� �ʱ�ȭ
						s.setRowCount(0);
						try {                                                                                      // �ٽ� �Է¹���
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("Select * From jumun");
							ResultSet results = statement.executeQuery();
							ArrayList<String[]> list = new ArrayList<String[]>();
							while(results.next()) {
								model3.addRow(new Object[] {results.getString("JumunName")});
							}
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
					}
					else if((String)listResult.getValueAt(i, 0) == "������" && (String)listResult.getValueAt(i+1, 0) == "�Ұ����Ƽ" && (String)listResult.getValueAt(i+2, 0) == "�Ұ����Ƽ" && (String)listResult.getValueAt(i+3, 0) == "�����" && (String)listResult.getValueAt(i+4, 0) == "����" && (String)listResult.getValueAt(i+5, 0) == "��Ŭ" && (String)listResult.getValueAt(i+6, 0) == "Ư���Ѽҽ�") {
						text.setBounds(680,410,120,50);
						text.setText(String.valueOf("��� �ϼ�"));
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("Update bank set Money = Money + 4900");    // ��� �ϼ��� �ݾ��߰�
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("Update food set SellCount = SellCount + 1 where SellFood = '���'");  // ��� �ϼ��� �Ǹŷ� 1����
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("delete from jumun where JumunName = '���'");
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						DefaultTableModel m = (DefaultTableModel)listResult.getModel();            // �����ĭ ��� �ʱ�ȭ
						m.setRowCount(0);
						DefaultTableModel s = (DefaultTableModel)list.getModel();                  // �ֹ����� ��� �ʱ�ȭ
						s.setRowCount(0);
						try {                                                                                      // �ٽ� �Է¹���
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("Select * From jumun");
							ResultSet results = statement.executeQuery();
							ArrayList<String[]> list = new ArrayList<String[]>();
							while(results.next()) {
								model3.addRow(new Object[] {results.getString("JumunName")});
							}
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
					}
					else if((String)listResult.getValueAt(i, 0) == "������" && (String)listResult.getValueAt(i+1, 0) == "ġŲ��Ƽ" && (String)listResult.getValueAt(i+2, 0) == "ġ��" && (String)listResult.getValueAt(i+3, 0) == "Ư���Ѽҽ�") {
						text.setBounds(655,410,120,50);
						text.setText(String.valueOf("ũ�����ǹ��� �ϼ�"));
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("Update bank set Money = Money + 3300");    // ũ�����ǹ��� �ϼ��� �ݾ��߰�
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("Update food set SellCount = SellCount + 1 where SellFood = 'ũ�����ǹ���'");  // ũ�����ǹ��� �ϼ��� �Ǹŷ� 1����
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("delete from jumun where JumunName = 'ũ�����ǹ���'");
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						DefaultTableModel m = (DefaultTableModel)listResult.getModel();            // �����ĭ ��� �ʱ�ȭ
						m.setRowCount(0);
						DefaultTableModel s = (DefaultTableModel)list.getModel();                  // �ֹ����� ��� �ʱ�ȭ
						s.setRowCount(0);
						try {                                                                                      // �ٽ� �Է¹���
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("Select * From jumun");
							ResultSet results = statement.executeQuery();
							ArrayList<String[]> list = new ArrayList<String[]>();
							while(results.next()) {
								model3.addRow(new Object[] {results.getString("JumunName")});
							}
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
					}
					else if((String)listResult.getValueAt(i, 0) == "������" && (String)listResult.getValueAt(i+1, 0) == "�����" && (String)listResult.getValueAt(i+2, 0) == "��Ŭ" && (String)listResult.getValueAt(i+3, 0) == "Ư���Ѽҽ�" && (String)listResult.getValueAt(i+4, 0) == "������") {
						text.setBounds(660,410,120,50);
						text.setText(String.valueOf("���������� �ϼ�"));
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("Update bank set Money = Money + 5300");    // ���������� �ϼ��� �ݾ��߰�
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("Update food set SellCount = SellCount + 1 where SellFood = '����������'");  // ���������� �ϼ��� �Ǹŷ� 1����
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("delete from jumun where JumunName = '����������'");
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						DefaultTableModel m = (DefaultTableModel)listResult.getModel();            // �����ĭ ��� �ʱ�ȭ
						m.setRowCount(0);
						DefaultTableModel s = (DefaultTableModel)list.getModel();                  // �ֹ����� ��� �ʱ�ȭ
						s.setRowCount(0);
						try {                                                                                      // �ٽ� �Է¹���
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("Select * From jumun");
							ResultSet results = statement.executeQuery();
							ArrayList<String[]> list = new ArrayList<String[]>();
							while(results.next()) {
								model3.addRow(new Object[] {results.getString("JumunName")});
							}
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
					}
					else if((String)listResult.getValueAt(i, 0) == "������" && (String)listResult.getValueAt(i+1, 0) == "�����" && (String)listResult.getValueAt(i+2, 0) == "Ư���Ѽҽ�" && (String)listResult.getValueAt(i+3, 0) == "��¡����Ƽ") {
						text.setBounds(660,410,120,50);
						text.setText(String.valueOf("��¡����� �ϼ�"));
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("Update bank set Money = Money + 4300");    // ��¡����� �ϼ��� �ݾ��߰�
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("Update food set SellCount = SellCount + 1 where SellFood = '��¡�����'");  // ��¡����� �ϼ��� �Ǹŷ� 1����
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("delete from jumun where JumunName = '��¡�����'");
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						DefaultTableModel m = (DefaultTableModel)listResult.getModel();            // �����ĭ ��� �ʱ�ȭ
						m.setRowCount(0);
						DefaultTableModel s = (DefaultTableModel)list.getModel();                  // �ֹ����� ��� �ʱ�ȭ
						s.setRowCount(0);
						try {                                                                                      // �ٽ� �Է¹���
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("Select * From jumun");
							ResultSet results = statement.executeQuery();
							ArrayList<String[]> list = new ArrayList<String[]>();
							while(results.next()) {
								model3.addRow(new Object[] {results.getString("JumunName")});
							}
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
					}
					else if((String)listResult.getValueAt(i, 0) == "������" && (String)listResult.getValueAt(i+1, 0) == "�����" && (String)listResult.getValueAt(i+2, 0) == "��Ŭ" && (String)listResult.getValueAt(i+3, 0) == "����" && (String)listResult.getValueAt(i+4, 0) == "�Ұ����Ƽ" && (String)listResult.getValueAt(i+5, 0) == "ġŲ��Ƽ") {
						text.setBounds(670,410,120,50);
						text.setText(String.valueOf("������� �ϼ�"));
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("Update bank set Money = Money + 4400");    // ������� �ϼ��� �ݾ��߰�
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("Update food set SellCount = SellCount + 1 where SellFood = '�������'");  // ������� �ϼ��� �Ǹŷ� 1����
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("delete from jumun where JumunName = '�������'");
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						DefaultTableModel m = (DefaultTableModel)listResult.getModel();            // �����ĭ ��� �ʱ�ȭ
						m.setRowCount(0);
						DefaultTableModel s = (DefaultTableModel)list.getModel();                  // �ֹ����� ��� �ʱ�ȭ
						s.setRowCount(0);
						try {                                                                                      // �ٽ� �Է¹���
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("Select * From jumun");
							ResultSet results = statement.executeQuery();
							ArrayList<String[]> list = new ArrayList<String[]>();
							while(results.next()) {
								model3.addRow(new Object[] {results.getString("JumunName")});
							}
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
					}
					else if((String)listResult.getValueAt(i, 0) == "������" && (String)listResult.getValueAt(i+1, 0) == "��Ŭ" && (String)listResult.getValueAt(i+2, 0) == "�ް�" && (String)listResult.getValueAt(i+3, 0) == "ġ��") {
						text.setBounds(655,410,120,50);
						text.setText(String.valueOf("����ġ����� �ϼ�"));
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("Update bank set Money = Money + 3500");    // ����ġ����� �ϼ��� �ݾ��߰�
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("Update food set SellCount = SellCount + 1 where SellFood = '����ġ�����'");  // ����ġ����� �ϼ��� �Ǹŷ� 1����
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("delete from jumun where JumunName = '����ġ�����'");
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						DefaultTableModel m = (DefaultTableModel)listResult.getModel();            // �����ĭ ��� �ʱ�ȭ
						m.setRowCount(0);
						DefaultTableModel s = (DefaultTableModel)list.getModel();                  // �ֹ����� ��� �ʱ�ȭ
						s.setRowCount(0);
						try {                                                                                      // �ٽ� �Է¹���
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("Select * From jumun");
							ResultSet results = statement.executeQuery();
							ArrayList<String[]> list = new ArrayList<String[]>();
							while(results.next()) {
								model3.addRow(new Object[] {results.getString("JumunName")});
							}
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
					}
					else if((String)listResult.getValueAt(i, 0) == "����" && (String)listResult.getValueAt(i+1, 0) == "�Ŀ���") {
						text.setBounds(670,410,120,50);
						text.setText(String.valueOf("����Ƣ�� �ϼ�"));
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("Update bank set Money = Money + 1000");    // ����Ƣ�� �ϼ��� �ݾ��߰�
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("Update food set SellCount = SellCount + 1 where SellFood = '����Ƣ��'");  // ����Ƣ�� �ϼ��� �Ǹŷ� 1����
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("delete from jumun where JumunName = '����Ƣ��'");
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						DefaultTableModel m = (DefaultTableModel)listResult.getModel();            // �����ĭ ��� �ʱ�ȭ
						m.setRowCount(0);
						DefaultTableModel s = (DefaultTableModel)list.getModel();                  // �ֹ����� ��� �ʱ�ȭ
						s.setRowCount(0);
						try {                                                                                      // �ٽ� �Է¹���
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("Select * From jumun");
							ResultSet results = statement.executeQuery();
							ArrayList<String[]> list = new ArrayList<String[]>();
							while(results.next()) {
								model3.addRow(new Object[] {results.getString("JumunName")});
							}
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
					}
					else if((String)listResult.getValueAt(i, 0) == "�ݶ�" && (String)listResult.getValueAt(i+1, 0) == "����") {
						text.setBounds(680,410,120,50);
						text.setText(String.valueOf("�ݶ� �ϼ�"));
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("Update bank set Money = Money + 1300");    // �ݶ� �ϼ��� �ݾ��߰�
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("Update food set SellCount = SellCount + 1 where SellFood = '�ݶ�'");  // �ݶ� �ϼ��� �Ǹŷ� 1����
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("delete from jumun where JumunName = '�ݶ�'");
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						DefaultTableModel m = (DefaultTableModel)listResult.getModel();            // �����ĭ ��� �ʱ�ȭ
						m.setRowCount(0);
						DefaultTableModel s = (DefaultTableModel)list.getModel();                  // �ֹ����� ��� �ʱ�ȭ
						s.setRowCount(0);
						try {                                                                                      // �ٽ� �Է¹���
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("Select * From jumun");
							ResultSet results = statement.executeQuery();
							ArrayList<String[]> list = new ArrayList<String[]>();
							while(results.next()) {
								model3.addRow(new Object[] {results.getString("JumunName")});
							}
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
					}
					else if((String)listResult.getValueAt(i, 0) == "���̴�" && (String)listResult.getValueAt(i+1, 0) == "����") {
						text.setBounds(680,410,120,50);
						text.setText(String.valueOf("���̴� �ϼ�"));
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("Update bank set Money = Money + 1300");    // ���̴� �ϼ��� �ݾ��߰�
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("Update food set SellCount = SellCount + 1 where SellFood = '���̴�'");  // ���̴� �ϼ��� �Ǹŷ� 1����
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("delete from jumun where JumunName = '���̴�'");
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						DefaultTableModel m = (DefaultTableModel)listResult.getModel();            // �����ĭ ��� �ʱ�ȭ
						m.setRowCount(0);
						DefaultTableModel s = (DefaultTableModel)list.getModel();                  // �ֹ����� ��� �ʱ�ȭ
						s.setRowCount(0);
						try {                                                                                      // �ٽ� �Է¹���
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("Select * From jumun");
							ResultSet results = statement.executeQuery();
							ArrayList<String[]> list = new ArrayList<String[]>();
							while(results.next()) {
								model3.addRow(new Object[] {results.getString("JumunName")});
							}
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
					}
					else if((String)listResult.getValueAt(i, 0) == "����" && (String)listResult.getValueAt(i+1, 0) == "����") {
						text.setBounds(660,410,120,50);
						text.setText(String.valueOf("���̽�ũ�� �ϼ�"));
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("Update bank set Money = Money + 500");    // ���̽�ũ�� �ϼ��� �ݾ��߰�
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("Update food set SellCount = SellCount + 1 where SellFood = '���̽�ũ��'");  // ���̽�ũ�� �ϼ��� �Ǹŷ� 1����
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("delete from jumun where JumunName = '���̽�ũ��'");
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						DefaultTableModel m = (DefaultTableModel)listResult.getModel();            // �����ĭ ��� �ʱ�ȭ
						m.setRowCount(0);
						DefaultTableModel s = (DefaultTableModel)list.getModel();                  // �ֹ����� ��� �ʱ�ȭ
						s.setRowCount(0);
						try {                                                                                      // �ٽ� �Է¹���
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("Select * From jumun");
							ResultSet results = statement.executeQuery();
							ArrayList<String[]> list = new ArrayList<String[]>();
							while(results.next()) {
								model3.addRow(new Object[] {results.getString("JumunName")});
							}
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						
					}
					
				}
			}
		});
		
		JButton LobiGo = new JButton("�κ�â����");    // �κ�â�����ư
		LobiGo.setBounds(650,480,120,50);
		jp1.add(LobiGo);
		
		LobiGo.addActionListener(new MainClass(){           // �κ�â�����̵�
			public void actionPerformed(ActionEvent e) {
				FirstWindow W1 = new FirstWindow();
		         W1.setVisible(true);
		         dispose();
				}
			});
		
		ct.add(jl1, BorderLayout.NORTH);
		ct.add(jp1);
		
		setBounds(1,1,800,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setVisible(true);
	}
}

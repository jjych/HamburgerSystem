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
	ResultSetMetaData rsmd = null; // resultset 에 대한 컬럼의 속성이나 타입 정보를 사용한다.
//	Ingredient2 stock =null;
//	Ingredient I = null;
	
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
	public static String[][] getJumun(){                  // 주문DB 불러오기
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
	
	public void con2() {                       // food DB연동
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
//		stock = I.getstock();	//서버에서 재고 데이터 넣어주기
		Container ct = getContentPane();
		setTitle("주방");
		setSize(800,600);
		
		String[] menu = {
				"참깨빵","불고기패티","양상추","피클",
				"특별한소스","감자","양파","치킨패티",
				"오징어패티","달걀","베이컨","식용유",
				"치즈","우유","사이다","콜라","얼음"};
		
		JLabel jl1 = new JLabel("주방");
		jl1.setHorizontalAlignment(JLabel.CENTER);
		
		JPanel jp1 = new JPanel();
		jp1.setLayout(null);
		
		JLabel jlist = new JLabel("주문내역");             // 주문내역 추가
		jlist.setBounds(50,5,100,20);
		jp1.add(jlist);
		
		String[][] dataJaro = ThirdWindow.getJumun();            // DB연동된 테이블
		String[] ColName3 = new String[] {"주문메뉴"};
		DefaultTableModel model3 = new DefaultTableModel(dataJaro,ColName3);
		JTable list = new JTable(model3);                      // 주문내역리스트 추가
		list.setFont(new Font("Sanserif", Font.BOLD,12));
		list.setBounds(1,30,150,150);
		jp1.add(list);
		
		String [] ColName = {"재료"};
		String [][] Data = null;
		DefaultTableModel model = new DefaultTableModel(Data,ColName);
		JTable listResult = new JTable(model);                  // 만드는 리스트
		listResult.setFont(new Font("Sanserif", Font.BOLD,12));
		listResult.setBounds(400,320,230,220);
		jp1.add(listResult);
		
		JLabel rasipy = new JLabel("레시피");              // 레시피 라벨
		rasipy.setBounds(60,200,100,20);
		jp1.add(rasipy);
		
		// 레시피 라벨 설명서
		JLabel sulmeng = new JLabel("<html>불고기버거 : 참깨빵 + 불고기패티 + 양상추 + 특별한소스 <br>"
				                    + "치즈버거 : 참깨빵 + 특별한소스 + 치즈<br>"
				                    + "빅맥 : 참깨빵 + 불고기패티 + 불고기패티 + 양상추 + 양파 + 피클 + 특별한소스<br>"
				                    + "크리스피버거 : 참깨빵 + 치킨패티 + 치즈 + 특별한소스<br>"
				                    + "베이컨버거 : 참깨빵 + 양상추 + 피클 + 특별한소스 + 베이컨<br>"
				                    + "오징어버거 : 참깨빵 + 양상추 + 특별한소스 + 오징어패티<br>"
				                    + "더블버거 : 참깨빵 + 양상추 + 피클 + 양파 + 불고기패티 + 치킨패티<br>"
				                    + "에그치즈버거 : 참깨빵 + 피클 + 달걀 + 치즈<br>"
				                    + "감자튀김 : 감자 + 식용유<br>"
				                    + "콜라 : 콜라 + 얼음<br>"
				                    + "사이다 : 사이다 + 얼음<br>"
				                    + "아이스크림 : 우유 + 얼음</html>");
		sulmeng.setBounds(10,240,300,300);
		sulmeng.setFont(new Font("굴림",Font.BOLD,12));
		jp1.add(sulmeng);
		
		JButton Bread = new JButton("참깨빵");             // 참깨빵
		Bread.setBounds(180,30,100,40);
		jp1.add(Bread);
		
		JLabel lb1 = new JLabel();                         // 참깨빵 남은 재료개수
		lb1.setBounds(210,60,50,50);
		lb1.setFont(new Font("", Font.BOLD, 12));
		jp1.add(lb1);
		
		JLabel lb1_1 = new JLabel("개");
		lb1_1.setBounds(240,60,50,50);
		lb1_1.setFont(new Font("",Font.BOLD,12));
		jp1.add(lb1_1);
		try {
			pstmt = con.prepareStatement("select * from ingredient where IngredientName = '참깨빵'"); // SQL 질의
			rs = pstmt.executeQuery(); // 쿼리 실행결과를 rs에 저장한다.
	
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
					PreparedStatement statement = con.prepareStatement("Update ingredient set Count = Count - 1 where IngredientName = '참깨빵'");
					ResultSet results = statement.executeQuery();
					
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
				try {
					pstmt = con.prepareStatement("select * from ingredient where IngredientName = '참깨빵'"); // SQL 질의
					rs = pstmt.executeQuery(); // 쿼리 실행결과를 rs에 저장한다.
			
					while (rs.next()) {
						lb1.setText(rs.getString(3));
						if(rs.getInt(3) <= 0 ) {
							JOptionPane.showMessageDialog(null, "재료가 떨어졌습니다.");
						}
					}
				
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
		JButton BulgogiPatti = new JButton("불고기패티");        // 불고기패티
		BulgogiPatti.setBounds(300,30,100,40);
		jp1.add(BulgogiPatti);
		
		JLabel lb2 = new JLabel();                         // 불고기패티 남은 재료개수
		lb2.setBounds(330,60,50,50);
		lb2.setFont(new Font("", Font.BOLD, 12));
		jp1.add(lb2);
		
		JLabel lb2_1 = new JLabel("개");
		lb2_1.setBounds(360,60,50,50);
		lb2_1.setFont(new Font("",Font.BOLD,12));
		jp1.add(lb2_1);
		try {
			pstmt = con.prepareStatement("select * from ingredient where IngredientName = '불고기패티'"); // SQL 질의
			rs = pstmt.executeQuery(); // 쿼리 실행결과를 rs에 저장한다.
	
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
					PreparedStatement statement = con.prepareStatement("Update ingredient set Count = Count - 1 where IngredientName = '불고기패티'");
					ResultSet results = statement.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
				try {
					pstmt = con.prepareStatement("select * from ingredient where IngredientName = '불고기패티'"); // SQL 질의
					rs = pstmt.executeQuery(); // 쿼리 실행결과를 rs에 저장한다.
			
					while (rs.next()) {
						lb2.setText(rs.getString(3));
						if(rs.getInt(3) <= 0 ) {
							JOptionPane.showMessageDialog(null, "재료가 떨어졌습니다.");
						}
					}
				
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
		JButton YangSangChu = new JButton("양상추");           // 양상추
		YangSangChu.setBounds(420,30,100,40);
		jp1.add(YangSangChu);
		
		JLabel lb3 = new JLabel();                         // 양상추 남은 재료개수
		lb3.setBounds(450,60,50,50);
		lb3.setFont(new Font("", Font.BOLD, 12));
		jp1.add(lb3);
		
		JLabel lb3_1 = new JLabel("개");
		lb3_1.setBounds(480,60,50,50);
		lb3_1.setFont(new Font("",Font.BOLD,12));
		jp1.add(lb3_1);
		try {
			pstmt = con.prepareStatement("select * from ingredient where IngredientName = '양상추'"); // SQL 질의
			rs = pstmt.executeQuery(); // 쿼리 실행결과를 rs에 저장한다.
	
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
					PreparedStatement statement = con.prepareStatement("Update ingredient set Count = Count - 1 where IngredientName = '양상추'");
					ResultSet results = statement.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
				try {
					pstmt = con.prepareStatement("select * from ingredient where IngredientName = '양상추'"); // SQL 질의
					rs = pstmt.executeQuery(); // 쿼리 실행결과를 rs에 저장한다.
			
					while (rs.next()) {
						lb3.setText(rs.getString(3));
						if(rs.getInt(3) <= 0 ) {
							JOptionPane.showMessageDialog(null, "재료가 떨어졌습니다.");
						}
					}
				
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
		JButton Picle = new JButton("피클");                // 피클
		Picle.setBounds(540,30,100,40);
		jp1.add(Picle);
		
		JLabel lb4 = new JLabel();                         // 피클 남은 재료개수
		lb4.setBounds(570,60,50,50);
		lb4.setFont(new Font("", Font.BOLD, 12));
		jp1.add(lb4);
		
		JLabel lb4_1 = new JLabel("개");
		lb4_1.setBounds(600,60,50,50);
		lb4_1.setFont(new Font("",Font.BOLD,12));
		jp1.add(lb4_1);
		try {
			pstmt = con.prepareStatement("select * from ingredient where IngredientName = '피클'"); // SQL 질의
			rs = pstmt.executeQuery(); // 쿼리 실행결과를 rs에 저장한다.
	
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
					PreparedStatement statement = con.prepareStatement("Update ingredient set Count = Count - 1 where IngredientName = '피클'");
					ResultSet results = statement.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
				try {
					pstmt = con.prepareStatement("select * from ingredient where IngredientName = '피클'"); // SQL 질의
					rs = pstmt.executeQuery(); // 쿼리 실행결과를 rs에 저장한다.
			
					while (rs.next()) {
						lb4.setText(rs.getString(3));
						if(rs.getInt(3) <= 0 ) {
							JOptionPane.showMessageDialog(null, "재료가 떨어졌습니다.");
						}
					}
				
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
		JButton SpecialSoce = new JButton("특별한소스");       // 특별한소스
		SpecialSoce.setBounds(660,30,100,40);
		jp1.add(SpecialSoce);
		
		JLabel lb5 = new JLabel();                         // 특별한소스 남은 재료개수
		lb5.setBounds(690,60,50,50);
		lb5.setFont(new Font("", Font.BOLD, 12));
		jp1.add(lb5);
		
		JLabel lb5_1 = new JLabel("개");
		lb5_1.setBounds(720,60,50,50);
		lb5_1.setFont(new Font("",Font.BOLD,12));
		jp1.add(lb5_1);
		try {
			pstmt = con.prepareStatement("select * from ingredient where IngredientName = '특별한소스'"); // SQL 질의
			rs = pstmt.executeQuery(); // 쿼리 실행결과를 rs에 저장한다.
	
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
					PreparedStatement statement = con.prepareStatement("Update ingredient set Count = Count - 1 where IngredientName = '특별한소스'");
					ResultSet results = statement.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
				try {
					pstmt = con.prepareStatement("select * from ingredient where IngredientName = '특별한소스'"); // SQL 질의
					rs = pstmt.executeQuery(); // 쿼리 실행결과를 rs에 저장한다.
			
					while (rs.next()) {
						lb5.setText(rs.getString(3));
						if(rs.getInt(3) <= 0 ) {
							JOptionPane.showMessageDialog(null, "재료가 떨어졌습니다.");
						}
					}
				
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
		JButton Gamja = new JButton("감자");               // 감자 
		Gamja.setBounds(180,100,100,40);
		jp1.add(Gamja);
		
		JLabel lb6 = new JLabel();                         // 감자 남은 재료개수
		lb6.setBounds(210,130,50,50);
		lb6.setFont(new Font("", Font.BOLD, 12));
		jp1.add(lb6);
		
		JLabel lb6_1 = new JLabel("개");
		lb6_1.setBounds(240,130,50,50);
		lb6_1.setFont(new Font("",Font.BOLD,12));
		jp1.add(lb6_1);
		try {
			pstmt = con.prepareStatement("select * from ingredient where IngredientName = '감자'"); // SQL 질의
			rs = pstmt.executeQuery(); // 쿼리 실행결과를 rs에 저장한다.
	
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
					PreparedStatement statement = con.prepareStatement("Update ingredient set Count = Count - 1 where IngredientName = '감자'");
					ResultSet results = statement.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
				try {
					pstmt = con.prepareStatement("select * from ingredient where IngredientName = '감자'"); // SQL 질의
					rs = pstmt.executeQuery(); // 쿼리 실행결과를 rs에 저장한다.
			
					while (rs.next()) {
						lb6.setText(rs.getString(3));	
						if(rs.getInt(3) <= 0 ) {
							JOptionPane.showMessageDialog(null, "재료가 떨어졌습니다.");
						}
					}
				
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
		JButton YangPa = new JButton("양파");             // 양파
		YangPa.setBounds(300,100,100,40);
		jp1.add(YangPa);
		
		JLabel lb7 = new JLabel();                         // 양파 남은 재료개수
		lb7.setBounds(330,130,50,50);
		lb7.setFont(new Font("", Font.BOLD, 12));
		jp1.add(lb7);
		
		JLabel lb7_1 = new JLabel("개");
		lb7_1.setBounds(360,130,50,50);
		lb7_1.setFont(new Font("",Font.BOLD,12));
		jp1.add(lb7_1);
		try {
			pstmt = con.prepareStatement("select * from ingredient where IngredientName = '양파'"); // SQL 질의
			rs = pstmt.executeQuery(); // 쿼리 실행결과를 rs에 저장한다.
	
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
					PreparedStatement statement = con.prepareStatement("Update ingredient set Count = Count - 1 where IngredientName = '양파'");
					ResultSet results = statement.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
				try {
					pstmt = con.prepareStatement("select * from ingredient where IngredientName = '양파'"); // SQL 질의
					rs = pstmt.executeQuery(); // 쿼리 실행결과를 rs에 저장한다.
			
					while (rs.next()) {
						lb7.setText(rs.getString(3));	
						if(rs.getInt(3) <= 0 ) {
							JOptionPane.showMessageDialog(null, "재료가 떨어졌습니다.");
						}
					}
				
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
		JButton ChikenPatti = new JButton("치킨패티");      // 치킨패티
		ChikenPatti.setBounds(420,100,100,40);
		jp1.add(ChikenPatti);
		
		JLabel lb8 = new JLabel();                         // 치킨패티 남은 재료개수
		lb8.setBounds(450,130,50,50);
		lb8.setFont(new Font("", Font.BOLD, 12));
		jp1.add(lb8);
		
		JLabel lb8_1 = new JLabel("개");
		lb8_1.setBounds(480,130,50,50);
		lb8_1.setFont(new Font("",Font.BOLD,12));
		jp1.add(lb8_1);
		try {
			pstmt = con.prepareStatement("select * from ingredient where IngredientName = '치킨패티'"); // SQL 질의
			rs = pstmt.executeQuery(); // 쿼리 실행결과를 rs에 저장한다.
	
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
					PreparedStatement statement = con.prepareStatement("Update ingredient set Count = Count - 1 where IngredientName = '치킨패티'");
					ResultSet results = statement.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
				try {
					pstmt = con.prepareStatement("select * from ingredient where IngredientName = '치킨패티'"); // SQL 질의
					rs = pstmt.executeQuery(); // 쿼리 실행결과를 rs에 저장한다.
			
					while (rs.next()) {
						lb8.setText(rs.getString(3));
						if(rs.getInt(3) <= 0 ) {
							JOptionPane.showMessageDialog(null, "재료가 떨어졌습니다.");
						}
					}
				
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
		JButton OjingPatti = new JButton("오징어패티");     // 오징어패티
		OjingPatti.setBounds(540,100,100,40);
		jp1.add(OjingPatti);
		
		JLabel lb9 = new JLabel();                         // 오징어패티 남은 재료개수
		lb9.setBounds(570,130,50,50);
		lb9.setFont(new Font("", Font.BOLD, 12));
		jp1.add(lb9);
		
		JLabel lb9_1 = new JLabel("개");
		lb9_1.setBounds(600,130,50,50);
		lb9_1.setFont(new Font("",Font.BOLD,12));
		jp1.add(lb9_1);
		try {
			pstmt = con.prepareStatement("select * from ingredient where IngredientName = '오징어패티'"); // SQL 질의
			rs = pstmt.executeQuery(); // 쿼리 실행결과를 rs에 저장한다.
	
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
					PreparedStatement statement = con.prepareStatement("Update ingredient set Count = Count - 1 where IngredientName = '오징어패티'");
					ResultSet results = statement.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
				try {
					pstmt = con.prepareStatement("select * from ingredient where IngredientName = '오징어패티'"); // SQL 질의
					rs = pstmt.executeQuery(); // 쿼리 실행결과를 rs에 저장한다.
			
					while (rs.next()) {
						lb9.setText(rs.getString(3));	
						if(rs.getInt(3) <= 0 ) {
							JOptionPane.showMessageDialog(null, "재료가 떨어졌습니다.");
						}
					}
				
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
		JButton Egg = new JButton("달걀");                // 달걀
		Egg.setBounds(660,100,100,40);
		jp1.add(Egg);
		
		JLabel lb10 = new JLabel();                         // 달걀 남은 재료개수
		lb10.setBounds(690,130,50,50);
		lb10.setFont(new Font("", Font.BOLD, 12));
		jp1.add(lb10);
		
		JLabel lb10_1 = new JLabel("개");
		lb10_1.setBounds(720,130,50,50);
		lb10_1.setFont(new Font("",Font.BOLD,12));
		jp1.add(lb10_1);
		try {
			pstmt = con.prepareStatement("select * from ingredient where IngredientName = '달걀'"); // SQL 질의
			rs = pstmt.executeQuery(); // 쿼리 실행결과를 rs에 저장한다.
	
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
					PreparedStatement statement = con.prepareStatement("Update ingredient set Count = Count - 1 where IngredientName = '달걀'");
					ResultSet results = statement.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
				try {
					pstmt = con.prepareStatement("select * from ingredient where IngredientName = '달걀'"); // SQL 질의
					rs = pstmt.executeQuery(); // 쿼리 실행결과를 rs에 저장한다.
			
					while (rs.next()) {
						lb10.setText(rs.getString(3));
						if(rs.getInt(3) <= 0 ) {
							JOptionPane.showMessageDialog(null, "재료가 떨어졌습니다.");
						}
					}
				
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
		JButton Beken = new JButton("베이컨");              // 베이컨
		Beken.setBounds(180,170,100,40);
		jp1.add(Beken);
		
		JLabel lb11 = new JLabel();                         // 베이컨 남은 재료개수
		lb11.setBounds(210,200,50,50);
		lb11.setFont(new Font("", Font.BOLD, 12));
		jp1.add(lb11);
		
		JLabel lb11_1 = new JLabel("개");
		lb11_1.setBounds(240,200,50,50);
		lb11_1.setFont(new Font("",Font.BOLD,12));
		jp1.add(lb11_1);
		try {
			pstmt = con.prepareStatement("select * from ingredient where IngredientName = '베이컨'"); // SQL 질의
			rs = pstmt.executeQuery(); // 쿼리 실행결과를 rs에 저장한다.
	
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
					PreparedStatement statement = con.prepareStatement("Update ingredient set Count = Count - 1 where IngredientName = '베이컨'");
					ResultSet results = statement.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
				try {
					pstmt = con.prepareStatement("select * from ingredient where IngredientName = '베이컨'"); // SQL 질의
					rs = pstmt.executeQuery(); // 쿼리 실행결과를 rs에 저장한다.
			
					while (rs.next()) {
						lb11.setText(rs.getString(3));	
						if(rs.getInt(3) <= 0 ) {
							JOptionPane.showMessageDialog(null, "재료가 떨어졌습니다.");
						}
					}
				
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
		JButton SingYoungYu = new JButton("식용유");        // 식용유
		SingYoungYu.setBounds(300,170,100,40);
		jp1.add(SingYoungYu);
		
		JLabel lb12 = new JLabel();                         // 식용유 남은 재료개수
		lb12.setBounds(330,200,50,50);
		lb12.setFont(new Font("", Font.BOLD, 12));
		jp1.add(lb12);
		
		JLabel lb12_1 = new JLabel("개");
		lb12_1.setBounds(360,200,50,50);
		lb12_1.setFont(new Font("",Font.BOLD,12));
		jp1.add(lb12_1);
		try {
			pstmt = con.prepareStatement("select * from ingredient where IngredientName = '식용유'"); // SQL 질의
			rs = pstmt.executeQuery(); // 쿼리 실행결과를 rs에 저장한다.
	
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
					PreparedStatement statement = con.prepareStatement("Update ingredient set Count = Count - 1 where IngredientName = '식용유'");
					ResultSet results = statement.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
				try {
					pstmt = con.prepareStatement("select * from ingredient where IngredientName = '식용유'"); // SQL 질의
					rs = pstmt.executeQuery(); // 쿼리 실행결과를 rs에 저장한다.
			
					while (rs.next()) {
						lb12.setText(rs.getString(3));
						if(rs.getInt(3) <= 0 ) {
							JOptionPane.showMessageDialog(null, "재료가 떨어졌습니다.");
						}
					}
				
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
		JButton Cheeze = new JButton("치즈");             // 치즈
		Cheeze.setBounds(420,170,100,40);
		jp1.add(Cheeze);
		
		JLabel lb13 = new JLabel();                         // 치즈 남은 재료개수
		lb13.setBounds(450,200,50,50);
		lb13.setFont(new Font("", Font.BOLD, 12));
		jp1.add(lb13);
		
		JLabel lb13_1 = new JLabel("개");
		lb13_1.setBounds(480,200,50,50);
		lb13_1.setFont(new Font("",Font.BOLD,12));
		jp1.add(lb13_1);
		try {
			pstmt = con.prepareStatement("select * from ingredient where IngredientName = '치즈'"); // SQL 질의
			rs = pstmt.executeQuery(); // 쿼리 실행결과를 rs에 저장한다.
	
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
					PreparedStatement statement = con.prepareStatement("Update ingredient set Count = Count - 1 where IngredientName = '치즈'");
					ResultSet results = statement.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
				try {
					pstmt = con.prepareStatement("select * from ingredient where IngredientName = '치즈'"); // SQL 질의
					rs = pstmt.executeQuery(); // 쿼리 실행결과를 rs에 저장한다.
			
					while (rs.next()) {
						lb13.setText(rs.getString(3));
						if(rs.getInt(3) <= 0 ) {
							JOptionPane.showMessageDialog(null, "재료가 떨어졌습니다.");
						}
					}
				
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
		JButton Milk = new JButton("우유");            // 우유
		Milk.setBounds(540,170,100,40);
		jp1.add(Milk);
		
		JLabel lb14 = new JLabel();                         // 우유 남은 재료개수
		lb14.setBounds(570,200,50,50);
		lb14.setFont(new Font("", Font.BOLD, 12));
		jp1.add(lb14);
		
		JLabel lb14_1 = new JLabel("개");
		lb14_1.setBounds(600,200,50,50);
		lb14_1.setFont(new Font("",Font.BOLD,12));
		jp1.add(lb14_1);
		try {
			pstmt = con.prepareStatement("select * from ingredient where IngredientName = '우유'"); // SQL 질의
			rs = pstmt.executeQuery(); // 쿼리 실행결과를 rs에 저장한다.
	
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
					PreparedStatement statement = con.prepareStatement("Update ingredient set Count = Count - 1 where IngredientName = '우유'");
					ResultSet results = statement.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
				try {
					pstmt = con.prepareStatement("select * from ingredient where IngredientName = '우유'"); // SQL 질의
					rs = pstmt.executeQuery(); // 쿼리 실행결과를 rs에 저장한다.
			
					while (rs.next()) {
						lb14.setText(rs.getString(3));
						if(rs.getInt(3) <= 0 ) {
							JOptionPane.showMessageDialog(null, "재료가 떨어졌습니다.");
						}
					}
				
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
		JButton Sider = new JButton("사이다");           // 사이다
		Sider.setBounds(660,170,100,40);
		jp1.add(Sider);
		
		JLabel lb15 = new JLabel();                         // 사이다 남은 재료개수
		lb15.setBounds(690,200,50,50);
		lb15.setFont(new Font("", Font.BOLD, 12));
		jp1.add(lb15);
		
		JLabel lb15_1 = new JLabel("개");
		lb15_1.setBounds(720,200,50,50);
		lb15_1.setFont(new Font("",Font.BOLD,12));
		jp1.add(lb15_1);
		try {
			pstmt = con.prepareStatement("select * from ingredient where IngredientName = '사이다'"); // SQL 질의
			rs = pstmt.executeQuery(); // 쿼리 실행결과를 rs에 저장한다.
	
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
					PreparedStatement statement = con.prepareStatement("Update ingredient set Count = Count - 1 where IngredientName = '사이다'");
					ResultSet results = statement.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
				try {
					pstmt = con.prepareStatement("select * from ingredient where IngredientName = '사이다'"); // SQL 질의
					rs = pstmt.executeQuery(); // 쿼리 실행결과를 rs에 저장한다.
			
					while (rs.next()) {
						lb15.setText(rs.getString(3));
						if(rs.getInt(3) <= 0 ) {
							JOptionPane.showMessageDialog(null, "재료가 떨어졌습니다.");
						}
					}
				
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
		JButton Cola = new JButton("콜라");            // 콜라
		Cola.setBounds(410,240,100,40);
		jp1.add(Cola);
		
		JLabel lb16 = new JLabel();                         // 콜라 남은 재료개수
		lb16.setBounds(440,270,50,50);
		lb16.setFont(new Font("", Font.BOLD, 12));
		jp1.add(lb16);
		
		JLabel lb16_1 = new JLabel("개");
		lb16_1.setBounds(470,270,50,50);
		lb16_1.setFont(new Font("",Font.BOLD,12));
		jp1.add(lb16_1);
		try {
			pstmt = con.prepareStatement("select * from ingredient where IngredientName = '콜라'"); // SQL 질의
			rs = pstmt.executeQuery(); // 쿼리 실행결과를 rs에 저장한다.
	
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
					PreparedStatement statement = con.prepareStatement("Update ingredient set Count = Count - 1 where IngredientName = '콜라'");
					ResultSet results = statement.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
				try {
					pstmt = con.prepareStatement("select * from ingredient where IngredientName = '콜라'"); // SQL 질의
					rs = pstmt.executeQuery(); // 쿼리 실행결과를 rs에 저장한다.
			
					while (rs.next()) {
						lb16.setText(rs.getString(3));	
						if(rs.getInt(3) <= 0 ) {
							JOptionPane.showMessageDialog(null, "재료가 떨어졌습니다.");
						}
					}
				
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
		JButton Ice = new JButton("얼음");              // 얼음
		Ice.setBounds(530,240,100,40);
		jp1.add(Ice);
		
		JLabel lb17 = new JLabel();                         // 얼음 남은 재료개수
		lb17.setBounds(560,270,50,50);
		lb17.setFont(new Font("", Font.BOLD, 12));
		jp1.add(lb17);
		
		JLabel lb17_1 = new JLabel("개");
		lb17_1.setBounds(590,270,50,50);
		lb17_1.setFont(new Font("",Font.BOLD,12));
		jp1.add(lb17_1);
		try {
			pstmt = con.prepareStatement("select * from ingredient where IngredientName = '얼음'"); // SQL 질의
			rs = pstmt.executeQuery(); // 쿼리 실행결과를 rs에 저장한다.
	
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
					PreparedStatement statement = con.prepareStatement("Update ingredient set Count = Count - 1 where IngredientName = '얼음'");
					ResultSet results = statement.executeQuery();
				
					}catch(Exception e1) {
						System.out.println(e1.getMessage());
				   }
				try {
					pstmt = con.prepareStatement("select * from ingredient where IngredientName = '얼음'"); // SQL 질의
					rs = pstmt.executeQuery(); // 쿼리 실행결과를 rs에 저장한다.
			
					while (rs.next()) {
						lb17.setText(rs.getString(3));	
						if(rs.getInt(3) <= 0 ) {
							JOptionPane.showMessageDialog(null, "재료가 떨어졌습니다.");
						}
					}
				
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
		JLabel text = new JLabel("제조중");        // 바뀔 문구라벨
		text.setBounds(690,410,120,50);
		jp1.add(text);
		
		JButton Retry = new JButton("다시만들기");   // 다시만들기 버튼
		Retry.setBounds(650,270,120,50);
		jp1.add(Retry);
		
		Retry.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel m = (DefaultTableModel)listResult.getModel();
				m.setRowCount(0);
				text.setBounds(690,410,120,50);
				text.setText(String.valueOf("제조중"));
			}
		});
		
		JButton Finish = new JButton("완성");      // 완성 버튼
		Finish.setBounds(650,340,120,50);
		jp1.add(Finish);
		
		Finish.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int rowCont = listResult.getRowCount();
				text.setBounds(660,410,120,50);
				text.setText(String.valueOf("잘못만들었어요."));
				for(int i=0;i<rowCont;i++) {
					if((String)listResult.getValueAt(i, 0) == "참깨빵" && (String)listResult.getValueAt(i+1, 0) == "불고기패티" && (String)listResult.getValueAt(i+2, 0) == "양상추" && (String)listResult.getValueAt(i+3, 0) == "특별한소스") {
						text.setBounds(660,410,120,50);
						text.setText(String.valueOf("불고기버거 완성"));
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("Update bank set Money = Money + 2000");    // 불고기버거 완성시 금액추가
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("Update food set SellCount = SellCount + 1 where SellFood = '불고기버거'");  // 불고기버거 완성시 판매량 1증가
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("delete from jumun where JumunName = '불고기버거'");
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						DefaultTableModel m = (DefaultTableModel)listResult.getModel();            // 재료기록칸 목록 초기화
						m.setRowCount(0);
						DefaultTableModel s = (DefaultTableModel)list.getModel();                  // 주문내역 목록 초기화
						s.setRowCount(0);
						try {                                                                                      // 다시 입력받음
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
					else if((String)listResult.getValueAt(i, 0) == "참깨빵" && (String)listResult.getValueAt(i+1, 0) == "특별한소스" && (String)listResult.getValueAt(i+2, 0) == "치즈") {
						text.setBounds(670,410,120,50);
						text.setText(String.valueOf("치즈버거 완성"));
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("Update bank set Money = Money + 2200");    // 치즈버거 완성시 금액추가
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("Update food set SellCount = SellCount + 1 where SellFood = '치즈버거'");  // 치즈버거 완성시 판매량 1증가
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("delete from jumun where JumunName = '치즈버거'");
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						DefaultTableModel m = (DefaultTableModel)listResult.getModel();            // 재료기록칸 목록 초기화
						m.setRowCount(0);
						DefaultTableModel s = (DefaultTableModel)list.getModel();                  // 주문내역 목록 초기화
						s.setRowCount(0);
						try {                                                                                      // 다시 입력받음
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
					else if((String)listResult.getValueAt(i, 0) == "참깨빵" && (String)listResult.getValueAt(i+1, 0) == "불고기패티" && (String)listResult.getValueAt(i+2, 0) == "불고기패티" && (String)listResult.getValueAt(i+3, 0) == "양상추" && (String)listResult.getValueAt(i+4, 0) == "양파" && (String)listResult.getValueAt(i+5, 0) == "피클" && (String)listResult.getValueAt(i+6, 0) == "특별한소스") {
						text.setBounds(680,410,120,50);
						text.setText(String.valueOf("빅맥 완성"));
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("Update bank set Money = Money + 4900");    // 빅맥 완성시 금액추가
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("Update food set SellCount = SellCount + 1 where SellFood = '빅맥'");  // 빅맥 완성시 판매량 1증가
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("delete from jumun where JumunName = '빅맥'");
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						DefaultTableModel m = (DefaultTableModel)listResult.getModel();            // 재료기록칸 목록 초기화
						m.setRowCount(0);
						DefaultTableModel s = (DefaultTableModel)list.getModel();                  // 주문내역 목록 초기화
						s.setRowCount(0);
						try {                                                                                      // 다시 입력받음
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
					else if((String)listResult.getValueAt(i, 0) == "참깨빵" && (String)listResult.getValueAt(i+1, 0) == "치킨패티" && (String)listResult.getValueAt(i+2, 0) == "치즈" && (String)listResult.getValueAt(i+3, 0) == "특별한소스") {
						text.setBounds(655,410,120,50);
						text.setText(String.valueOf("크리스피버거 완성"));
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("Update bank set Money = Money + 3300");    // 크리스피버거 완성시 금액추가
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("Update food set SellCount = SellCount + 1 where SellFood = '크리스피버거'");  // 크리스피버거 완성시 판매량 1증가
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("delete from jumun where JumunName = '크리스피버거'");
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						DefaultTableModel m = (DefaultTableModel)listResult.getModel();            // 재료기록칸 목록 초기화
						m.setRowCount(0);
						DefaultTableModel s = (DefaultTableModel)list.getModel();                  // 주문내역 목록 초기화
						s.setRowCount(0);
						try {                                                                                      // 다시 입력받음
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
					else if((String)listResult.getValueAt(i, 0) == "참깨빵" && (String)listResult.getValueAt(i+1, 0) == "양상추" && (String)listResult.getValueAt(i+2, 0) == "피클" && (String)listResult.getValueAt(i+3, 0) == "특별한소스" && (String)listResult.getValueAt(i+4, 0) == "베이컨") {
						text.setBounds(660,410,120,50);
						text.setText(String.valueOf("베이컨버거 완성"));
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("Update bank set Money = Money + 5300");    // 베이컨버거 완성시 금액추가
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("Update food set SellCount = SellCount + 1 where SellFood = '베이컨버거'");  // 베이컨버거 완성시 판매량 1증가
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("delete from jumun where JumunName = '베이컨버거'");
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						DefaultTableModel m = (DefaultTableModel)listResult.getModel();            // 재료기록칸 목록 초기화
						m.setRowCount(0);
						DefaultTableModel s = (DefaultTableModel)list.getModel();                  // 주문내역 목록 초기화
						s.setRowCount(0);
						try {                                                                                      // 다시 입력받음
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
					else if((String)listResult.getValueAt(i, 0) == "참깨빵" && (String)listResult.getValueAt(i+1, 0) == "양상추" && (String)listResult.getValueAt(i+2, 0) == "특별한소스" && (String)listResult.getValueAt(i+3, 0) == "오징어패티") {
						text.setBounds(660,410,120,50);
						text.setText(String.valueOf("오징어버거 완성"));
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("Update bank set Money = Money + 4300");    // 오징어버거 완성시 금액추가
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("Update food set SellCount = SellCount + 1 where SellFood = '오징어버거'");  // 오징어버거 완성시 판매량 1증가
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("delete from jumun where JumunName = '오징어버거'");
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						DefaultTableModel m = (DefaultTableModel)listResult.getModel();            // 재료기록칸 목록 초기화
						m.setRowCount(0);
						DefaultTableModel s = (DefaultTableModel)list.getModel();                  // 주문내역 목록 초기화
						s.setRowCount(0);
						try {                                                                                      // 다시 입력받음
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
					else if((String)listResult.getValueAt(i, 0) == "참깨빵" && (String)listResult.getValueAt(i+1, 0) == "양상추" && (String)listResult.getValueAt(i+2, 0) == "피클" && (String)listResult.getValueAt(i+3, 0) == "양파" && (String)listResult.getValueAt(i+4, 0) == "불고기패티" && (String)listResult.getValueAt(i+5, 0) == "치킨패티") {
						text.setBounds(670,410,120,50);
						text.setText(String.valueOf("더블버거 완성"));
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("Update bank set Money = Money + 4400");    // 더블버거 완성시 금액추가
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("Update food set SellCount = SellCount + 1 where SellFood = '더블버거'");  // 더블버거 완성시 판매량 1증가
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("delete from jumun where JumunName = '더블버거'");
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						DefaultTableModel m = (DefaultTableModel)listResult.getModel();            // 재료기록칸 목록 초기화
						m.setRowCount(0);
						DefaultTableModel s = (DefaultTableModel)list.getModel();                  // 주문내역 목록 초기화
						s.setRowCount(0);
						try {                                                                                      // 다시 입력받음
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
					else if((String)listResult.getValueAt(i, 0) == "참깨빵" && (String)listResult.getValueAt(i+1, 0) == "피클" && (String)listResult.getValueAt(i+2, 0) == "달걀" && (String)listResult.getValueAt(i+3, 0) == "치즈") {
						text.setBounds(655,410,120,50);
						text.setText(String.valueOf("에그치즈버거 완성"));
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("Update bank set Money = Money + 3500");    // 에그치즈버거 완성시 금액추가
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("Update food set SellCount = SellCount + 1 where SellFood = '에그치즈버거'");  // 에그치즈버거 완성시 판매량 1증가
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("delete from jumun where JumunName = '에그치즈버거'");
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						DefaultTableModel m = (DefaultTableModel)listResult.getModel();            // 재료기록칸 목록 초기화
						m.setRowCount(0);
						DefaultTableModel s = (DefaultTableModel)list.getModel();                  // 주문내역 목록 초기화
						s.setRowCount(0);
						try {                                                                                      // 다시 입력받음
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
					else if((String)listResult.getValueAt(i, 0) == "감자" && (String)listResult.getValueAt(i+1, 0) == "식용유") {
						text.setBounds(670,410,120,50);
						text.setText(String.valueOf("감자튀김 완성"));
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("Update bank set Money = Money + 1000");    // 감자튀김 완성시 금액추가
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("Update food set SellCount = SellCount + 1 where SellFood = '감자튀김'");  // 감자튀김 완성시 판매량 1증가
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("delete from jumun where JumunName = '감자튀김'");
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						DefaultTableModel m = (DefaultTableModel)listResult.getModel();            // 재료기록칸 목록 초기화
						m.setRowCount(0);
						DefaultTableModel s = (DefaultTableModel)list.getModel();                  // 주문내역 목록 초기화
						s.setRowCount(0);
						try {                                                                                      // 다시 입력받음
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
					else if((String)listResult.getValueAt(i, 0) == "콜라" && (String)listResult.getValueAt(i+1, 0) == "얼음") {
						text.setBounds(680,410,120,50);
						text.setText(String.valueOf("콜라 완성"));
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("Update bank set Money = Money + 1300");    // 콜라 완성시 금액추가
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("Update food set SellCount = SellCount + 1 where SellFood = '콜라'");  // 콜라 완성시 판매량 1증가
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("delete from jumun where JumunName = '콜라'");
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						DefaultTableModel m = (DefaultTableModel)listResult.getModel();            // 재료기록칸 목록 초기화
						m.setRowCount(0);
						DefaultTableModel s = (DefaultTableModel)list.getModel();                  // 주문내역 목록 초기화
						s.setRowCount(0);
						try {                                                                                      // 다시 입력받음
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
					else if((String)listResult.getValueAt(i, 0) == "사이다" && (String)listResult.getValueAt(i+1, 0) == "얼음") {
						text.setBounds(680,410,120,50);
						text.setText(String.valueOf("사이다 완성"));
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("Update bank set Money = Money + 1300");    // 사이다 완성시 금액추가
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("Update food set SellCount = SellCount + 1 where SellFood = '사이다'");  // 사이다 완성시 판매량 1증가
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("delete from jumun where JumunName = '사이다'");
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						DefaultTableModel m = (DefaultTableModel)listResult.getModel();            // 재료기록칸 목록 초기화
						m.setRowCount(0);
						DefaultTableModel s = (DefaultTableModel)list.getModel();                  // 주문내역 목록 초기화
						s.setRowCount(0);
						try {                                                                                      // 다시 입력받음
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
					else if((String)listResult.getValueAt(i, 0) == "우유" && (String)listResult.getValueAt(i+1, 0) == "얼음") {
						text.setBounds(660,410,120,50);
						text.setText(String.valueOf("아이스크림 완성"));
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("Update bank set Money = Money + 500");    // 아이스크림 완성시 금액추가
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("Update food set SellCount = SellCount + 1 where SellFood = '아이스크림'");  // 아이스크림 완성시 판매량 1증가
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						try {
							Connection con = getIngre();
							PreparedStatement statement = con.prepareStatement("delete from jumun where JumunName = '아이스크림'");
							ResultSet results = statement.executeQuery();
						
							}catch(Exception e1) {
								System.out.println(e1.getMessage());
						   }
						DefaultTableModel m = (DefaultTableModel)listResult.getModel();            // 재료기록칸 목록 초기화
						m.setRowCount(0);
						DefaultTableModel s = (DefaultTableModel)list.getModel();                  // 주문내역 목록 초기화
						s.setRowCount(0);
						try {                                                                                      // 다시 입력받음
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
		
		JButton LobiGo = new JButton("로비창가기");    // 로비창가기버튼
		LobiGo.setBounds(650,480,120,50);
		jp1.add(LobiGo);
		
		LobiGo.addActionListener(new MainClass(){           // 로비창으로이동
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

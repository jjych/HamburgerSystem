package com.java.ex;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import com.java.ex.Event.*;

public class FirstWindow extends JFrame{
	public FirstWindow() {
		
		Container ct = getContentPane();
		ct.setLayout(new BorderLayout(10,10));
		
		JButton btn1 = new JButton("�ֹ��ϱ�");
		JButton btn2 = new JButton("�ܾ���ȸ �� ����ǰ��");
		JButton btn3 = new JButton("����� â��");
		
		setTitle("�κ�");
		setSize(500,300);
		
		// '�κ�' ���� ��
		JLabel jl1 = new JLabel("�κ�");
		jl1.setHorizontalAlignment(JLabel.CENTER);
		
		// �ֹ��ϱ� ��ư
		JPanel jp1 = new JPanel();
		jp1.setLayout(new BorderLayout());
		jp1.add(btn1); 
		
		// �ܾ���ȸ �� ����ǰ�� ��ư
		JPanel jp2 = new JPanel();
		jp2.setLayout(new BorderLayout(3,1));
		jp2.add(btn2);
		
		// �����â�� ��ư
		JPanel jp3 = new JPanel();
		jp3.setLayout(new BorderLayout(3,1));
		jp3.add(btn3);
		
		ct.add(jl1 ,BorderLayout.NORTH);  // ���߰�
		ct.add(jp1, BorderLayout.CENTER);
		ct.add(jp2, BorderLayout.WEST);
		ct.add(jp3, BorderLayout.EAST);
		
		btn1.addActionListener(new Order() {                  // �ֹ����� �̵�
		public void actionPerformed(ActionEvent e) {
			SecondWindow W2 = new SecondWindow();
	         W2.setVisible(true);
	         dispose();
			}
		});
			
		btn2.addActionListener(new Bank(){                      // �ܾ���ȸ�� �̵�
			public void actionPerformed(ActionEvent e) {
				ForthWindow W4 = new ForthWindow();
//		         W4.setVisible(true);
		         dispose();
				}
			});
		
		btn3.addActionListener(new Store() {
			public void actionPerformed(ActionEvent e) {
				FifthWindow W5 = new FifthWindow();
				W5.setVisible(true);
				dispose();
			}
		});
		
		setBounds(1,1,500,300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		
	}

}

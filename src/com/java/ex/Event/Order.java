package com.java.ex.Event;

import java.awt.event.ActionEvent;
import com.java.ex.*;
import com.java.ex.main.MainClass;

import java.awt.event.ActionListener;
import javax.swing.*;

public class Order implements ActionListener{
	public Order() {
	
	}
	public void actionPerformed(ActionEvent e) {
		SecondWindow W2 = new SecondWindow();
	}

}

package com.java.ex.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.java.ex.*;

public class MainClass implements ActionListener{

	public static void main(String[] args) {
		FirstWindow W1 = new FirstWindow();
	}
	public MainClass() {
	}
	public void actionPerformed(ActionEvent e) {
		FirstWindow W1 = new FirstWindow();
	}

}

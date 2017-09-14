package com.mengoud.babynametor.view;

import java.awt.CardLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.mengoud.babynametor.core.AppUtil;

public class AppWindow extends JFrame  {

	private CardLayout cardLayout;
	public AppWindow() {
		initView();
	}
	
	private void initView() {
		this.setTitle("宝宝取名器");
		this.setIconImage(AppUtil.loadImage("img/logo.png", 32, 32));
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(320, 480);
		this.setLocationRelativeTo(null);
		cardLayout=new CardLayout();
		this.setLayout(cardLayout);
		this.add(new WelcomeView(this));
		this.add(new MainView(this));
	}
	public void gotoMainView() {
		cardLayout.next(getContentPane());
	}
	
}

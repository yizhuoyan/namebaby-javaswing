package com.mengoud.babynametor.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.mengoud.babynametor.core.AppUtil;

public class WelcomeView extends Box implements ActionListener {
	final AppWindow win;

	public WelcomeView(AppWindow win) {
		super(BoxLayout.Y_AXIS);
		this.win = win;
		init();
	}

	public void init() {
		{
			JPanel row = new JPanel(new BorderLayout());
			JLabel titleView = new JLabel("宝宝取名器 v1.01");
			row.add(titleView);
			Icon icon = AppUtil.loadIcon("img/bg.png", 100, 100);
			titleView.setIcon(icon);
			this.add(row);
		}
		{
			JTextArea introView = new JTextArea(getIntroText());
			introView.setEditable(false);
			introView.setWrapStyleWord(true);
			introView.setLineWrap(true);
			introView.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
			this.add(introView);
		}
		{
			JPanel row = new JPanel();
			JButton iKnowBtn = new JButton("我知道了");
			iKnowBtn.addActionListener(this);
			row.add(iKnowBtn);
			this.add(Box.createVerticalStrut(30));
			this.add(row);
			this.add(Box.createVerticalStrut(30));
		}

		
	}

	private static String getIntroText() {
		StringBuilder text = new StringBuilder();
		text.append("       名字伴人一生，父母都是绞尽脑汁想给新生宝宝起个吉祥美名。怎样才能给宝宝起个吉祥又好听的名字呢?");
		text.append("\n");
		text.append("让宝宝取名器来帮助您吧！");
		return text.toString();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		win.gotoMainView();
	}
}

package com.mengoud.babynametor.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MessageDialog extends JDialog implements ActionListener {
	JTextArea messageView;
	JFrame owner;
	public MessageDialog(JFrame owner) {
		super(owner);
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		initView();
		this.setLocationRelativeTo(owner);
	}
	private void initView() {
		this.setMinimumSize(new Dimension(320, 180));
		this.setLayout(new BorderLayout());
		this.messageView=new JTextArea();
		messageView.setEditable(false);
		messageView.setLineWrap(true);
		messageView.setBorder(BorderFactory.createEmptyBorder(15, 15, 15,15));
		this.add(this.messageView);
		JPanel actionPanel=new JPanel();
		
		JButton exitBtn=new JButton("确定");
		exitBtn.addActionListener(this);
		actionPanel.add(exitBtn);
		this.add(actionPanel,BorderLayout.SOUTH);
	}
	public void showMessage(String title,String message) {
		this.setTitle(title);
		this.messageView.setText(message);
		this.pack();
		this.setVisible(true);
	}
	public void showMessage(String message) {
		this.showMessage("消息",message);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}

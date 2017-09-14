package com.mengoud.babynametor.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.mengoud.babynametor.core.ThisAppException;
import com.mengoud.babynametor.model.BabyNameModel;

public class MainView extends Box implements ActionListener {
	final AppWindow win;
	/**模型*/
	private BabyNameModel model;
	/**姓氏的文本框*/
	private JTextField firstNameField;
	/**推荐名字的显示视图*/
	private JTextArea commendNamesView;
	/**消息对话框*/
	private MessageDialog messageDialog;
	
	public MainView(AppWindow win) {
		super(BoxLayout.Y_AXIS);
		model=new BabyNameModel();
		this.win=win;
		initView();
		
	}

	private void initView() {
		JTextArea howLabel = new JTextArea("\n      请填写您的姓氏和宝宝的性别，我们将列出一些列的名字供您参考，祝宝宝好名远扬！");
		howLabel.setEditable(false);
		howLabel.setLineWrap(true);
		howLabel.setPreferredSize(new Dimension(0, 100));
		howLabel.setBorder(BorderFactory.createEmptyBorder(15, 15,15,15));
		this.add(howLabel);
		this.add(initFormView());
		this.add(initResultView());
	}

	private JComponent initFormView() {
		Box formPanle = new Box(BoxLayout.Y_AXIS);
		{
			JPanel formRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
			JLabel firstNameLabel = new JLabel("您的姓氏：");
			JTextField firstNameField =new JTextField();
			firstNameField.setColumns(10);
			formRow.add(firstNameLabel);
			formRow.add(this.firstNameField=firstNameField);
			formPanle.add(formRow);
		}

		{
			JPanel formRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
			JLabel sexLabel = new JLabel("宝宝性别：");
			JRadioButton maleBtn = new JRadioButton("男",true);
			maleBtn.setActionCommand("maleBaby");
			model.setBabyMale(true);
			JRadioButton femaleBtn = new JRadioButton("女");
			femaleBtn.setActionCommand("femaleBaby");
			ButtonGroup sexBtnGrounp =new ButtonGroup();
			sexBtnGrounp.add(maleBtn);
			sexBtnGrounp.add(femaleBtn);
			
			formRow.add(sexLabel);
			for(Enumeration<AbstractButton> elements = sexBtnGrounp.getElements();elements.hasMoreElements();) {
				AbstractButton btn = elements.nextElement();
				btn.addActionListener(this);
				formRow.add(btn);
			}
			formPanle.add(formRow);
		}
		{
			JPanel formRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
			JLabel nameStyleLabel = new JLabel("名字形式：");
			JRadioButton singleWordBtn = new JRadioButton("单字");
			singleWordBtn.setActionCommand("singleNameStyle");
			JRadioButton doubleWordBtn = new JRadioButton("双字",true);
			model.setNameStyle(2);
			doubleWordBtn.setActionCommand("doubleNameStyle");
			JRadioButton repeatWordBtn = new JRadioButton("叠字");
			repeatWordBtn.setActionCommand("repeatNameStyle");
			ButtonGroup nameStyleBtnGroup =new ButtonGroup();
			nameStyleBtnGroup.add(singleWordBtn);
			nameStyleBtnGroup.add(doubleWordBtn);
			nameStyleBtnGroup.add(repeatWordBtn);

			formRow.add(nameStyleLabel);
			for(Enumeration<AbstractButton> elements = nameStyleBtnGroup.getElements();elements.hasMoreElements();) {
				AbstractButton btn = elements.nextElement();
				btn.addActionListener(this);
				formRow.add(btn);
			}

			formPanle.add(formRow);
		}
		{
			JPanel formRow = new JPanel();
			JButton actionBtn = new JButton("名字推荐");
			actionBtn.setActionCommand("commendName");
			actionBtn.addActionListener(this);
			formRow.add(actionBtn);
			formPanle.add(formRow);
		}
		return formPanle;
	}
	
	private JComponent initResultView() {
		Box div=new Box(BoxLayout.Y_AXIS);
		
		{
			JTextArea namesView=new JTextArea();
			namesView.setEditable(false);
			namesView.setLineWrap(true);
			namesView.setRows(10);
			namesView.setBorder(BorderFactory.createEmptyBorder(15, 15,15,15));
			div.add(commendNamesView=namesView);
		}
		{
			JPanel namesActionPanel=new JPanel();
			JButton previewBtn=new JButton("上一批");
			previewBtn.setActionCommand("preCommendNames");
			previewBtn.addActionListener(this);
			JButton nextBtn=new JButton("下一批");
			nextBtn.setActionCommand("nextCommendNames");
			nextBtn.addActionListener(this);
			namesActionPanel.add(previewBtn);
			namesActionPanel.add(nextBtn);
			div.add(namesActionPanel);
		}
		return div;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "commendName":
			handlerGenerateNameBtnClick((JButton)e.getSource());
			break;
		case "maleBaby":
			this.model.setBabyMale(true);
			break;
		case "femalBaby":
			this.model.setBabyMale(false);
			break;
		case "singleNameStyle":
			this.model.setNameStyle(1);
			break;
		case "doubleNameStyle":
			this.model.setNameStyle(2);
			break;
		case "repeatNameStyle":
			this.model.setNameStyle(3);
			break;
		case "nextCommendNames":
			handlerNextNamesBtnClick((JButton)e.getSource());
			break;
		case "preCommendNames":
			handlerPreNamesBtnClick((JButton)e.getSource());
			break;
		}
		
	}
	private void handlerGenerateNameBtnClick(JButton btn) {
		try {
			String firstName=this.firstNameField.getText().trim();
			if(firstName.length()==0) {
				this.firstNameField.requestFocus();
				throw new ThisAppException("请输入您的姓氏！");
			}
			model.setFirstName(firstName);
			String[] commendNames=model.commendNames();
			updateResultView(commendNames);
		}catch(Exception e) {
			updateErrorView(e);
		}
	}
	private void handlerNextNamesBtnClick(JButton btn) {
		String[] commendNames=model.nextCommendNames();
		updateResultView(commendNames);
	}
	private void handlerPreNamesBtnClick(JButton btn) {
		String[] commendNames=model.preCommendNames();
		updateResultView(commendNames);
	}
	private void updateResultView(String[] names) {
		StringBuilder texts=new StringBuilder();
		for (int i = 0; i < names.length; i++) {
			String name = names[i];
			texts.append(i+1).append("、");
			texts.append(name);
			texts.append("\n");
		}
		commendNamesView.setText(texts.toString());
		
	}
	private void updateErrorView(Exception e) {
		e.printStackTrace();
		MessageDialog dialog=this.messageDialog;
		if(dialog==null) {
			this.messageDialog=dialog=new MessageDialog(this.win);
		}
		dialog.showMessage("错误", e.getMessage());
	}
}

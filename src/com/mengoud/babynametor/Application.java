package com.mengoud.babynametor;

import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import com.mengoud.babynametor.view.AppWindow;

public class Application {

	public static void main(String[] args)throws Exception {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				AppWindow win=new AppWindow();
				
				win.setVisible(true);
			}
		});
	}
}

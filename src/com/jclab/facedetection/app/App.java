package com.jclab.facedetection.app;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.jclab.facedetection.gui.MainFrame;

public class App {

	public static void main(String[] args) {
	
		try {
			
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {				
				new MainFrame();
			}
		});
		

	}

}

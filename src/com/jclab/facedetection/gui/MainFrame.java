package com.jclab.facedetection.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.jclab.facedetection.constants.Constants;
import com.jclab.facedetection.logic.FaceDetection;

public class MainFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private ImagePanel imagePanel;
	private JFileChooser fileChooser;
	private FaceDetection faceDetection;
	private File file;
	
	public MainFrame() {
		super(Constants.APPLICATION_NAME);
		
		setJMenuBar(createMenuBar());
		
		this.imagePanel = new ImagePanel();
		this.fileChooser = new JFileChooser();
		this.faceDetection = new FaceDetection();
		
		add(imagePanel, BorderLayout.CENTER);
		
		setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setLocationRelativeTo(this);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}

	private JMenuBar createMenuBar() {
		
		JMenuBar menuBar = new JMenuBar();
		
		JMenu fileMenu = new JMenu("File");
		JMenuItem loadMenuItem = new JMenuItem("Load image");
		JMenuItem detectMenuItem = new JMenuItem("Detect faces");
		JMenuItem exitMenuItem = new JMenuItem("Exit");
		fileMenu.add(loadMenuItem);
		fileMenu.add(detectMenuItem);
		fileMenu.add(exitMenuItem);
		
		loadMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					MainFrame.this.file = fileChooser.getSelectedFile();
					System.out.println(MainFrame.this.file);
					MainFrame.this.imagePanel.loadImage(MainFrame.this.file);					
				}
			}
		});
		
		detectMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {				
				MainFrame.this.faceDetection.detectFaces(MainFrame.this.file, 
						MainFrame.this.imagePanel);
			}
		});
		
		JMenu aboutMenu = new JMenu("About");		
		JMenu helpMenu = new JMenu("Help");
		
		menuBar.add(fileMenu);
		menuBar.add(aboutMenu);
		menuBar.add(helpMenu);
		
		exitMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int action = JOptionPane.showConfirmDialog(MainFrame.this, Constants.EXIT_WARNING);
				
				if(action == JOptionPane.OK_OPTION) {
					System.gc();
					System.exit(0);
				}
				
			}
		});
		
		aboutMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {				
				JOptionPane.showMessageDialog(MainFrame.this, "About us", 
						"Developed by: Jessie S. Labadan",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		return menuBar;
		
	}
	
	
	
}

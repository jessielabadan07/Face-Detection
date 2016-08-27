package com.jclab.facedetection.gui;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jclab.facedetection.constants.Constants;

import java.awt.BorderLayout;

public class ImagePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel imageLabel;
	
	public ImagePanel() {
		
		this.imageLabel = new JLabel();
		
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(Constants.IMAGE_LABEL_BORDER, Constants.IMAGE_LABEL_BORDER,
				Constants.IMAGE_LABEL_BORDER, Constants.IMAGE_LABEL_BORDER));
		
	}
	
}

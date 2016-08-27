package com.jclab.facedetection.logic;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
//import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.highgui.Highgui;
import org.opencv.objdetect.CascadeClassifier;

import com.jclab.facedetection.constants.Constants;
import com.jclab.facedetection.gui.ImagePanel;

public class FaceDetection {

	private CascadeClassifier cascadeClassifier;
	
	public FaceDetection() {
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		this.cascadeClassifier = new CascadeClassifier(Constants.CASCADE_CLASSIFIER);		
	}
	
	public void detectFaces(File file, ImagePanel imagePanel) {		
		Mat image = Highgui.imread(file.getAbsolutePath(), Highgui.CV_LOAD_IMAGE_COLOR);
		
		MatOfRect faceDetections = new MatOfRect();
		cascadeClassifier.detectMultiScale(image, faceDetections, 1.2, 3, 10, new Size(50, 50), new Size(500, 500));
		
		for(Rect rect : faceDetections.toArray()) {			
			Core.rectangle(image, new Point(rect.x, rect.y), 
					new Point(rect.x+rect.width, rect.y+rect.height), 
					new Scalar(100, 100, 250), 
					10);
		}
		
		BufferedImage bufferedImage = convertMatToImage(image); 
		imagePanel.updateImage(bufferedImage);
	}

	private BufferedImage convertMatToImage(Mat mat) {		
		
		int type = BufferedImage.TYPE_BYTE_GRAY;
		
		if(mat.channels() > 1) {
			type = BufferedImage.TYPE_3BYTE_BGR;
		}
		
		int bufferSize = mat.channels() * mat.cols() * mat.rows();
		byte[] bytes = new byte[bufferSize];
		mat.get(0, 0, bytes);
		BufferedImage image = new BufferedImage(mat.cols(), mat.rows(), type);
		final byte[] targetPixels = ( (DataBufferByte) image.getRaster().getDataBuffer()).getData();
		System.arraycopy(bytes, 0, targetPixels, 0, bytes.length);
		return image;
	}
	
}

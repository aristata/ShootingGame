package com.aristatait.Frame;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageLoader {

	public static final ImageLoader INSTANCE = new ImageLoader();
	
	public static ImageLoader getInstance() {
		return INSTANCE;
	}
	
	//private File p_file, m_file, e_file, bg_file;
	private static Image[] p_Img; //플레이어 이미지
	private static Image f_Img; //파이어볼 이미지
	private static Image e_Img; //적 이미지
	private static Image bg_Img; //배경 이미지
	private static Image[] c_Img; //구름 이미지
	private static Image[] ep_Img; //폭발 이미지
	
	//ImageIcon 을 사용함에 따라 필요 없어진 변수
	//적 이미지의 크기값을 받을 변수
	//public static int e_w, e_h;
	
	//미사일 이미지의 크기값을 받을 변수
	//public static int m_w, m_h;
		
	
	public ImageLoader() {
		/* 이미지 로드 방식을 ImageIcon 으로 변경함으로써 주석처리함
		 * try {
			p_file = new File("C:/IT/Eclipse-JAVA/workspace/ShootingGame/Images/airplane.gif");
			p_Img = ImageIO.read(p_file);
			
			m_file = new File("C:/IT/Eclipse-JAVA/workspace/shootingGame/Images/fireball.png");
			m_Img = ImageIO.read(m_file);
			m_w = ImageWidthValue(m_Img);
			m_h = ImageWidthValue(m_Img);
			
			e_file = new File("C:/IT/Eclipse-JAVA/workspace/ShootingGame/Images/monster.jpg");
			e_Img = ImageIO.read(e_file);
			e_w = ImageWidthValue(e_Img);
			e_h = ImageHeightValue(e_Img);
		
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
		p_Img = new Image[5];
		for (int i = 0; i < p_Img.length; i++) {
			p_Img[i] = new ImageIcon("./Images/f15k_" + i +".png").getImage();
		}
		
		f_Img = new ImageIcon("./Images/fireball.png").getImage();
		e_Img = new ImageIcon("./Images/monster.png").getImage();
		
		bg_Img = new ImageIcon("./Images/background.png").getImage();
		
		c_Img = new Image[3];
		for (int i = 0; i < c_Img.length; i++) {
			c_Img[i] = new ImageIcon("./Images/cloud_" + i + ".png").getImage();
		}
		
		ep_Img = new Image[3];
		for (int i = 0; i < ep_Img.length; i++) {
			ep_Img[i] = new ImageIcon("./Images/explo_" + i + ".png").getImage();
		}
		
	}

	public static Image getP_Img(int i) {
		return p_Img[i];
	}

	public static Image getF_Img() {
		return f_Img;
	}

	public static Image getE_Img() {
		return e_Img;
	}

	public static Image getBg_Img() {
		return bg_Img;
	}

	public static Image getC_Img(int i) {
		return c_Img[i];
	}

	public static Image getEp_Img(int i) {
		return ep_Img[i];
	}

	
	
	
	
	/*public static int getE_w() {
		return e_w;
	}

	public static int getE_h() {
		return e_h;
	}

	public static int getM_w() {
		return m_w;
	}

	public static int getM_h() {
		return m_h;
	}

	//이미지 가로 계산
	public int ImageWidthValue (Image img) {
		int x = 0;
		x = img.getWidth(null);
		return x;
	}
	
	//이미지 세로 계산
	public int ImageHeightValue (Image img) {
		int y = 0;
		y = img.getHeight(null);
		return y;
	}*/
	
	
	
}

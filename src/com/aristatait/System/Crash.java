package com.aristatait.System;

import java.awt.Image;

public class Crash {

	public Crash() {
		// TODO Auto-generated constructor stub
	}
	
	public static boolean Crash(int x1, int y1, int x2, int y2, Image img1, Image img2){
		boolean check = false;
		if (Math.abs((x1 + img1.getWidth(null) / 2) - (x2 + img2.getWidth(null) / 2)) < (img2.getWidth(null) / 2 + img1.getWidth(null) / 2) && Math.abs((y1 + img1.getHeight(null) / 2) - (y2 + img2.getHeight(null) / 2)) < (img2.getHeight(null) / 2 + img1.getHeight(null) / 2)) {
			check = true;
		}else{
			check = false;
		}
		return check;
	}
}

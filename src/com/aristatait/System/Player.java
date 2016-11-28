package com.aristatait.System;

import com.aristatait.Frame.ImageLoader;
import com.aristatait.Frame.MainFrame;

public class Player {

	public static final Player INSTANCE = new Player();
	
	public static Player getInstance() {
		return INSTANCE;
	}
	
	private static int p_x;
	private static int p_y;
	
		
	public Player() {
		p_x = 100;
		p_y = 100;
	}
	
	// 케릭터의 움직임을 실현하기 위한 메소드
	public static void playerProcess(){
		if(KeyController.isKEY_UP() == true) {
			if(p_y > 20){
				p_y -= MainFrame.player_Speed;	
			}
			MainFrame.player_Status = 0;		
		}
		if(KeyController.isKEY_DOWN() == true) {
			if(p_y + ImageLoader.getP_Img(0).getHeight(null) < MainFrame.FRAME_HEIGHT){
				p_y += MainFrame.player_Speed;
			}
			MainFrame.player_Status = 0;
						
		}
		if(KeyController.isKEY_LEFT() == true) {
			if(p_x > 0 ){
				p_x -= MainFrame.player_Speed;
			}
			MainFrame.player_Status = 0;			
		}
		if(KeyController.isKEY_RIGHT() == true) {
			if(p_x + ImageLoader.getP_Img(0).getWidth(null) < MainFrame.FRAME_WIDTH){
				p_x += MainFrame.player_Speed;	
			}
			MainFrame.player_Status = 0;		
		}		
	}

	public static int getP_x() {
		return p_x;
	}

	public static int getP_y() {
		return p_y;
	}	
	
	
}

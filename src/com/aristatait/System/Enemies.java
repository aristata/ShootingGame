package com.aristatait.System;

import java.util.ArrayList;

import com.aristatait.Frame.ImageLoader;
import com.aristatait.Frame.MainFrame;

public class Enemies {
	
	public int x;
	public int y;
	public int speed;
	
	public Enemies(int x, int y, int speed) {
		this.x = x;
		this.y = y;
		this.speed = speed;
	}
	
	public void move() {
		x -= speed;
	}
	
	//적 행동 처리 메소드
	public static void enemeisProcess() {
		for (int i = 0; i < MainFrame.eList.size(); i++) {
			MainFrame.enm = (Enemies)(MainFrame.eList.get(i));
			MainFrame.enm.move();
			if(MainFrame.enm.x < -200){
				MainFrame.eList.remove(i);
			}
			//플레이어와 적이 충돌
			if(Crash.Crash(Player.getP_x(), Player.getP_y(), MainFrame.enm.x, MainFrame.enm.y, ImageLoader.getP_Img(0), ImageLoader.getE_Img())){
				MainFrame.player_HealthPoint --;
				MainFrame.eList.remove(i);
				MainFrame.game_Score += 10;
				
				//적위치 중심좌표와 폭발 설정 값 인자로 폭발 객체 생성
				// 0 - 폭발, 1 - 충돌
				Explosion ex = new Explosion(MainFrame.enm.x + ImageLoader.getE_Img().getWidth(null) / 2, MainFrame.enm.y + ImageLoader.getE_Img().getHeight(null) / 2, 0);
				MainFrame.explosion_List.add(ex);
				
				ex = new Explosion(MainFrame.enm.x, MainFrame.enm.y, 1);
				MainFrame.explosion_List.add(ex);
			}
		}
		
		if(MainFrame.cnt % 200 == 0 ){
			MainFrame.enm = new Enemies(MainFrame.FRAME_WIDTH + 100, 100, MainFrame.enemy_Speed);
			MainFrame.eList.add(MainFrame.enm);
			
			MainFrame.enm = new Enemies(MainFrame.FRAME_WIDTH + 100, 200, MainFrame.enemy_Speed);
			MainFrame.eList.add(MainFrame.enm);
			
			MainFrame.enm = new Enemies(MainFrame.FRAME_WIDTH + 100, 300, MainFrame.enemy_Speed);
			MainFrame.eList.add(MainFrame.enm);
			
			MainFrame.enm = new Enemies(MainFrame.FRAME_WIDTH + 100, 400, MainFrame.enemy_Speed);
			MainFrame.eList.add(MainFrame.enm);
			
			MainFrame.enm = new Enemies(MainFrame.FRAME_WIDTH + 100, 500, MainFrame.enemy_Speed);
			MainFrame.eList.add(MainFrame.enm);
		}
	}
}

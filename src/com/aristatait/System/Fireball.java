package com.aristatait.System;

import com.aristatait.Frame.ImageLoader;
import com.aristatait.Frame.MainFrame;

public class Fireball {
			
	public int m_x, m_y, angle, fireball_Speed;
			
	public Fireball(int m_x, int m_y, int angle, int fireball_Speed) {
		this.m_x = m_x;
		this.m_y = m_y;
		this.angle = angle;
		this.fireball_Speed = fireball_Speed;
	}
	
	public void move() {
		m_x += Math.cos(Math.toRadians(angle)) * fireball_Speed;
		m_y += Math.sin(Math.toRadians(angle)) * fireball_Speed;
	}
	
	public static void fireballProcess() {
		if(KeyController.isKEY_SPACE() == true) {
			//파이어볼을 사용하면 플레이어 캐릭터 상태를 1로 변경한다.
			MainFrame.player_Status = 1;
			
			//플레이어의 연사속도를 조절한다.
			if((MainFrame.cnt % MainFrame.fire_Speed) == 0){
				MainFrame.fb = new Fireball(Player.getP_x() + 160, Player.getP_y() + 25, 0, MainFrame.fireball_Speed);
				MainFrame.fire_list.add(MainFrame.fb);
				
				MainFrame.fb = new Fireball(Player.getP_x() + 160, Player.getP_y() + 25, 330, MainFrame.fireball_Speed);
				MainFrame.fire_list.add(MainFrame.fb);
				
				MainFrame.fb = new Fireball(Player.getP_x() + 160, Player.getP_y() + 25, 30, MainFrame.fireball_Speed);
				MainFrame.fire_list.add(MainFrame.fb);
				
				Sounds.playSounds("mfire.wav", false);
			}
			
			
		}
		for (int i = 0; i < MainFrame.fire_list.size(); ++i) {
			MainFrame.fb = (Fireball) MainFrame.fire_list.get(i);
			MainFrame.fb.move();
			if(MainFrame.fb.m_x > MainFrame.FRAME_WIDTH - 20){
				MainFrame.fire_list.remove(i);
			}
			for (int j = 0; j < MainFrame.eList.size(); ++j) {
				MainFrame.enm = (Enemies) MainFrame.eList.get(j);
				if(Crash.Crash(MainFrame.fb.m_x, MainFrame.fb.m_y, MainFrame.enm.x, MainFrame.enm.y, ImageLoader.getF_Img(), ImageLoader.getE_Img())){
					MainFrame.fire_list.remove(i);
					MainFrame.eList.remove(j);
					
					MainFrame.game_Score += 10;
					
					Explosion ex = new Explosion(MainFrame.enm.x + ImageLoader.getE_Img().getWidth(null) / 2, MainFrame.enm.y + ImageLoader.getE_Img().getHeight(null) / 2, 0);
					MainFrame.explosion_List.add(ex);
					Sounds.playSounds("explo.wav", false);
				}
			}
		}
		
	}
	
	
}

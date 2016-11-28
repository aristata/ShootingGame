package com.aristatait.System;

import java.util.ArrayList;

import com.aristatait.Frame.MainFrame;

public class Explosion {
	
	
	//이미지를 그릴 x 좌표
	public int x;
	
	//이미지를 그릴 y 좌표
	public int y;
	
	//이미지를 순차적으로 그리기 위한 카운터
	public int ex_cnt;
	
	//이미지 종류를 구분하기 위한 변수값
	public int damage;

	public Explosion(int x, int y, int damage) {
		super();
		this.x = x;
		this.y = y;
		this.damage = damage;
		ex_cnt = 0;
	}
	public void effect() {
		ex_cnt ++;
	}
	public static void explosionProcess() {
		for (int i = 0; i < MainFrame.explosion_List.size(); i++) {
			MainFrame.ex = (Explosion) MainFrame.explosion_List.get(i);
			MainFrame.ex.effect();
		}
	}
	
}

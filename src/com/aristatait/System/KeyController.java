package com.aristatait.System;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import com.aristatait.Frame.ImageLoader;
import com.aristatait.Frame.MainFrame;

public class KeyController implements KeyListener {

	public static final KeyController INSTANCE = new KeyController();
	
	public static KeyController getInstance() {
		return INSTANCE;
	}
	
	//키보드 입력
	private static boolean KEY_UP = false;
	private static boolean KEY_DOWN = false;
	private static boolean KEY_LEFT = false;
	private static boolean KEY_RIGHT = false;
	private static boolean KEY_SPACE = false;
		
	private static int x;
	private static int y;
	
	public KeyController() {
			
		x = Player.getP_x();
		y = Player.getP_y();
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		// 키보드가 눌러졌을때 이벤트 처리하는 곳
			switch(e.getKeyCode()){
			case KeyEvent.VK_UP :
				KEY_UP = true;
				break;
			case KeyEvent.VK_DOWN :
				KEY_DOWN = true;
				break;
			case KeyEvent.VK_LEFT :
				KEY_LEFT = true;
				break;
			case KeyEvent.VK_RIGHT :
				KEY_RIGHT = true;
				break;
			case KeyEvent.VK_SPACE :
				KEY_SPACE = true;
				break;
			}
			
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// 키보드가 떼어졌을때 이벤트 처리하는 곳
			switch(e.getKeyCode()){
			case KeyEvent.VK_UP :
				KEY_UP = false;
				break;
			case KeyEvent.VK_DOWN :
				KEY_DOWN = false;
				break;
			case KeyEvent.VK_LEFT :
				KEY_LEFT = false;
				break;
			case KeyEvent.VK_RIGHT :
				KEY_RIGHT = false;
				break;
			case KeyEvent.VK_SPACE :
				KEY_SPACE = false;
				break;
			}
		
	}
	

	public static boolean isKEY_UP() {
		return KEY_UP;
	}

	public static boolean isKEY_DOWN() {
		return KEY_DOWN;
	}

	public static boolean isKEY_LEFT() {
		return KEY_LEFT;
	}

	public static boolean isKEY_RIGHT() {
		return KEY_RIGHT;
	}

	public static boolean isKEY_SPACE() {
		return KEY_SPACE;
	}
	
	
	
}

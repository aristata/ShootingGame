package com.aristatait.System;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sounds {

	public Sounds() {
		
	}
	//사운드 재생 메소드	
	public static void playSounds(String file, boolean Loop){
		Clip clip;
		
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream("./Sounds/"+file)));
			clip = AudioSystem.getClip();
			clip.open(ais);
			clip.start();
			if(Loop == true){
				clip.loop(-1);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		
		
	}
}

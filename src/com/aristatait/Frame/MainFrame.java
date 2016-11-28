package com.aristatait.Frame;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.aristatait.System.Enemies;
import com.aristatait.System.Explosion;
import com.aristatait.System.Fireball;
import com.aristatait.System.KeyController;
import com.aristatait.System.Player;
import com.aristatait.System.Sounds;

public class MainFrame extends JFrame implements Runnable {

	//전역변수 ===================================================================================
	
	//프레임의 가로
	public final static int FRAME_WIDTH = 1200;
	
	//프레임의 세로
	public final static int FRAME_HEIGHT = 600; 
	
	//이미지를 불러오기 위한 툴킷
	private Toolkit tk = Toolkit.getDefaultToolkit();
				
	//스레드
	private Thread thread;
	
	//더블버퍼링을 위한 변수
	private Image buffImage;
	private Graphics buffg;
	
	//배경 스크롤 속도 제어용 변수
	public int[] cx = {0, 0, 0};
	
	//전체 배경 스크롤 용 변수
	public int bx = 0;
	
	//플레이어 상태 ( 0 - default, 1 - fire, 2 - crash )
	public static int player_Status = 0 ;
	
	//플레이어 속도
	public static int player_Speed;
	
	//파이어볼 속도
	public static int fireball_Speed;
	
	//연사 속도
	public static int fire_Speed;
	
	//적 이동 속도
	public static int enemy_Speed;
	
	//게임 점수
	public static int game_Score;
	
	//플레이어 체력
	public static int player_HealthPoint;
	
	//루프 카운트
	public static int cnt = 0;
	
	//폭발 배열
	public static ArrayList<Explosion> explosion_List = new ArrayList<>();
	public static Explosion ex;
	
	//적 배열
	public static ArrayList<Enemies> eList = new ArrayList<>();
	public static Enemies enm;
	
	//파이어볼 배열
	public static ArrayList<Fireball> fire_list = new ArrayList<>();
	public static Fireball fb;

	
	//생성자 ======================================================================================
	public MainFrame() {
		
		//프레임 생성과 동시에 실행할 메소드
		init();
		start();
		
				
		//프레임 제목
		setTitle("슈팅 게임"); 
		
		//오른쪽 상단 종료버튼
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
		//프레임 크기를 지정한다
		setSize(FRAME_WIDTH, FRAME_HEIGHT); 
		
		//현재 모니터의 해상도 값을 불러온다
		Dimension screen = tk.getScreenSize(); 
		
		//프레임을 모니터의 중앙에 표시되도록 설정한다
		int frame_x_position = (int)(screen.getWidth() / 2 - FRAME_WIDTH / 2);
		int frame_y_position = (int)(screen.getHeight() / 2 - FRAME_HEIGHT / 2);
		setLocation(frame_x_position, frame_y_position);
		
		//프레임의 크기를 임의로 변경하지 못하게 설정한다
		setResizable(false);
				
		//프레임을 눈에 보이게 표시한다
		setVisible(true);
		
	}
	
	//메소드 =====================================================================================
	public void init(){
		KeyController.getInstance();
		Player.getInstance();
		ImageLoader.getInstance();	
		
		game_Score = 0; // 게임 스코어 초기화
		
		player_HealthPoint = 3; // 최초 플레이어 체력
		
		player_Speed = 5; // 플레이어 움직이는 속도
		
		fireball_Speed = 11; // 파이어볼 움직이는 속도
		
		fire_Speed = 15; // 파이어볼 발사 속도
		
		enemy_Speed = 7; // 적 움직이는 속도
		
	}
	
	public void start(){
		addKeyListener(new KeyController());
		thread = new Thread(this);
		thread.start();
		Sounds.playSounds("Game.wav", true);
		
	}
	
	public void run(){
		while(true){
			Player.playerProcess();
			Fireball.fireballProcess();
			Enemies.enemeisProcess();
			Explosion.explosionProcess();
			repaint();
			
			try {
				Thread.sleep(20);
				cnt++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}	
	
	public void paint(Graphics g) {
		
		//더블버퍼링 버퍼 크기를 화면 크기와 같게 설정한다
		buffImage = createImage(FRAME_WIDTH, FRAME_HEIGHT);
		
		//버퍼의 그래픽 객체를 얻는다
		buffg = buffImage.getGraphics();
		
		update(g);
			
	}
	
	public void update(Graphics g) {
		
		drawBackGround();
		
		drawPlayer();
		
		drawEnemies();
		
		drawFireball();
		
		drawExplosion();
		
		drawStatusText();
		
		
		
		//화면에 버퍼에 그려놓은 그림을 가져온다
		g.drawImage(buffImage, 0, 0, this);
	}
	
	//실제로 그림들을 그릴 부분
	public void drawPlayer() {
		
		switch(player_Status){
		case 0 : //평상시
			if((cnt / 5 % 2) == 0){
				//좌표(x, y)에 플레이어 이미지를 그린다
				buffg.drawImage(ImageLoader.getP_Img(1), Player.getP_x(), Player.getP_y(), this);
			}else{
				//엔진쪽에 불을 뿜는 이미지를 번갈아 그린다
				buffg.drawImage(ImageLoader.getP_Img(2), Player.getP_x(), Player.getP_y(), this);
			}
			break;
		case 1 : //파이어볼 발사시
			if((cnt / 5 % 2) == 0){
				buffg.drawImage(ImageLoader.getP_Img(3), Player.getP_x(), Player.getP_y(), this);
			}else{
				buffg.drawImage(ImageLoader.getP_Img(4), Player.getP_x(), Player.getP_y(), this);
			}
			player_Status = 0; // 총쏘기가 끝나면 플레이어 상태를 0으로 돌린다.
			break;
		case 2 : // 충돌
			break;
		}
		
		
	}
	
	//파이어볼을 그리는 메소드
	public void drawFireball() {
		for (int i = 0; i < fire_list.size(); i++) {
			fb = (Fireball)(fire_list.get(i));
			buffg.drawImage(ImageLoader.getF_Img(), fb.m_x, fb.m_y, this);
			
		}
	}
	
	//적을 그리는 메소드
	public void drawEnemies() {
		for (int i = 0; i < eList.size(); i++) {
			enm = (Enemies)(eList.get(i));
			buffg.drawImage(ImageLoader.getE_Img(), enm.x, enm.y, this);
		
			
		}
	}
	
	//배경을 그리는 메소드
	public void drawBackGround() {
		//좌표(0, 0)에서 해상도 크기만큼 화면을 지운다
		buffg.clearRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		
		//기본 값이 0인 bx가 -3500 보다 크면 실행한다.
		//bx를 0에서 -1만큼 계속 줄이므로 배경이미지의 x좌표는 계속 좌측으로 이동한다.
		if(bx > -3500) {
			buffg.drawImage(ImageLoader.getBg_Img(), bx, 0, this);
			bx -= 1;
		}else{
			bx = 0;
		}
		
		//3개의 구름 이미지를 각기 다른 속도 값으로 좌측으로 움직인다.
		for (int i = 0; i < cx.length; i++) {
			if(cx[i] < 1400) {
				cx[i] += 5 + i * 3;
			}else{
				cx[i] = 0;
			}
			buffg.drawImage(ImageLoader.getC_Img(i), 1200 - cx[i], 50 + i * 200, this);
		}
	}
	
	//폭발을 그리는 메소드
	public void drawExplosion() {
		for (int i = 0; i < explosion_List.size(); i++) {
			ex = (Explosion) explosion_List.get(i);
			
			if(ex.damage == 0){
				if(ex.ex_cnt < 7){
					Image image = ImageLoader.getEp_Img(0);
					buffg.drawImage(image, ex.x - image.getWidth(null) / 2, ex.y - image.getHeight(null) / 2, this);
				}else if(ex.ex_cnt < 14){
					Image image = ImageLoader.getEp_Img(1);
					buffg.drawImage(image, ex.x - image.getWidth(null) / 2, ex.y - image.getHeight(null) / 2, this);
				}else if(ex.ex_cnt < 21){
					Image image = ImageLoader.getEp_Img(2);
					buffg.drawImage(image, ex.x - image.getWidth(null) / 2, ex.y - image.getHeight(null) / 2, this);
				}else{
					explosion_List.remove(i);
					ex.ex_cnt = 0;
				}
			}else{
				if(ex.ex_cnt < 7){
					Image image = ImageLoader.getEp_Img(0);
					buffg.drawImage(image, ex.x + 120, ex.y + 15, this);
				}else if(ex.ex_cnt < 14){
					Image image = ImageLoader.getEp_Img(1);
					buffg.drawImage(image, ex.x + 60, ex.y + 5, this);
				}else if(ex.ex_cnt < 21){
					Image image = ImageLoader.getEp_Img(2);
					buffg.drawImage(image, ex.x + 5, ex.y + 10, this);
				}else{
					explosion_List.remove(i);
					ex.ex_cnt = 0;
				}
			}
		}
	}

	//텍스트
	public void drawStatusText() {
		//폰트 설정을 합니다.  기본폰트, 굵게, 사이즈 20
		buffg.setFont(new Font("Defualt", Font.BOLD, 20));
		
		//좌표 x : 1000, y : 70에 스코어를 표시합니다.
		buffg.drawString("SCORE : " + game_Score, 1000, 70);
		
		//좌표 x : 1000, y : 90에 플레이어 체력을 표시합니다.
		buffg.drawString("HP : " + player_HealthPoint, 1000, 90);
		
		//좌표 x : 1000, y : 110에 나타난 미사일 수를 표시합니다.
		buffg.drawString("Fireball Count : " + fire_list.size(), 1000, 110);
		
		//좌표 x : 1000, y : 130에 나타난 적의 수를 표시합니다.
		buffg.drawString("Enemy Count : " + eList.size(), 1000, 130);
				
	}
	
}

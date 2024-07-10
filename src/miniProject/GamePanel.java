package miniProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GamePanel extends JPanel {
	
	private ImageIcon bgIcon = new ImageIcon("images/back.png"); // 배경사진
	private Image bgImg = bgIcon.getImage();
	
	private JLabel[] wordLabel = new JLabel[5]; // 떨어지는 단어의 배열
	private String fallingWord = null; // 떨어지는 단어
	private JTextField input = new JTextField(20); // 단어 입력 Field
	
	private GroundPanel gndPanel = new GroundPanel(); // 게임이 진행되는 팬
	private WordList words = null; // 읽을 단어가 있는 큐
	public ScorePanel scorePanel = new ScorePanel(); // 점수 Panel
	private AddPanel addPanel = new AddPanel(); // 단어추가 Panel
	private CurrentPanel current; // 현재 행성 Panel
	
	private FallingThread thread = null; // 스레드
	private ResultFrame resultFrame;
	private String id = addPanel.getId();
	private int num = scorePanel.getScore();
	
	public int level = 1; // 레벨
	public int count = 0; // 나온 단어의 개수
	public int heart = 5; // 생명

	
	public GamePanel(WordList wordList, ScorePanel score, CurrentPanel current){
		this.words = wordList;
		this.scorePanel = score;
		
		setLayout(new BorderLayout());
		add(gndPanel, BorderLayout.CENTER); // groundPanel 부착
		
		JPanel inputPanel = new JPanel(); // 단어를 입력할 Panel
		inputPanel.setBackground(Color.GRAY);
		inputPanel.add(input); // inputPanel에 TextField 부착
		
		input.addActionListener(new ActionListener() { // 입력 액션
			@Override
			public void actionPerformed(ActionEvent e) {
				JTextField th = (JTextField)e.getSource(); // 입력한 값
				boolean flag = false; // 단어를 맞추었는 지
				
				for(int i = 0 ; i < 5; i++){
					if(th.getText().equals(wordLabel[i].getText())) { // 맞춤
						wordLabel[i].setForeground(new Color(255,0,0,0));
						th.setText(""); // 입력창 초기화
						flag = true; // 단어 맞춤
						count++; // 나온 단어 개수 증가
						
						if(count % 5 == 0){ // 단어가 5의 배수라면
							current.setImage(level); // 다음 행성으로 넘어감
							level++; // 레벨 증가
							stopGame(); // 게임 중지
							startGame(); // 게임 시작
						}
						break;
					}
				}
				if(flag) score.increase(); // 단어를 맞췄다면 점수 증가
				else { // 단어를 틀렸다면
					th.setText(""); // 입력창 초기화
					score.decrease(--heart); // 점수와 생명 감소
					if (heart==0) {
						stopGame(); // 생명이 0이면 게임 종료
						resultFrame = new ResultFrame(id, num); // 결과화면 객체 생성
						scorePanel.addFile(); // 점수 파일에 저장
					}
				};
			}
		});
		
		add(inputPanel, BorderLayout.SOUTH);
	}

	public void stopGame() { // 게임 중지 함수
		for(int i = 0; i < 5; i++){
			if(thread == null)
				return; // 스레드가 없음 
			thread.interrupt(); // 스레드 강제 종료
			thread = null;
		}
	}

	public void startInitGame() { // 게임 첫 실행 함수
		init();
		startGame();
	}

	public void startGame() { // 게임 시작
		for(int i = 0; i < 5; i++){
			fallingWord = words.getWord(level); // 레벨에 따른 단어 얻기
			wordLabel[i].setText(fallingWord); // 레이블에 단어 쓰기
			wordLabel[i].setSize(200, 30); // 레이블 크기
			int x = (int)(Math.random()*(getWidth()-200));
			wordLabel[i].setLocation(x, 0); // 레이블 위치
			wordLabel[i].setForeground(new Color(255,0,0,0)); //레이블의 글자색 설정				
			wordLabel[i].setFont(new Font("Tahoma", Font.BOLD, 15)); // 레이블 글자의 폰트 설정
			
			thread = new FallingThread(this, wordLabel[i], i, level); // 게임 스레드 호출
			thread.start(); // 스레드 시작
		}
	}
	
	public void init() { // 게임 초기 설정
		heart = 5; // 생명
		level = 1; // 레벨
		count = 0; // 나온 단어의 개수

		scorePanel.updateHeart(heart); // 생명 업데이트
		current.init(); // 현재 행성 설정
		words = new WordList("words.txt"); // 읽을 단어 파일 설정
	}

	public void setWord(String word, int i) { // 단어를 wordLabel에 쓰기
		wordLabel[i].setText(word);
	}
	
	class GroundPanel extends JPanel { // Label 5개 생성 후 부착
		public GroundPanel() {
			for(int i = 0; i < 5; i++){
				wordLabel[i] = new JLabel();
				add(wordLabel[i]);
			}
		}

		@ Override
		public void paintComponent(Graphics g) { // 배경 사진 설정
			super.paintComponent(g); // 먼저 불러야 배경색 등 덧붙혀서 설정 가능	
			g.drawImage(bgImg, 0, 0, null); // 크기 지정
		}
	}
}

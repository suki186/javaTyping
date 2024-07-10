package miniProject;

import java.awt.Color;
import javax.swing.JLabel;

class FallingThread extends Thread {
	
	private long delay = 200; // 지연 시간
	private int level; // 단계
	private int index; // 몇번째 단어인지
	
	private ResultFrame resultFrame;
	private GamePanel panel;
	private JLabel label;
	private ScorePanel scorePanel = new ScorePanel(); // 점수 Panel
	private AddPanel addPanel = new AddPanel();
	
	private boolean falling = false; // 떨이지고 있는지
	private String id = addPanel.getId();
	private int num = scorePanel.getScore(); // socre
	
	public FallingThread(GamePanel panel, JLabel label, int index, int level) {
		this.panel = panel;
		this.label = label;
		this.index = index;
		this.level = level;
	}
	
	public boolean isFalling() {
		return falling; 
	}	
	
	@Override
	synchronized public void run() {
		falling = true; // 떨어지는 중
		try{
			Thread.sleep(delay*index*20 / level); // level 마다 다른 속도
			if (panel.heart == 0) return; // 만약 생명이 0 이면 종료
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
		
		label.setForeground(Color.WHITE);
		while(true) {
			try {
				if(panel.heart == 0) {
					panel.stopGame(); // 생명 0이면 게임 종료
					num = scorePanel.getScore();
					resultFrame = new ResultFrame(id, num);
					scorePanel.addFile();
					break;
				}
				//if (panel.heart == 0) break;  // 만약 생명이 0 이면 종료
				sleep(delay); // 단어가 나오는 텀
				int y = label.getY() + level; //1픽셀 씩 아래로 이동
				if(y >= 500) {  // 만약 바닥에 닿았다면
					falling = false; // 떨어지지 않음
					//label.setText("");
					label.setForeground(new Color(255,0,0,0));
					panel.scorePanel.decrease(--panel.heart); // 생명과 점수 감소
					
					break; // 스레드 종료
				}
				label.setLocation(label.getX(), y); // 단어 재배치
				
			}
			catch (InterruptedException e) {
				falling = false; // 떨어지지 않음
				//panel.stopGame();
				//Thread.interrupted();
				return; // 스레드 종료
			}
		}
	}	
}

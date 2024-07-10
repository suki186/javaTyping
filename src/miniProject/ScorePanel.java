package miniProject;

import java.awt.Color;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScorePanel extends JPanel {
	
	public int score = 0; // 점수
	private JLabel scoreLabel = new JLabel(Integer.toString(score)); // 점수 레이블
	private JLabel heartPanel = new JLabel();
	private File file = new File("result.txt"); // 단어를 저장할 파일명
	private AddPanel addPanel = new AddPanel();
	private String id = addPanel.getId();
	
	public ScorePanel() {
		setBackground(Color.YELLOW);
		add(new JLabel("점수 : "));
		add(scoreLabel);
		add(heartPanel);
		updateHeart(5);
	}

	public void updateHeart(int heartCount){ // 생명을 업데이트하는 함수
		String star ="";
		for(int i = 0; i < heartCount; i++){ // 생명 그리기
			star += "  ★";
		}
		heartPanel.setText(star);
	}

	public void increase() { // 점수 증가 함수
		score += 200;
		scoreLabel.setText(Integer.toString(score));
	}
	
	public void decrease(int heartCount) { // 점수 감소, 생명 감소 함수
		score -= 100;
		scoreLabel.setText(Integer.toString(score));
		updateHeart(heartCount);
	}
	
	public int getScore() { // 점수를 반환하는 함수
		return score;
	}
	
	public void addFile() { // ID와 점수를 파일에 적는 함수
		try {
			FileWriter fw = new FileWriter(file, true); // 파일이 있다면 문자를 이어서 작성
			fw.write("\n" + id + ", " + score); // file에 단어 추가
			fw.close(); // file 닫기
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}

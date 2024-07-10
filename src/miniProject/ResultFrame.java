package miniProject;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ResultFrame extends JFrame {
	
	private ResultPanel resultPanel;
	
	public ResultFrame(String id, int score) {
		super("Finish");
		resultPanel = new ResultPanel(id, score);
		add(resultPanel);
		
		setSize(300, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // 화면 가운데 출력시키기
		
		
		setVisible(true);
		setResizable(false); // 크기 변경 불가
	}
	
	class ResultPanel extends JPanel {
		private int score;
		private String id;
		
		public ResultPanel(String id, int score) {
			this.id = id;
			this.score = score;
			
			setLayout(null);
			setBounds(0, 0, 390, 260);
			
			JLabel idLabel = new JLabel(id); // id가 나타날 레이블
			idLabel.setSize(30, 20);
			idLabel.setLocation(0, 0);
			idLabel.setFont(new Font("양재참숯체B", Font.BOLD, 20));
			idLabel.setForeground(Color.BLACK);
			idLabel.setBounds(115,95,180,25);
			idLabel.setText(id + " 님");
			add(idLabel);
			
			JLabel scoreLabel = new JLabel(); // score가 나타날 레이블
			scoreLabel.setSize(30, 20);
			scoreLabel.setLocation(0, 10);
			scoreLabel.setFont(new Font("양재참숯체B", Font.BOLD, 20));
			scoreLabel.setForeground(Color.BLACK);
			scoreLabel.setBounds(115,125,180,25);
			String strScore = Integer.toString(score);
			scoreLabel.setText(strScore + " 점");
			add(scoreLabel);
		}
	}
}

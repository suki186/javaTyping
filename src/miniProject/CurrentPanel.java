package miniProject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CurrentPanel extends JPanel {
	private int level;
	private String [] planetList = {"Mercury.png", "Venus.png", "Earth.png", "Mars.png", "Jupiter.png", "Saturn.png", "Uranus.png", "Neptune.png"}; 
	private ImageIcon icon = new ImageIcon("images/init.png");
	private Image img = icon.getImage();

	@ Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // 먼저 불러야 배경색 등 덧붙혀서 설정 가능	
		g.drawImage(img, 0, 0, 320, 320, null); // 크기 지정
		
	}

	public void init(){ // 초기 배경 설정
		icon = new ImageIcon("images/" + planetList[0]);
		img = icon.getImage();
		repaint(); // 새로 그리기
	}

	public void setImage(int level) { // 레벨마다 배경을 바꾸는 함수
		icon = new ImageIcon("images/" + planetList[level]);
		img = icon.getImage();
		repaint(); // 새로 그리기
	}
}

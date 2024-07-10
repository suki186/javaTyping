package miniProject;

import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;

public class GameFrame extends JFrame {
	
	private ImageIcon startIcon = new ImageIcon("images/start.png"); // 게임 시작 버튼 이미지
	private ImageIcon pressedIcon = new ImageIcon("images/pressed.png"); // 게임 시작 버튼을 눌렀을 때의 이미지

	private String id = ""; // 사용자 ID
	private JButton startBtn = new JButton(startIcon); // 시작 버튼
	private WordList wordList = new WordList("words.txt");
	private ScorePanel scorePanel = new ScorePanel(); // 점수 Panel
	private AddPanel addPanel = new AddPanel(); // 단어 추가 Panel
	public CurrentPanel currentPanel = new CurrentPanel();
	private GamePanel gamePanel = new GamePanel(wordList, scorePanel, currentPanel); // 게임화면 Panel
	

	public GameFrame(String id) {
		super("토깽이의 우주정복");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // 화면 가운데 출력시키기
		setSize(800, 600);
		
		this.id = id;
		addPanel.setId(id); // ID 설정
		makeToolBar(); // 툴바 만들기
		makeSplitPane(); // 화면 분할
		
		setVisible(true);
		setResizable(false); // 크기 변경 불가
	}
    
	private void makeToolBar() { // 툴바 만드는 함수
		JToolBar tBar = new JToolBar();
		
		startBtn.setPressedIcon(pressedIcon); // 시작 버튼
		startBtn.addActionListener(new ActionListener() { // 시작버튼을 눌렀을 때
			public void actionPerformed(ActionEvent e) {
				gamePanel.startInitGame(); // 초기 세팅 게임 시작
			}
		});
		
		tBar.add(startBtn); // 시작 버튼 부착
		tBar.add(new JLabel("장애물"));
		tBar.add(new JCheckBox()); // 장애물 유무 체크박스
		
		getContentPane().add(tBar, BorderLayout.NORTH); // 툴바를 북쪽에 부착
	}
	
	private void makeSplitPane() {
		JSplitPane hPane = new JSplitPane();
		hPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT); // 수평으로 분리
		hPane.setDividerLocation(450); // 초기 분리선 위치 지정
		getContentPane().add(hPane, BorderLayout.CENTER);
		
		JSplitPane vPane = new JSplitPane();
		vPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		hPane.setRightComponent(vPane);
		hPane.setLeftComponent(gamePanel);
		
		JSplitPane v2Pane = new JSplitPane();
		v2Pane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		vPane.setTopComponent(v2Pane);
		vPane.setDividerLocation(350); // 초기 분리선 위치 지정
		
		vPane.setBottomComponent(addPanel); // 아래쪽에 addPanel 붙이기
		v2Pane.setTopComponent(scorePanel); // 위쪽에 scorePanel 붙이기
		v2Pane.setBottomComponent(currentPanel); // 아래쪽에 currentPanel 붙이기
		
	}
	
	
	
	
}

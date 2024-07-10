package miniProject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class LoginFrame extends JFrame {
	
	private JTextField input = new JTextField(10); // 닉네임 입력 Field
	private JButton okBtn = new JButton("확인"); // 시작 버튼
	
	public LoginFrame() {
		super("로그인");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(250, 150);
		setLayout(null);
		setLocationRelativeTo(null); // 화면 가운데 출력시키기
		
		input.setBounds(70, 20, 116, 21); // ID를 입력받을 TextField 설정
		add(input);
		
		JLabel id = new JLabel("ID :"); // ID를 출력할 레이블 설정
		id.setBounds(40, 20, 50, 20);
		add(id);
		
		okBtn.addActionListener(new ActionListener() { // 확인 버튼이 눌렸을 때
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = (String)input.getText();
				GameFrame gameFrame = new GameFrame(name); // GameFrame 객체 생성
				setVisible(false); // 이 창은 보이지 않게
			}
		});
		okBtn.setBounds(75, 55, 80, 30); // 버튼 설정
		add(okBtn);
		
		setVisible(true);
		setResizable(false); // 크기 변경 불가
	}
}

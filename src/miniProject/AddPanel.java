package miniProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddPanel extends JPanel {
	
	private JTextField tf = new JTextField(20); // 추가할 단어 입력창
	private JButton saveBtn = new JButton("save"); // 저장 버튼
	private JLabel guide = new JLabel("추가할 단어를 입력한 후 save 버튼을 누르세요."); // 가이드 문구
	private JLabel notice = new JLabel(""); // 단어가 추가되었는지 안내 문구
	private JLabel id = new JLabel("");
	private String name = " ";
	private File file = new File("words.txt"); // 단어를 저장할 파일명
	
	public AddPanel() {
		setLayout(null); // 배치관리자 제거
		
		guide.setSize(300, 40); // guide 레이블 설정
		guide.setLocation(20, 10);
		add(guide);
		
		tf.setSize(150, 20); // 단어 추가 TextField 설정
		tf.setLocation(30, 55);
		add(tf);
		
		saveBtn.setSize(60, 30); // 저장 버튼 설정
		saveBtn.setLocation(200, 50);
		saveBtn.setFont(new Font("DomCasual BT", Font.PLAIN, 18)); // 저장버튼의 폰트 설정
		add(saveBtn); // 저장 버튼 부착
		saveBtn.addActionListener(new addWordAction()); // 저장버튼을 눌렀을 때
		
		notice.setSize(300, 20); // 저장 여부 notice 레이블 설정
		notice.setLocation(30, 75);
		add(notice);
		
		id.setSize(250, 40); // id 나타날 레이블 설정
		id.setLocation(30, 100);
		add(id);
		
		
	}
	
	private void addWord(String word) { // 파일에 단어를 추가하는 함수
		
		try {
			FileWriter fw = new FileWriter(file, true); // 파일이 있다면 문자를 이어서 작성
			fw.write("\n" + word); // file에 단어 추가
			fw.close(); // file 닫기
			
			notice.setOpaque(true);
			notice.setForeground(Color.BLUE); // 안내 Label 폰트 색상
			notice.setText("[" + word + "]" + " 단어를 저장했습니다."); // 저장 완료 문구 출력
		}
		catch (IOException e) {
			notice.setText("[" + word + "]" + " 단어 저장에 실패했습니다."); // 저장 실패 문구 출력
			e.printStackTrace();
		}
	}
	
	public void setId(String name) { // ID를 설정하는 함수
		this.name = name;
		
		id.setOpaque(true);
		id.setBackground(Color.YELLOW); // 배경색 설정
		id.setFont(new Font("HY울릉도B", Font.PLAIN, 30)); // 폰트 설정
		id.setText("ID : " + name); // ID 쓰기
	}
	
	public String getId() { // ID를 반환하는 함수
		return name;
	}
	
	private class addWordAction implements ActionListener { // save 버튼이 눌리면 호출될 함수
		@Override
		public void actionPerformed(ActionEvent e) {
			String word = tf.getText(); // 입력창에 쓴 단어 읽기
			addWord(word); // 파일에 단어 추가
			tf.setText(""); // 입력창 초기화
		}
	}
}

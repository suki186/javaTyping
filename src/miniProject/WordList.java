package miniProject;
import java.util.Queue;
import java.util.LinkedList;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class WordList {
	// 연결리스트를 이용한 큐 queue
	private Queue<String> queue = new LinkedList<>();
	
	public WordList(String fileName) {
		try {
			Scanner scanner = new Scanner(new FileReader(fileName));
			while(scanner.hasNext()) { // 읽을 단어가 있는 동안 반복
				String word = scanner.nextLine(); // 단어 word에 저장
				queue.add(word); // 큐에 단어 저장
			}
			scanner.close();
		}
		catch (FileNotFoundException e) { // file이 없다면
			System.out.println("파일을 찾을 수 없습니다.");
			e.printStackTrace();
		}
	}
	
	public String getWord(int level) { // 단어를 얻어오는 함수
		return queue.poll(); // 큐의 맨앞 값을 반환한 후 삭제한다
	}
}

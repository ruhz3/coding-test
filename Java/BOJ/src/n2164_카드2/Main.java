package n2164_카드2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// 00. LinkedList를 사용하자.
		LinkedList<Integer> list = new LinkedList<Integer>();
		for(int i = 1; i <= N; i++) {
			list.add(i);
		}
		// 01. 문제 그대로 숫자를 다뤄준다.
		while(list.size() > 1) {
			list.removeFirst();
			list.add(list.pop());
		}
		// 02. 남은 원소를 반환한다.
		System.out.println(list.peek());
	}
}

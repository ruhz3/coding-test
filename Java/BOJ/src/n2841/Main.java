package n2841;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	/* 악보*/
	static ArrayList<Stack<Integer>> tab;
	
	public static void main(String[] args) throws IOException {
		tab = new ArrayList<Stack<Integer>>();
		for(int i = 0; i < 6; i++) tab.add(new Stack<Integer>());
		int count = 0;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int string = Integer.parseInt(st.nextToken())-1;
			int fret = Integer.parseInt(st.nextToken());
			count += play(string, fret, 0);
		}
		
		System.out.println(count);
	}
	
	public static int play(int string, int fret, int count) {
		Stack<Integer> stack = tab.get(string);
		// 00. 스택이 비어엤는 경우 추가하고 1 리턴
		if(stack.size() == 0) {
			stack.add(fret);
			return count+1;
		}
		
		// 01. 현재 해당 스트링에 누르고 있는 최고 프렛과 비교하여 필요하다면 손가락을 떼준다.
		int peek = stack.peek();
		if (fret > peek) {
			stack.add(fret);
			return count+1;
		} else if(fret < peek) {
			stack.pop();
			return play(string, fret, count+1);
		}
		
		return count;
	}
}

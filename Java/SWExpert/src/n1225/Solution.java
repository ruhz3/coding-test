package n1225;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
 * 1225_암호생성기
 * 메모리 :	19,600 kb
 * 실행시간 :	127 ms
 */

public class Solution {
	public static void main(String[] args) throws IOException {
		LinkedList<Integer> list = new LinkedList<Integer>(); 
		
		StringBuilder result = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input;
		while(true) {
			input = br.readLine();
			if(input == null || input.equals("")) break;
			
			// 00. 입력한다.
			result.append("#").append(Integer.parseInt(input)).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 8; i++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
			
			// 01. 프로그램을 실행한다.
			boolean flag = false;
			while(true) {
				for(int i = 1; i <= 5; i++) {
					int get = list.poll()-i;
					if(get <= 0) {
						list.add(0);
						flag = true;
						break;
					} else {
						list.add(get);
					}
				}
				if(flag) break;
			}
			
			// 02. 결과를 출력한다.
			for(int elem : list) {
		 		result.append(elem).append(" ");
			}
			result.append("\n");
			list.clear();
		}
		System.out.println(result.toString());
	}
}

package n1228_암호문1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
 * 1228_암호문1
 * 메모리 :	19,368 kb
 * 실행시간 :	104 ms
 */

public class Solution {
	static LinkedList<Integer> list = new LinkedList<Integer>();
	
	public static void main(String[] args) throws IOException {
		StringBuilder result = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for(int tc = 1; tc <= 10; tc++) {
			result.append("#").append(tc).append(" ");
			// 00. 입력 한다.
			list.clear();
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
			// 01. 문제 그대로 입력 받는다. 
			int O = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < O; i++) {
				st.nextToken();
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				for(int loop = 0; loop < y; loop++) {
					list.add(x++, Integer.parseInt(st.nextToken()));
				}
			}
			// 02. 10자리 까지만 출력한다.
			N = N > 10 ? 10 : N;
			for(int i = 0; i < N; i++) {
				result.append(list.get(i)).append(" ");
			}
			result.append("\n");
		}
		System.out.println(result.toString());
	}
}

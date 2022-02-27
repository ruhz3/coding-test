package n7964_부먹왕국의_차원_관문;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 7964_부먹왕국의 차원 관문
 * 메모리 :	92,552 kb
 * 실행시간 :	274 ms
 */

public class Solution {
	static Queue<Character> city = new LinkedList<Character>();
	static int N, D;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder result = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());

			// 00. Queue에 현재 도시들을 순서대로 입력한다. 
			city.clear();
			city.add('1');
			st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < N; i++)
				city.add(st.nextToken().charAt(0));
			city.add('1');
			
			// 01. 하나씩 꺼내면서 필요한 최대 지점에 세운다. 
			int charge = 0;
			int count = 0;
			while(!city.isEmpty()) {
				char key = city.poll();
				if(key == '1') {
					charge = D;
				} else if(charge <= 0) {
					charge = D;
					count++;
				}
				charge--;
			}
			// 02. 결과를 출력한다.
			result.append("#").append(tc).append(" ").append(count).append("\n");
		}
		System.out.println(result.toString());
	}
}

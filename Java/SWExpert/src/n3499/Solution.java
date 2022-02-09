package n3499;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 3499_퍼펙트 셔플
 * 메모리 :	24,372 kb
 * 실행시간 :	116 ms
 */

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		Queue<String> aboveQueue = new LinkedList<String>();
		Queue<String> belowQueue = new LinkedList<String>();
		
		StringBuilder result = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			result.append("#").append(tc).append(" ");
			int N = Integer.parseInt(br.readLine());
			
			// 00. 절반을 나눠서 큐에 넣는다.
			st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < (N+1)/2; i++) aboveQueue.add(st.nextToken());
			for(int i = (N+1)/2; i < N; i++) belowQueue.add(st.nextToken());
			
			// 01. 번갈아가며 출력한다.
			boolean flag = true;
			for(int i = 0; i < N; i++) {
				if(flag) result.append(aboveQueue.poll()).append(" ");
				else result.append(belowQueue.poll()).append(" ");
				flag = !flag;
			}
			result.append("\n");
		}
		System.out.println(result.toString());
	}
}

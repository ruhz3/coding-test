package n1206_View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 1206_View
 * 메모리 :	24,448 kb
 * 실행시간 :	125 ms
 */

public class Solution {
	static PriorityQueue<Integer> pQueue = new PriorityQueue<Integer>(Collections.reverseOrder());
	static int[] buildings;
	static boolean[] check;
	static int N;
	
	public static void main(String[] args) throws IOException {
		StringBuilder result = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int tc = 1; tc <= 10; tc++) {
			// 00. 입력한다.
			N = Integer.parseInt(br.readLine());
			buildings = new int[N];
			check = new boolean[N];
			Arrays.fill(check, true);
			st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < N; i++) {
				buildings[i] = Integer.parseInt(st.nextToken());
			}
			// 01. 각 자리별로 강변 뷰 세대를 세어주되, 가능성이 없는 집은 건너뛴다.
			int sum = 0;
			for(int i = 2; i < N-2; i++) {
				if(check[i]) sum += getViewCount(i);
			}
			// 02. 출력한다.
			result.append("#").append(tc).append(" ").append(sum).append("\n");
		}
		System.out.println(result.toString());
	}
	
	private static int getViewCount(int index) {
		// 00. 자신 주변 4개 셀 중 최대값을 찾는다.
		pQueue.add(buildings[index-2]);
		pQueue.add(buildings[index-1]);
		pQueue.add(buildings[index+1]);
		pQueue.add(buildings[index+2]);
		
		int max = buildings[index];
		int postMax = pQueue.poll();
		pQueue.clear();
		
		// 01. 자신이 제일 크다면, 자신의 전망을 반환한다.
		if(max <= postMax) {
			return 0;
		}
		else {
			check[index+1] = false;
			check[index+2] = false;
			return max - postMax;
		}
	}
}

package n9229;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 9229_한빈이와 Spot Mart
 * 메모리 :	25,936 kb
 * 실행시간 :	153 ms
 */

public class Solution {
	static int[] weights = new int[1000];
	static int T, N, M;
	
	public static int getMaxSum(int idx, int nowWeight, int nowCarry) {
		// 00. 양손 들고 있는 경우에만 반환한다.
		if (nowCarry == 2) {
			return nowWeight;
		}
		// 01. 재귀로 최댓값을 찾아내고, default 리턴 값은 -1이다.
		int maxSum = -1;
		for(int i = idx + 1; i < N; i++) {
			if(nowWeight + weights[i] > M) continue;
			int sum = getMaxSum(i, nowWeight + weights[i], nowCarry+1);
			if(sum > maxSum) maxSum = sum;
		}
		return maxSum;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder result = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			result.append("#").append(tc).append(" ");
			// 00. 입력한다.
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				weights[i] = Integer.parseInt(st.nextToken());
			}
			
			// 01. 최댓값을 찾아낸다.
			result.append(getMaxSum(-1, 0, 0)).append("\n");
		}
		System.out.println(result.toString());
	}
}

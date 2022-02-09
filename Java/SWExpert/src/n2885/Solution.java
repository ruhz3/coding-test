package n2885;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 2885_농작물 수확하기
 * 메모리 :	20,396 kb
 * 실행시간 :	137 ms
 */

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		int[][] farm = new int[51][51];
		
		StringBuilder result = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input;
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			result.append("#").append(tc).append(" ");
			// 00. 농장을 이차원 배열에 입력한다.
			int N = Integer.parseInt(br.readLine());
			for(int i = 0; i < N; i++) {
				input = br.readLine();
				for(int j = 0; j < N; j++) {
					farm[i][j] = input.charAt(j) - '0';
				}
			}
			// 01. 크기에 따라 합을 구한다.
			int sum = 0;
			int k = N/2;
			for(int i = 0; i < N; i++) {
				for(int j = k; j < N-k; j++) {
					sum += farm[i][j];
				}
				if(i < N/2) k--;
				else k++;
			}
			result.append(sum).append("\n");
		}
		System.out.println(result.toString());
	}
}

package n2001;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		// * 크기가 15 밖에 안 되니까, 미리 생성한다. 
		int[][] map = new int[15][15];
		
		// 00. 입력을 받는다.
		StringBuilder result = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// 01. 좌상단 셀을 기준으로 탐색하며 최대합을 찾는다.
			int maxSum = 0;
			int sum = 0;
			
			// 01-01. i, j는 좌상단 셀의 좌표다.
			for(int i = 0; i < N-(M-1); i++) {
				for(int j = 0; j < N-(M-1); j++) {
					sum = 0;
					// 01-02. 좌상단 셀 기준으로 M 만큼의 구역의 합을 구한다.
					for(int y = i; y < i + M; y++) {
						for(int x = j; x < j + M; x++) {
							sum += map[y][x]; 
						}
					}
					// 01-03. 합을 구했으면 최댓값과 비교한다.
					if(sum > maxSum) {
						maxSum = sum;
					}
				}
			}
			// 02. 최대합을 출력할 결과 문자열에 붙여준다. 
			result.append("#").append(tc).append(" ").append(maxSum).append("\n");
		}
		// 03. 결과를 출력한다.
		System.out.println(result.toString());
	}
}

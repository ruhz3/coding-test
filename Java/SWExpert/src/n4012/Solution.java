package n4012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 4012_요리사
 * 메모리 :	22,872 kb
 * 실행시간 :	220 ms
 */
public class Solution {
	static int[][] map = new int[16][16];
	static boolean[] recipe = new boolean[16];
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder result = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 00. 입력한다.
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			Arrays.fill(recipe, false);
			result.append("#").append(tc).append(" ").append(makeRecipe(-1, 0)).append("\n");
		}
		System.out.println(result.toString());
	}
	private static int makeRecipe(int idx, int cnt) {
		if(cnt == N/2) return getDiff();
		if(idx >= N) return Integer.MAX_VALUE;

		int minDiff = Integer.MAX_VALUE;
		for(int i = idx+1; i < N; i++) {
			recipe[i] = true;
			minDiff = Math.min(minDiff, makeRecipe(i, cnt+1));
			recipe[i] = false;
		}
		return minDiff;
	}
	private static int getDiff() {
		int A = 0;
		int B = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(recipe[i] && recipe[j]) A += map[i][j];
				else if(!recipe[i] && !recipe[j]) B += map[i][j];
			}
		}
		return Math.abs(A-B);
	}
}

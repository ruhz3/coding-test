package IM_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int[][] map = new int[8][8];
	static int[] rowDir = {-1, 0, 1, 0};  // 상, 우, 하, 좌
	static int[] colDir = {0, 1, 0, -1};  // 상, 우, 하, 좌
	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder result = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int areaCount = 0;
			int gkR = 0;
			int gkC = 0;
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] != 1) areaCount++;
					if(map[i][j] == 2) {
						gkR = i;
						gkC = j;
					}
				}
			}
			int count = 1;
			for(int dir = 0; dir < 4; dir++) {
				int r = gkR;
				int c = gkC;
				while(true) {
					int nr = r + rowDir[dir];
					int nc = c + colDir[dir];
					if(nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] == 1) break;
					count++;
					r = nr;
					c = nc;
				}
			}
			result.append("#").append(tc).append(" ").append(areaCount - count).append("\n");
		}
		System.out.println(result.toString());
	}
}

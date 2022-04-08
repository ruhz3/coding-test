package n1953_탈주범_검거;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static boolean[][] tunnels = {
			{false, false, false, false},
			{true,  true,  true,  true},	// 상, 우, 하, 좌
			{true,  false, true,  false},	// 상, 하
			{false, true,  false, true},	// 우, 좌
			{true,  true,  false, false},	// 상, 우
			{false, true,  true,  false},	// 우, 하
			{false, false, true,  true},	// 하, 좌
			{true,  false, false, true},	// 상, 좌
	};
	static int[][] map = new int[50][50];
	static int N, M, R, C, L;
	
	static Queue<int[]> queue = new LinkedList<int[]>();
	static boolean[][] isVisited = new boolean[50][50];
	static int[] rowDir = {-1, 0, 1, 0};
	static int[] colDir = {0, 1, 0, -1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					isVisited[i][j] = false;
				}
			}
			
			int answer = 0;
			int time = 1;
			
			queue.clear();
			queue.offer(new int[]{R, C});
			isVisited[R][C] = true;
			answer++;
			
			while(!queue.isEmpty() && time < L) {
				int len = queue.size();
				for(int i = 0; i < len; i++) {
					int[] coord = queue.poll();
					int r = coord[0];
					int c = coord[1];
					
					boolean[] tunnel = tunnels[map[r][c]];
					for(int dir = 0; dir < 4; dir++) {
						if(!tunnel[dir]) continue;
						
						int nr = r + rowDir[dir];
						int nc = c + colDir[dir];
						if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
						if(isVisited[nr][nc] || !tunnels[map[nr][nc]][(dir + 2) % 4]) continue;  
						
						queue.offer(new int[]{nr, nc});
						isVisited[nr][nc] = true;
						answer++;
					}
				}
				time++;
			}
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}
}

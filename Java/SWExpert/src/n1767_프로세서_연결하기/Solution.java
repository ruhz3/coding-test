package n1767_프로세서_연결하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Coord{
	int r, c;
	public Coord (int r, int c) {
		super();
		this.r = r;
		this.c = c; }
}

public class Solution {
	private static int N, size, min;
	private static int map[][];
	
	private static int[] rowDir = {-1, 0, 1, 0}; 
	private static int[] colDir = {0, 1, 0, -1};
	private static Coord cores[] = new Coord[12];
	private static boolean isVisited[] = new boolean[12];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder result = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			size = 0;
			for(int i = 1; i < N - 1; i++) {
				for(int j = 1; j < N - 1; j++) {
					if(map[i][j] == 1) {
						cores[size++] = new Coord(i, j);
					}
				}
			}
			
			min = Integer.MAX_VALUE;
			for(int i = size; i >= 0; i--) {
				combination(0, 0, i);
				if(min < Integer.MAX_VALUE) break;
			}
			result.append("#").append(tc).append(" ").append(min).append("\n");
		}
		System.out.println(result.toString());
	}
	
	public static void combination(int idx, int cnt, int R) {
		if(cnt == R) {
			dfs(0, 0);
			return;
		}
		for(int i = idx; i < size; i++) {
			isVisited[i] = true;
			combination(i + 1, cnt + 1, R);
			isVisited[i] = false;
		}
	}
	
	public static void dfs(int idx, int cnt) {
		if(idx == size) {
			min = Math.min(min, cnt);
			return;
		}
		if(!isVisited[idx]) {
			dfs(idx + 1, cnt);
			return;
		}
		for(int dir = 0; dir < 4; dir++) {
			int nr = cores[idx].r;
			int nc = cores[idx].c;
			int tmp = 0;
			boolean isDone = false;
			while(true) {
				nr += rowDir[dir];
				nc += colDir[dir];
				if(nr < 0 || nr >= N || nc < 0 || nc >= N) {
					isDone = true;
					break;
				}
				if(map[nr][nc] != 0) break;
				map[nr][nc] = 2;
				tmp++;
			}
			if(isDone) dfs(idx + 1, cnt + tmp);
			while(true) {
				nr -= rowDir[dir];
				nc -= colDir[dir];
				if(nr == cores[idx].r && nc == cores[idx].c) break;
				map[nr][nc] = 0;
			}
		}
	}
}
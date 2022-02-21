package n1987;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
	static char[][] board;
	static boolean[] check;
	static int R, C; // 보드 크기
	static int[] rowDir = {-1, 1, 0, 0};
	static int[] colDir = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new char[R][];
		check = new boolean['Z'-'A'+1];
		
		for(int i = 0; i < R; i++) {
			board[i] = br.readLine().toCharArray();
		}
		System.out.println(findMaxCount(0, 0, 1));
	}
	
	private static int findMaxCount(int r, int c, int cnt) {
		check[board[r][c] - 'A'] = true;
		boolean isStucked = true;
		int maxCount = 0;
		for(int dir = 0; dir < 4; dir++) {
			int nr = r + rowDir[dir];
			int nc = c + colDir[dir];
			
			// 00. 범위를 벗어나거나, 갔던 알파벳이라면 못 간다.
			if(nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
			if(check[board[nr][nc] - 'A']) continue;
			
			// 01. 갈 곳이 있다면 가보자!
			isStucked = false;
			maxCount = Math.max(maxCount, findMaxCount(nr, nc, cnt+1));
			check[board[nr][nc] - 'A'] = false;
		}
		
		if(isStucked) return cnt;
		else return maxCount;
	}
	
}

package n3109;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 빵집 */
public class Main {
	static boolean[][] check;
	static char[][] map;
	static int R, C;
	static int[] moveDir = {-1, 0, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// 입력
		st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][]; 
		
		for(int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		// DFS
		int count = 0;
		for(int r = 0; r < R; r++) {
			if(findPath(r, 0)) count++;
		}
		System.out.println(count);
	}
	
	private static boolean findPath(int r, int c) {
		// 00. 이제 못 지나갑니다.
		map[r][c] = 'x';
		if(c == C-1)
			return true;
		
		// 01. 위를 기준으로 차곡차곡 경로를 포개놓는다. 
		for(int i = 0; i < 3; i++) {
			int nr = r + moveDir[i];
			int nc = c + 1;
			if(nr < 0 || nr >= R || nc < 0 || nc >= C || map[nr][nc] == 'x') continue;
			
			if(findPath(nr, nc))
				return true;
		}
		return false;
	}
}

package n20057_마법사_상어와_토네이도;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] A;
	static int[] rowDir = {0, 1, 0, -1};  // 좌, 하, 우, 상
	static int[] colDir = {-1, 0, 1, 0};
	static int[][][] masks = {
			{	// 좌
				{0, 0, 2, 0, 0},
				{0, 10, 7, 1, 0},
				{5, 0, 0, 0, 0},
				{0, 10, 7, 1, 0},
				{0, 0, 2, 0, 0}
			},
			{	// 하
				{0, 0, 0, 0, 0},
				{0, 1, 0, 1, 0},
				{2, 7, 0, 7, 2},
				{0, 10, 0, 10, 0},
				{0, 0, 5, 0, 0}
			},
			{	// 우
				{0, 0, 2, 0, 0},
				{0, 1, 7, 10, 0},
				{0, 0, 0, 0, 5},
				{0, 1, 7, 10, 0},
				{0, 0, 2, 0, 0}
			},
			{	// 상
				{0, 0, 5, 0, 0},
				{0, 10, 0, 10, 0},
				{2, 7, 0, 7, 2},
				{0, 1, 0, 1, 0},
				{0, 0, 0, 0, 0}
			}
	};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// 00. 입력한다.
		N = Integer.parseInt(br.readLine());
		A = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 01. 토네이도를 회전시킨다.
		int nr = N/2;
		int nc = N/2;
		int level = 1;
		int dir = 0;
		int sum = 0;
	OUTER : while(level <= N) {
			// 01-00. 방향을 2번 꺾을 떄마다, 한 칸씩 더 움직인다.
			for(int i = 0; i < 2; i++) {
				for(int j = 0; j < level; j++) {
					nr += rowDir[dir];
					nc += colDir[dir];
					if(nc < 0) break OUTER;
					
					// 01-01. 토네이도가 위치한 곳에 바람을 일으킨다.
					sum += blow(nr, nc, dir);
				}
				// 01-02. 좌, 하, 우, 상 (반시계방향)으로 꺾는다.
				dir = (dir + 1)%4;
			}
			level++;
		}
		System.out.println(sum);
	}
	private static int blow(int r, int c, int dir) {
		// 00. 방향에 맞는 mask를 고른다.
		int[][] mask = masks[dir];
		int sand = A[r][c];
		int sr = r - 2;
		int sc = c - 2;
		
		// 01. mask를 적용하되, 경계를 벗어난 모래들은 누적해 저장한다.
		int result = 0;
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				if(mask[i][j] == 0) continue;
				int s = A[r][c] * mask[i][j] / 100 ;
				sand -= s;
				
				int nr = sr + i;
				int nc = sc + j;
				if(nr < 0 || nr >= N || nc < 0 || nc >= N) {
					result += s;
				} else {
					A[nr][nc] += s;
				}
			}
		}
		// 02. 남은 모래들을 방향에 맞게 옮겨준다.
		int lr = r + rowDir[dir];
		int lc = c + colDir[dir];
		if(lr < 0 || lr >= N || lc < 0 || lc >= N) result += sand;
		else A[lr][lc] += sand;
		
		// 03. 휩쓸고 지나간 자리는 아무것도 남지 않는다...
		A[r][c] = 0;
		
		// 04. 경계를 벗어난 모래를 반환한다.
		return result;
	}
}

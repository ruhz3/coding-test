package n10026_적록색약;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static char[][] picture;
	static boolean[][] visited;
	static int[] rowDir = {-1, 0, 1, 0};
	static int[] colDir = {0, 1, 0, -1};
	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		picture = new char[N][];
		visited = new boolean[N][N];
		for(int i = 0; i < N; i++) {
			picture[i] = br.readLine().toCharArray();
		}
		// 00. 먼저 정상인의 시각으로 구별해본다.
		int count = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(!visited[i][j]) {
					makeArea(i, j, picture[i][j]);
					count++;
				}
			}
		}
		System.out.print(count + " ");
		
		// 01. 적록색약의 그림을 만들어준다.
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(picture[i][j] == 'G') picture[i][j] = 'R';
			}
		}
		
		// 02. 초기화한다.
		for(int i = 0; i < N; i++) {
			Arrays.fill(visited[i], false);
		}
		
		// 03. 적록색약의 시각으로 구별해본다.
		count = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(!visited[i][j]) {
					makeArea(i, j, picture[i][j]);
					count++;
				}
			}
		}
		System.out.println(count);
	}
	private static void makeArea(int r, int c, char color) {
		visited[r][c] = true;
		for(int dir = 0; dir < 4; dir++) {
			int nr = r + rowDir[dir];
			int nc = c + colDir[dir];
			if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
			if(!visited[nr][nc] && picture[nr][nc] == color) makeArea(nr, nc, color);
		}
	}
}

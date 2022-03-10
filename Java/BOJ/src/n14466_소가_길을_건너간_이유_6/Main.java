package n14466_소가_길을_건너간_이유_6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Coord {
	int r, c;
	public Coord(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class Main {
	static Queue<Coord> queue = new LinkedList<Coord>();
	static int[] cows;
	static boolean[][] isVisited;
	static boolean[][][] isBlocked;
	static int[] rowDir = {-1, 0, 1, 0};  //상, 우, 하, 좌
	static int[] colDir = {0, 1, 0, -1};  //상, 우, 하, 좌
	static int[][] map;
	static int N, K, R;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		// 00. 어느 셀에서 어느 방향이 막혔는지 표시한다. 
		isBlocked = new boolean [N+1][N+1][4];
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r1 = Integer.parseInt(st.nextToken());
			int c1 = Integer.parseInt(st.nextToken());
			int r2 = Integer.parseInt(st.nextToken());
			int c2 = Integer.parseInt(st.nextToken());
			if(r1 > r2) {
				isBlocked[r1][c1][0] = true;
				isBlocked[r2][c2][2] = true;
				continue;
			}
			if(r1 < r2) {
				isBlocked[r1][c1][2] = true;
				isBlocked[r2][c2][0] = true;
				continue;
			}
			if(c1 > c2) {
				isBlocked[r1][c1][3] = true;
				isBlocked[r2][c2][1] = true;
				continue;
			}
			if(c1 < c2) {
				isBlocked[r1][c1][1] = true;
				isBlocked[r2][c2][3] = true;
				continue;
			}
		}
		// 01. 구역을 나눈다. 
		map = new int[N+1][N+1];
		isVisited = new boolean[N+1][N+1];
		int level = 1;
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(map[i][j] == 0) {
					makeSection(i, j, level++);
				}
			}
		}
		// 02. 소가 소속한 구역을 저장한다.
		cows = new int[K];
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			cows[i] = map[r][c];
		}
		 
		int count = 0;
		for(int i = 0; i < K; i++) {
			for(int j = i+1; j < K; j++) {
				if(cows[i] != cows[j]) count++;
			}
		}
		System.out.println(count);
	}
	private static void makeSection(int r, int c, int level) {
		map[r][c] = level;
		queue.add(new Coord(r, c));
		while(!queue.isEmpty()) {
			Coord coord = queue.poll();
			for(int dir = 0; dir < 4; dir++) {
				int nr = coord.r + rowDir[dir];
				int nc = coord.c + colDir[dir];
				if(nr < 1 || nr > N || nc < 1 || nc > N || map[nr][nc] != 0 || isBlocked[coord.r][coord.c][dir]) continue;
				map[nr][nc] = level;
				queue.add(new Coord(nr, nc));
			}
		}
		
	}
}

package n1600_말이_되고픈_원숭이;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Coord {
	int r, c;
	int horseCount;
	public Coord(int r, int c, int horseCount) {
		super();
		this.r = r;
		this.c = c;
		this.horseCount = horseCount;
	}
}

public class Main {  
	static int K;
	static int W, H;
	static boolean[][] map;
	static boolean[][][] isVisited;
	
	static Queue<Coord> queue = new LinkedList<Coord>();
	static int[] rowDir = {-1, 0, 1, 0};  // 상, 우, 하, 좌
	static int[] colDir = {0, 1, 0, -1};  // 상, 우, 하, 좌
	static int[] horseRowDir = {-2, -2, -1, 1, 2, 2, 1, -1};  // 상좌, 상우, 우상, 우하, 하우, 하좌, 좌하, 좌상
	static int[] horseColDir = {-1, 1, 2, 2, 1, -1, -2, -2};  // 상좌, 상우, 우상, 우하, 하우, 하좌, 좌하, 좌상
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 00. 입력한다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new boolean[H][W];
		isVisited = new boolean[H][W][K+1];
		
		for(int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()) == 0;
			}
		}
		if(H == 1 && W == 1) {
			System.out.println(0);
			return;
		}
		
		// 01. BFS 탐색한다.
		isVisited[0][0][0] = true;
		queue.offer(new Coord(0, 0, 0));
		
		int time = 1;
		boolean isDone = false;
	OUTER : while(!queue.isEmpty()) {
			for(int i = 0, len = queue.size(); i < len; i++) {
				Coord c = queue.poll();
				if(c.horseCount < K) {
					for(int dir = 0; dir < 8; dir++) {
						int nr = c.r + horseRowDir[dir];
						int nc = c.c + horseColDir[dir];
						if(nr < 0 || nr >= H || nc < 0 || nc >= W) continue;
						if(isVisited[nr][nc][c.horseCount+1] || !map[nr][nc]) continue;
						if(nr == H-1 && nc == W-1) {
							isDone = true;
							break OUTER;
						}
						isVisited[nr][nc][c.horseCount+1] = true;
						queue.offer(new Coord(nr, nc, c.horseCount + 1));
					}				
				}
				for(int dir = 0; dir < 4; dir++) {
					int nr = c.r + rowDir[dir];
					int nc = c.c + colDir[dir];
					if(nr < 0 || nr >= H || nc < 0 || nc >= W) continue;
					if(isVisited[nr][nc][c.horseCount] || !map[nr][nc]) continue;
					if(nr == H-1 && nc == W-1) {
						isDone = true;
						break OUTER;
					}
					isVisited[nr][nc][c.horseCount] = true;
					queue.offer(new Coord(nr, nc, c.horseCount));
				}
			}
			time++;
		}
		if(isDone) System.out.println(time);
		else System.out.println(-1);
	}
}

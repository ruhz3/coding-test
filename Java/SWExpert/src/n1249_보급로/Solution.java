package n1249_보급로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;


class Coord implements Comparable<Coord>{
	int r, c;
	int value;
	public Coord(int r, int c, int value) {
		super();
		this.r = r;
		this.c = c;
		this.value = value;
	}
	@Override
	public int compareTo(Coord o) {
		return value - o.value;
	}
}

public class Solution {
	static PriorityQueue<Coord> queue = new PriorityQueue<Coord>();
	static int[] rowDir = {-1, 0, 1, 0};
	static int[] colDir = {0, 1, 0, -1};
	
	static boolean[][] isVisited = new boolean[100][100];
	static int[][] map = new int[100][100];
	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder result = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			// 00. 입력한다.
			N = Integer.parseInt(br.readLine());
			for(int i = 0; i < N; i++) {
				String in = br.readLine();
				for(int j = 0; j < N; j++) {
					map[i][j] = in.charAt(j) - '0';
					isVisited[i][j] = false;
				}
			}
			// 01. BFS 탐색한다.
			queue.clear();
			queue.offer(new Coord(0, 0, map[0][0]));
			isVisited[0][0] = true;
			int answer = 0;
		OUTER : while(!queue.isEmpty()) {
				int len = queue.size();
				for(int i = 0; i < len; i++) {
					Coord c = queue.poll();
					/* 탈출 조건*/
					if(c.r == N-1 && c.c == N-1) {
						answer = c.value;
						break OUTER;
					}
					for(int dir = 0; dir < 4; dir++) {
						int nr = c.r + rowDir[dir];
						int nc = c.c + colDir[dir];
						if(nr < 0 || nr >= N || nc < 0 || nc >= N || isVisited[nr][nc]) continue;
						queue.offer(new Coord(nr, nc, c.value + map[nr][nc]));
						isVisited[nr][nc] = true;
					}
				}
			}
			result.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(result.toString());
	}
}

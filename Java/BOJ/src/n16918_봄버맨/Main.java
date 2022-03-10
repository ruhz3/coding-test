package n16918_봄버맨;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


class Bomb {
	int r, c;
	public Bomb(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class Main {
	static Queue<Integer> 
	static Queue<Bomb> queue = new LinkedList<Bomb>(); 
	static int[][] map;
	static int R, C, N;
	static int[] rowDir = {-1, 0, 1, 0};
	static int[] colDir = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		for(int i = 0; i < R; i++) {
			String input = br.readLine();
			for(int j = 0; j < C; j++) {
				if(input.charAt(j) == 'O') map[i][j] = 3;
				else map[i][j] = 0;
			}
		}
		int time = 0;
		timePass();
		print();
		time++;
		while(time < N) {
			setBombs();
			timePass();
			print();
			time++;
		}
		
	}
	private static void timePass() {
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(map[i][j] > 1) map[i][j]--;
				if(map[i][j] == 1) {
					map[i][j] = 0;
					for(int dir = 0; dir < 4; dir++) {
						int nr = i + rowDir[dir];
						int nc = j + colDir[dir];
						if(nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
						map[nr][nc] = 0;
					}
				}
			}
		}
	}
	
	private static void setBombs() {
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(map[i][j] == 0) map[i][j] = 3;
			}
		}
	}
	private static void print() {
		System.out.println("=========================");
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(map[i][j] == 0) System.out.print(".");
				else System.out.print("O");
			}System.out.println();
		}
	}
}

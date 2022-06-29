package n14499_주사위_굴리기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static int[][] map;
	static int[] commands;
	static Dice dice;
	
	static class Dice {
		int r, c;
		int top, bottom;
		int north, south, east, west;
		StringBuilder logger;

		public Dice(int r, int c) {
			logger = new StringBuilder();
			this.r = r;
			this.c = c;
		}

		public void act(int command) {
			boolean isActed = false;
			if (command == 1) isActed = moveEast();
			else if (command == 2) isActed = moveWest();
			else if (command == 3) isActed = moveNorth();
			else isActed = moveSouth();
			if(isActed) {
				stamp();
				logger.append(top).append("\n");
			}
		}

		public boolean moveEast() {
			if(c + 1 >= M) return false;
			else c++;
			int tmp = top;
			top = west;
			west = bottom;
			bottom = east;
			east = tmp;
			return true;
		}

		public boolean moveWest() {
			if(c - 1 < 0) return false;
			else c--;
			int tmp = top;
			top = east;
			east = bottom;
			bottom = west;
			west = tmp;
			return true;
		}

		public boolean moveNorth() {
			if(r - 1 < 0) return false;
			else r--;
			int tmp = top;
			top = south;
			south = bottom;
			bottom = north;
			north = tmp;
			return true;
		}

		public boolean moveSouth() {
			if(r + 1 >= N) return false;
			else r++;
			int tmp = top;
			top = north;
			north = bottom;
			bottom = south;
			south = tmp;
			return true;
		}
		
		public void stamp() {
			if(map[r][c] == 0) {
				map[r][c] = bottom;
			} else {
				bottom = map[r][c];
				map[r][c] = 0;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		init();
		for(int command : commands)
			dice.act(command);
		System.out.println(dice.logger.toString());
	}
	/* 입력 */
	private static void init() throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		dice = new Dice(x, y);
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < K; i++)
			commands[i] = Integer.parseInt(st.nextToken());
	}
}

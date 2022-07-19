package n11559_Puyo_Puyo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	static final int W = 6;
	static final int H = 12;
	static char[][] map = new char[H][];

	static ArrayList<int[]> queue = new ArrayList<>();
	static boolean[][] isVisited = new boolean[H][W];
	static int[] rowDir = {-1, 0, 1, 0};
	static int[] colDir = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i = H-1; i >= 0; i--) {
			map[i] = br.readLine().toCharArray();
		}
		int count = 0;
		while(pop()) {
			gravity();
			count++;
		}
		System.out.println(count);
	}
	
	private static boolean pop() {
		for(boolean[] elem : isVisited)
			Arrays.fill(elem, false);
		
		boolean isPopped = false;
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < W; j++) {
				if(isVisited[i][j] || map[i][j] == '.') continue;
				isPopped |= bfs(i, j); 
			}
		}
		return isPopped;
	}
	private static boolean bfs(int r, int c) {
		queue.clear();
		queue.add(new int[] {r, c});
		isVisited[r][c] = true;
		
		int pointer = 0;
		while(pointer < queue.size()) {
			int[] coord = queue.get(pointer++);
			int cr = coord[0];
			int cc = coord[1];
			for(int dir = 0; dir < 4; dir++) {
				int nr = cr + rowDir[dir];
				int nc = cc + colDir[dir];
				if(nr < 0 || nr >= H || nc < 0 || nc >= W) continue;
				if(isVisited[nr][nc] || map[cr][cc] != map[nr][nc]) continue;
				isVisited[nr][nc] = true;
				queue.add(new int[] {nr, nc});
			}
		}
		if(queue.size() < 4) {
			return false;
		}
		for(int[] elem : queue) {
			map[elem[0]][elem[1]] = '.';
		}
		return true;
	}
	private static void gravity() {
		for(int j = 0; j < W; j++) {
			for(int i = 0; i < H; i++) {
				if(map[i][j] != '.') continue;
				boolean isChanged = false;
				for(int k = i; k < H; k++) {
					if(map[k][j] != '.') {
						map[i][j] = map[k][j];
						map[k][j] = '.';
						isChanged = true;
						break;
					}
				}
				if(!isChanged) break;
			}
			
		}
	}
}

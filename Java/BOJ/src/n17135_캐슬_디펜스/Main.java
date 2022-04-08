package n17135_캐슬_디펜스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
	static Queue<int[]> queue = new LinkedList<int[]>();
	static int N, M, D;
	static int[][] curmap;
	static int[][] map;
	static int[] archer;
	
	public static void main(String[] args) throws IOException {
		// 00. 입력한다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		curmap = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 01. 궁수를 세워준다.
		archer = new int[M];
		archer[M-1] = 1;
		archer[M-2] = 1;
		archer[M-3] = 1;
		
		// 02. 궁수의 위치를 np로 바꾸며 게임을 진행하고, 최대 점수를 갱신한다. 
		int result = 0;
		do {
			for(int i = 0; i < N; i++) curmap[i] = map[i].clone();
			result = Math.max(play(), result);
		} while(np());
		
		// 03. 결과를 출력한다.
		System.out.println(Integer.toString(result));
	}
	
	public static int play() {
		int count = 0;
		for(int row = N; row > 0; row--) {
			queue.clear();
			// Queue에 쏠 적 좌표를 넣어준다.
			for(int i = 0 ; i < M; i++) {
				if(archer[i] == 1) {
					int[] enemy = shoot(row,i);
					if(enemy != null) queue.offer(enemy);
				}
			}
			// Queue에서 빼서 쏴준다. 
			while(!queue.isEmpty()) {
				int er = queue.peek()[0];
				int ec = queue.poll()[1];
				if(curmap[er][ec] == 1) {
					count++;
					curmap[er][ec] = 0;
				}
			}
		}
		return count;
	}
	
	public static int[] shoot(int r, int c) {
		// 조건에 맞게 범위 안의 적을 쏜다.
		for(int i = 0; i < D; i++) {
			int nr = r - 1;
			int nc = c - i;
			for(int j = 0; j <= 2*i; j++) {
				if(nr >= 0 && nr < N && nc >= 0&& nc < M && curmap[nr][nc] == 1) {
					return new int[] {nr,nc};
				}
				nc++;
				if(j < i) nr--;
				else nr++;
			}
		}
		return null;
	}
	
	public static boolean np() {
		int i = M-1;
		while (i > 0 && archer[i-1] >= archer[i]) --i;
		if(i == 0) return false;
		
		int j = M-1;
		while(archer[i-1] >= archer[j]) --j;
		swap(i-1, j);
		
		int k = M-1;
		while(i < k) swap(i++, k--);
		
		return true;
	}
	
	public static void swap(int i,int j) {
		int temp = archer[i];
		archer[i] = archer[j];
		archer[j] = temp;
	}
}
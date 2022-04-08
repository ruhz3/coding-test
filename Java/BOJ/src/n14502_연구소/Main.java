package n14502_연구소;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static List<int[]> list = new ArrayList<>();
	static Queue<int[]> queue = new LinkedList<>();
	static boolean[][] isVisited;
	static int[] rowDir = {-1, 0, 1, 0};  // 상, 우, 하, 좌
	static int[] colDir = {0, 1, 0, -1};
	
	static int[][] map;
	static int N, M;
	
	public static void main(String[] args) throws IOException {
		// 00. 입력한다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		isVisited = new boolean[N][M];
		
		// 01. 입력하며, 안전한 영역의 개수를 세고, 바이러스 위치를 따로 담는다. 
		int safeCount = 0;
		for(int i = 0; i < N; i++ ){
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 0) safeCount++;
				if(map[i][j] == 2) list.add(new int[] {i, j});
			}
		}
		// 02. 0인 타일 중 조합을 만들어 준다.  
		int minInfectCount = Integer.MAX_VALUE;
		for(int i = 0; i < N*M - 2; i++) {
			if(map[i / M][i % M] != 0) continue;
			for(int j = i; j < N*M - 1; j++) {
				if(map[j / M][j % M] != 0) continue;
				for(int k = j; k < N*M; k++) {
					if(map[k / M][k % M] != 0) continue;
					
					// 03. 벽을 세우고 추가 감염영역을 세어준 다음, 최소값을 갱신한다. 
					map[i / M][i % M] = 1;
					map[j / M][j % M] = 1;
					map[k / M][k % M] = 1;
					minInfectCount = Math.min(minInfectCount, spread());
					map[i / M][i % M] = 0;
					map[j / M][j % M] = 0;
					map[k / M][k % M] = 0;
				}
			}
		}
		// 04. 안전한 영역 중 추가 감염영역과 3개의 벽을 빼준다.
		System.out.println(safeCount - minInfectCount - 3);
	}
	
	private static int spread() {
		/* 바이러스 위치를 초기값으로, BFS 탐색하는 함수*/
		int infectCount = 0;
		for(int[] elem : list) queue.offer(elem);
		
		while(!queue.isEmpty()) {
			int[] coord = queue.poll();
			for(int dir = 0; dir < 4; dir++) {
				int nr = coord[0] + rowDir[dir];
				int nc = coord[1] + colDir[dir];
				if(nr < 0 || nr >= N || nc < 0 || nc >= M)  continue;
				if(isVisited[nr][nc] || map[nr][nc] != 0) continue;
				isVisited[nr][nc] = true;
				queue.offer(new int[]{nr, nc});
				infectCount++;
			}
		}
		// 정리하고 가셔야죠~
		for(int i = 0; i < N; i++) Arrays.fill(isVisited[i], false);
		return infectCount;
	}
}

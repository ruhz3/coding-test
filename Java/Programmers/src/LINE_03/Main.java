package LINE_03;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int n = 5;
	static int m = 2;
	static int[][] fires = {{1, 1}};
	static int[][] ices = {{3, 3}};
	public static void main(String[] args) {
		new Solution().solution(3, 2, fires, ices);
	}
}

class Solution {
	Queue<int[]> queue;
	boolean[][] isVisited;
	long[][] map;
	int N, M;
	int[] dr = {-1, 0, 1, 0};
	int[] dc = {0, 1, 0, -1};
	
    public long[][] solution(int n, int m, int[][] fires, int[][] ices) {
        N = n; M = m;
    	map = new long[N][N];
        isVisited = new boolean[N][N];
        queue = new LinkedList<>();
        
        for(int[] fire : fires) {
        	fire(fire[0]-1, fire[1]-1);
        }
        for(int[] ice : ices) {
        	ice(ice[0]-1, ice[1]-1);
        }
        for(int i = 0; i < N; i++) {
        	for(int j = 0; j < N; j++) {
        		System.out.print(map[i][j]);
        	} System.out.println();
        }
        return map;
    }
    private void fire(int r, int c) {
    	map[r][c] += M;
    	for(int k = 1; k <= M; k++) {
    		for(int i = r-k; i <= r+k; i++) {
    			if(i < 0 || i >= N) continue;
    			if(c-k >= 0) map[i][c-k] += M - (k-1);
    			if(c+k < N) map[i][c+k] += M - (k-1);
    		}
    		for(int j = c-k+1; j <= c+k-1; j++) {
    			if(j < 0 || j >= N) continue;
    			if(r-k >= 0) map[r-k][j] += M - (k-1);
    			if(r+k < N) map[r+k][j] += M - (k-1);
    		}
    	}
    }
    private void ice(int r, int c) {
    	queue.clear();
    	for(int i = 0; i < N; i++) {
    		Arrays.fill(isVisited[i], false);
    	}
    	
    	queue.offer(new int[] {r, c});
    	map[r][c] -= M;
    	isVisited[r][c] = true;
    	
    	
    	int k = M;
    	while(!queue.isEmpty() && k > 0) {
    		int len = queue.size();
    		for(int i = 0; i < len; i++) {
    			int[] coord = queue.poll();
    			for(int dir = 0; dir < 4; dir++) {
    				int nr = coord[0] + dr[dir];
    				int nc = coord[1] + dc[dir];
    				if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
    				if(isVisited[nr][nc]) continue;
    				map[nr][nc] -= k;
    				isVisited[nr][nc] = true;
    				queue.offer(new int[] {nr, nc});
    			}
    		}
    		k--;
    	}
    	
    }
}
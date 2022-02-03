package n1954;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 1954_달팽이 숫자
 * 메모리 :	19,408 kb
 * 실행시간 :	102 ms
 * 코드길이 :	3,322
 */

public class Solution {
	static int[] rowDir = {0, 1, 0, -1};
	static int[] colDir = {1, 0, -1, 0};
	
	public static int changeDir(int dir) {
		int newDir = dir + 1;
		if(newDir > 3) {
			newDir = 0;
		}
		return newDir;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder result = new StringBuilder();
		int[][] map = new int[10][10];
		Queue<Integer> queue = new LinkedList<Integer>();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			result.append("#").append(tc).append("\n");
			int N = Integer.parseInt(br.readLine());
			
			// 00. 움직일 방향을 queue에 미리 넣는다.
			// 00-01. 처음에는 N-1번 같은 방향으로 간다.
			int dir = 0;
			for(int i = 0; i < N-1; i++) {
				queue.add(dir%3);
			}
			dir++;
			
			// 00-02. 두 번 방향을 바꿀 때까지 가고, 같은 방향으로 가는 횟수를 하나씩 줄인다.   
			for(int i = N-1; i > 0; i--) {
				for(int j = 0; j < i; j++) {
					queue.add(dir%4);
				}
				dir++;
				for(int j = 0; j < i; j++) {
					queue.add(dir%4);
				}
				dir++;
			}
			
			// 01. 넣어놓은 방향대로 움직인다.
			int num = 1;
			int r = 0, c = 0;
			map[r][c] = num++;
			while(!queue.isEmpty()) {
				int d = queue.poll();
				r += rowDir[d];
				c += colDir[d];
				map[r][c] = num++;
			}
			
			// 02. 출력해보자.
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					result.append(map[i][j]).append(" ");
				}
				result.append("\n");
			}
			
		}
		System.out.println(result.toString());
	}
}
package n9205_맥주_마시면서_걸어가기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
	static Queue<Integer> queue = new LinkedList<Integer>();
	static int[][] list = new int[102][102];
	static int[][] map = new int[102][102];
	static boolean[] isVisited = new boolean[102];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder result = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			int C = Integer.parseInt(br.readLine());			

			// 00. 사용할 자료형을 초기화한다.
			queue.clear();
			Arrays.fill(isVisited, false);
			for(int i = 0; i < C+2; i++) {
				Arrays.fill(list[i], 0);
				Arrays.fill(map[i], 0);
			}
			
			// 01. list를 저장 후, 인접 리스트를 초기화한다.
			for(int i = 0; i < C+2; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				list[i][0] = Integer.parseInt(st.nextToken());
				list[i][1] = Integer.parseInt(st.nextToken());
			}
			for(int i = 0; i < C+2 - 1; i++) {
				for(int j = i+1; j < C+2; j++) {
					if(map[i][j] != 0) continue;
					int distance = Math.abs(list[i][0] - list[j][0]) + Math.abs(list[i][1] - list[j][1]);
					if(distance <= 50 * 20) {
						map[i][j] = distance;
						map[j][i] = distance;
					}
				}
			}
			
			// 02. BFS 탐색한다.
			isVisited[0] = true;
			queue.add(0);
			boolean isArrived = false;
		OUTER : while(!queue.isEmpty()) {
				int n = queue.poll();
				for(int i = 0; i < C+2; i++) {
					if(isVisited[i] || map[n][i] == 0) continue;
					if(i == C+1) {
						isArrived = true;
						break OUTER;
					}
					isVisited[i] = true;
					queue.add(i);
				}
			}
			
			// 03. 출력한다.
			if(isArrived) result.append("happy").append("\n");
			else result.append("sad").append("\n");
		}
		System.out.println(result.toString());
	}
}

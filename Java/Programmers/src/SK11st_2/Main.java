package SK11st_2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static boolean[][] isConnected;
	static boolean[] isVisited;
	static Queue<Integer> queue = new LinkedList<>();
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		// 00. 입력 받고 자료형을 초기화한다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
 		M = Integer.parseInt(br.readLine());
 		
 		isVisited = new boolean[N+1];
 		isConnected = new boolean[N+1][N+1];
 		
 		for(int i = 0; i < M; i++) {
 			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
 			int c1 = Integer.parseInt(st.nextToken());
 			int c2 = Integer.parseInt(st.nextToken());
 			// 컴퓨터의 연결 관계는 뒤집어서도 기록해 준다. 
 			isConnected[c1][c2] = true;
 			isConnected[c2][c1] = true;
 		}
 		
 		// 02. BFS 탐색한다.
 		int count = 0;
 		queue.offer(1);
 		isVisited[1] = true;
 		
 		while(!queue.isEmpty()) {
 			// 02-00. 탐색 할 컴퓨터를 큐에서 꺼내온다.
 			int pc = queue.poll();
 			
 			// 02-01. 현재 탐색 중인 컴퓨터와 "연결되어 있으면서" "탐색한 적 없는" 컴퓨터는 Queue에 담는다.
 			// 02-02. Queue에 담을 때는 방문 여부를 체크하고, 감염된 컴퓨터 count를 추가해준다.
 			for(int i = 1; i <= N; i++) {
 				if(isConnected[pc][i] && !isVisited[i]) {
 					isVisited[i] = true;
 					queue.offer(i);
 					count++;
 				}
 			}
 		}
 		
 		// 03. 감염
 		System.out.println(count);
	}
}


package n1043_거짓말;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static Queue<Integer> queue = new LinkedList<Integer>();
	static boolean[] isVisited;
	
	static boolean[] isEnlightened;
	static boolean[][] relations;
	static int[][] parties;
	static int N, M;
			
	public static void main(String[] args) throws IOException {
		// 00. 입력한다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		isEnlightened = new boolean[N+1];
		isVisited = new boolean[N+1];
		relations = new boolean[N+1][N+1];
		parties = new int[M][];
		st = new StringTokenizer(br.readLine(), " ");
		int K = Integer.parseInt(st.nextToken());
		for(int i = 0; i < K; i++) {
			int t = Integer.parseInt(st.nextToken());			
			isEnlightened[t] = true;
			isVisited[t] = true;
			queue.add(t);
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int num = Integer.parseInt(st.nextToken());
			parties[i] = new int[num];
			for(int j = 0; j < num; j++) {
				parties[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 01. 파티를 쭉 보며, 관계도를 만든다.
		for(int i = 0; i < M; i++) {
			int[] party = parties[i];
			for(int a = 0; a < party.length-1; a++) {
				for(int b = a+1; b < party.length; b++) {
					relations[party[a]][party[b]] = true;
					relations[party[b]][party[a]] = true;
				}
			}
		}
		// 02. BFS로 관계도를 마저 완성한다.
		while(!queue.isEmpty()) {
			int t = queue.poll();
			boolean[] rel= relations[t];
			for(int i = 1; i <= N; i++) {
				if(rel[i] && !isEnlightened[i] && !isVisited[i]) {
					isEnlightened[i] = true;
					isVisited[i] = true;
					queue.offer(i);
				}
			}
		}
		// 03. 거짓말 쳐도 되는 파티를 센다.
		int count = 0;
		for(int i = 0; i < M; i++) {
			int[] party = parties[i];
			boolean noSpy = true;
			for(int person : party) {
				if(isEnlightened[person]) {
					noSpy = false;
					break;
				}
			}
			if(noSpy) count++;
		}
		// 04. 출력한다.
		System.out.println(count);
	}
}

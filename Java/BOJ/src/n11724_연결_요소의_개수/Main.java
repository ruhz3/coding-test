package n11724_연결_요소의_개수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static boolean[][] map;
	static boolean[] check;
	static int N, M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new boolean[N+1][N+1];
		check = new boolean[N+1];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			map[a][b] = true;
			map[b][a] = true;
		}
		
		int count = 0;
		for(int i = 1; i <= N; i++) {
			if(!check[i]) {
				dfs(i);
				count++;
			}
		}
		System.out.println(count);
	}
	
	private static void dfs(int num) {
		check[num] = true;
		for(int i = 1; i <= N; i++) {
			if(map[num][i] && !check[i]) dfs(i);
		}
	}
}

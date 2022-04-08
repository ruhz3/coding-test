package n1263_사람_네트워크2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder answer = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int[][] adj = new int[N][N];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					adj[i][j] = Integer.parseInt(st.nextToken());
					if(i != j && adj[i][j] == 0) adj[i][j] = 1000 * 1000 - 1;
				}
			}
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(i == j) continue;
					for(int k = 0; k < N; k++) {
						if(j == k || k == i) continue;
						if(adj[j][k] > adj[j][i] + adj[i][k]) adj[j][k] = adj[j][i] + adj[i][k];
					}
				}
			}
			int minSum = Integer.MAX_VALUE;
			for(int i = 0; i < N; i++) {
				int sum = 0; 
				for(int j = 0; j < N; j++) {
					sum += adj[i][j];
				}
				minSum = Math.min(minSum, sum);
			}
			answer.append("#").append(tc).append(" ").append(minSum).append("\n");
		}
		System.out.println(answer.toString());
	}
}

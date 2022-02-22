package n18233;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] range;
	static int[] plan; 
	static int N, P, E;
	static boolean isDone;
	static StringBuilder result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		range = new int[N][2];
		plan= new int[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			range[i][0] = Integer.parseInt(st.nextToken());
			range[i][1] = Integer.parseInt(st.nextToken());
		}
		giveDuck(-1, 0, 0);
		System.out.println(isDone ? result.toString() : -1);
	}
	
	private static void giveDuck(int idx, int sum, int cnt) {
		if(cnt == P && sum == E && !isDone) {
			isDone = true;
			result = new StringBuilder();
			for(int i = 0; i < N; i++)
				result.append(plan[i]).append(" ");
			return;
		}
		if(cnt > P || idx >= N-1 || isDone) {
			return;
		}
		
		for(int i = idx + 1; i < N; i++) {
			for(int j = range[i][0]; j <= range[i][1]; j++) {
				plan[i] = j;
				giveDuck(i, sum+j, cnt+1);
				if(isDone) return;
				plan[i] = 0;
			}
		}
	}
}

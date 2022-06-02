package n17404_RGB거리_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static final int BIG = Integer.MAX_VALUE;
	static int N;
	static int[][] cost;
	static int[] street;
	static int[][][] cache;
	static int start;
	
	public static void main(String[] args) throws IOException {
		// 00. 입력한다. (R, G, B = 0, 1, 2)
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		cost = new int[N][3];
		street = new int[N];
		cache = new int[3][N][3];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			cost[i][0] = Integer.parseInt(st.nextToken());
			cost[i][1] = Integer.parseInt(st.nextToken());
			cost[i][2] = Integer.parseInt(st.nextToken());
		}
		int minCost = BIG;
		for(int color = 0; color < 3; color++) {
			start = color;
			minCost = Math.min(minCost, paint(0, color));			
		}
		System.out.println(minCost);
	}
	private static int paint(int index, int color) {
		if(index == N-1) {
			if(street[0] == color) return BIG;
			else return cost[index][color];
		}
		int ret = cache[start][index][color];
		if(ret != 0) return ret;
		
		street[index] = color;
		int result = BIG;
		for(int c = 0; c < 3; c++) {
			if(c == color) continue;
			result = Math.min(result, paint(index+1, c));
		}
		result += cost[index][color];
		cache[start][index][color] = result;
		return result;
	}
}

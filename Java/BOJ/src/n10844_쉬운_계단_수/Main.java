package n10844_쉬운_계단_수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static long[][] cache;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		cache = new long[10][N];
		long sum = 0;
		for(int i = 1; i <= 9; i++) {
			sum += dfs(i, 1);
			sum %= 1000000000;
		}
		System.out.println(sum);
	}
	private static long dfs(int num, int count) {
		if(count == N) return 1;
		if(cache[num][count] != 0) return cache[num][count];
		
		long ret = 0;
		if(num - 1 >= 0) ret += dfs(num-1, count+1);
		if(num + 1 <= 9) ret += dfs(num+1, count+1);
		cache[num][count] = ret % 1000000000;
		return ret % 1000000000;
	}
}

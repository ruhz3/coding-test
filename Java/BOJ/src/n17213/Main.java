package n17213;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[][] cache;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		cache = new int[M][M];
		System.out.println(combination(M-1, N-1));
	}
	
	public static int combination(int n, int r) {
		if(n == r || r == 0)
			return 1;
		if(cache[n][r] != 0) return cache[n][r];
		cache[n][r] = combination(n-1, r) + combination(n-1, r-1);
		return cache[n][r];
	}
}

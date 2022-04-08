package n2133_타일_채우기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[] dp;
	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[N+3];
		if(N % 2 == 0)
		
		// 00. 점화식 초기값
		dp[0] = 1;
		dp[2] = 3;
		
		// 01. A(n) = A(n-2) * 3 + S(i:0~n-4)[A(i) * 2]
		for(int i = 4; i <= N; i += 2) {
			dp[i] = dp[i - 2] * 3;
			for(int j = 4; j <= i; j += 2) {
				dp[i] += dp[i - j] * 2;
			}
		}
		System.out.println(dp[N]);
	}
}

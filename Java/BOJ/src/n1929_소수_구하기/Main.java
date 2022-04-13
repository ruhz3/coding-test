package n1929_소수_구하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static boolean[] isNotPrime;
	static int M, N;
	
	public static void main(String[] args) throws IOException {
		// 00. 입력한다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		isNotPrime = new boolean[N + 1];		
		
		// 01. 에라토스테네스의 체를 사용하되, sqrt(N)까지만 시도한다.
		isNotPrime[1] = true;
		for(int i = 2, limit = (int)Math.sqrt(N); i <= limit; i++) {
			if(isNotPrime[i]) continue;
			for(int j = 2*i; j <= N; j += i) {
				isNotPrime[j] = true;
			}
		}
		
		// 02. M부터 N사이의 소수를 출력한다. 
		for(int i = M; i <= N; i++) {
			if(!isNotPrime[i]) sb.append(i).append("\n");
		}
		System.out.println(sb.toString());
	}
}

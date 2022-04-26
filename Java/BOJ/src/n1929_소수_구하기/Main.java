package n1929_소수_구하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static byte[] isPrime;
	static int M, N;
	
	public static void main(String[] args) throws IOException {
		// 00. 입력한다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		// 01. boolean 배열 대신 비트마스킹을 사용해보자.
		isPrime = new byte[N/8 + 1];		
		Arrays.fill(isPrime, (byte)255);
		isPrime[0] -= 3;
		
		// 01. 에라토스테네스의 체를 사용하되, sqrt(N)까지만 시도한다.
		for(int i = 2, limit = (int)Math.sqrt(N); i <= limit; i++) {
			if((isPrime[i>>>3] & (1 << (i&7))) == 0) continue; // 소수가 아니라면
			for(int j = 2*i; j <= N; j += i) {
				isPrime[j>>>3] &= ~(1 << (j&7));
			}	
		}
		// 02. M과 N사이의 소수들을 출력한다.
		for(int i = M; i <= N; i++) {
			if((isPrime[i>>>3] & (1 << (i&7))) != 0) {
				sb.append(i).append("\n");				
			}
		}
		System.out.println(sb.toString());
	}
}

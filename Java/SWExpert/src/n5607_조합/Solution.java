package n5607_조합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static final long p = 1234567891;
	static long N, R;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			
			long a = 1;
			long b = 1;
			long r = Math.min(R, N-R);
			
			for(int i = 0; i < r; i++) {
				a = a * (N-i)%p;
				b = b * (r-i)%p;
			}
			long answer = (a%p * div(b, p-2)%p) %p;
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static long div(long a, long b) {
		if(b == 1) return a;
		
		long tmp = div(a, b/2);
		if(b%2 == 1) return tmp * tmp%p * a%p;
		else return tmp * tmp%p;
	}
}
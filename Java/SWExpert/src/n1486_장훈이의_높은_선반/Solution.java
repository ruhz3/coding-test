package n1486_장훈이의_높은_선반;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N, B;
	static int[] clerks;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			// 00. 입력한다.
			sb.append("#").append(tc).append(" ");
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			clerks = new int[N];
			st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < N; i++) {
				clerks[i] = Integer.parseInt(st.nextToken());
			}
			
			// 01. 0부터 2^N-1의 수의 이진수 표현을 사용하여 부분집합을 따져본다. 
			int minHeight = Integer.MAX_VALUE;
			for(int bit = 0; bit < (1<<N); bit++) {
				int height = 0;
				for(int i = 0; i < N; i++) {
					if((bit & (1 << i)) != 0) height += clerks[i];
					if(height > minHeight) break;
				}
				if(height < B) continue;
				minHeight = height;
			}
			sb.append(minHeight - B).append("\n");
		}
		System.out.println(sb.toString());
	}
}

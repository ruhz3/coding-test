package n2491_수열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int ascendCnt = 1;
		int maxAscendCnt = 1;
		int descendCnt = 1;
		int maxDescendCnt = 1;
		int prev = Integer.parseInt(st.nextToken());
		
		// 00. 순서를 세다가 끝이 나면, max 값을 업데이트한다. 
		for(int i = 0; i < N-1; i++) {
			int in = Integer.parseInt(st.nextToken());
			// 00-01. 이전 원소보다 작다.
			if(in < prev) {
				descendCnt++;
				maxAscendCnt = Math.max(maxAscendCnt, ascendCnt);
				ascendCnt = 1;
			// 00-02. 이전 원소보다 크다.
			} else if(in > prev) {
				ascendCnt++;
				maxDescendCnt = Math.max(maxDescendCnt, descendCnt);
				descendCnt = 1;
			// 00-03. 이전 원소와 같다.
			} else {
				descendCnt++;
				ascendCnt++;
			}
			prev = in;
		}
		
		//  01. 마지막 비교는 맨 마지막에 비교를 하지 못하고 끝났기 때문이다.  
		maxAscendCnt = Math.max(ascendCnt, maxAscendCnt);
		maxDescendCnt = Math.max(descendCnt, maxDescendCnt);
		System.out.println(Math.max(maxAscendCnt, maxDescendCnt));
	}
}

package n1052_물병;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int cnt = 0;
		while(true) {
			String strN = Integer.toBinaryString(N);
			int len = strN.length();
			
			// 00. 현재 물병의 개수를 세준다.
			int curCnt = 0;
			for(int i = 0; i < len; i++) {
				if(strN.charAt(i) == '1') curCnt++;
			}
			if(curCnt <= K) break;
			
			// 01. 물병을 추매한다.
			for(int i = len-1; i >= 0; i--) {
				if(strN.charAt(i) == '1') {
					N += 1 << (len-1 - i);
					cnt += 1 << (len-1 - i);
					break;
				}
			}
		}
		System.out.println(cnt);
	}
}

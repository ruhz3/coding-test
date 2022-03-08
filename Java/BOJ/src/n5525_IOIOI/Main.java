package n5525_IOIOI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static char[] P, S;
	static int[] failLink;
	static int N, M;	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		S = br.readLine().toCharArray();
		int len = 2*N+1;
		
		P = new char[len];
		failLink = new int[len];
		for(int i = 0; i < len; i+=2) P[i] = 'I';
		for(int i = 1; i < len; i+=2) P[i] = 'O';
		
		// 00. failLink를 초기화한다.
		int j = 0;
		for(int i = 1; i < len; i++) {
			while(j > 0 && P[i] != P[j]) j = failLink[j-1];
			if(P[i] == P[j]) failLink[i] = ++j;
		}
		
		// 01. KMP를 실행한다.
		int count = 0;
		int k = 0;
		for(int i = 1; i < M; i++) {
			while(k > 0 && S[i] != P[k]) {
				k = failLink[k-1];
			}
			if(S[i] == P[k]) {
				if(k == len-1) {
					k = failLink[k];
					count++;
				}
				else k++;
			}
		}
		System.out.println(count);		
	}
}

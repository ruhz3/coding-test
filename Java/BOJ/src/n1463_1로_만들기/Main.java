package n1463_1로_만들기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static int[] cache;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		cache = new int[N+1];
		System.out.println(Xto1(N)-1);
	}
	private static int Xto1(int X) {
		if(X == 1) return 1;
		
		if(cache[X] != 0) return cache[X];
		int res = Integer.MAX_VALUE;
		if(X % 3 == 0) res = Math.min(res, Xto1(X/3));
		if(X % 2 == 0) res = Math.min(res, Xto1(X/2));
		res = Math.min(res, Xto1(X-1));
		cache[X] = res + 1;
		
		return cache[X];
	}
}

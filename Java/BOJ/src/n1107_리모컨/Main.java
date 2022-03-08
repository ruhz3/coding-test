package n1107_리모컨;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static String target;
	static int num;
	static int N;
	static boolean[] isBroken = new boolean[10];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		target = br.readLine();
		num = Integer.parseInt(target);
		N = target.length();
		int M = Integer.parseInt(br.readLine());
		if(M > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < M; i++) {
				isBroken[Integer.parseInt(st.nextToken())] = true;
			}
		}
		if(num == 100) {
			System.out.println(0);
		} else {
			int result = Math.abs(num - 100);
			for(int i = 0; i <= 1000000; i++) {
				if(check(i)) {
					result = Math.min(result, Math.abs(num - i) + Integer.toString(i).length());
				}
			}
			System.out.println(result);
		}
	}
	private static boolean check(int n) {
		if(n == 0) return !isBroken[0]; 
		int k = n;
		while(k > 0) {
			if(isBroken[k%10]) return false;
			k /= 10;
		}
		return true;
	}
}

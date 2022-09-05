package n24416_알고리즘_수업_피보나치_수_1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int count = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		fib(n);
		System.out.println(count + " " +(n-2));
	}
	private static void fib(int n) {
		if(n == 1 || n == 2) {
			count++;
		} else {
			fib(n-1);
			fib(n-2);
		}
	}
}

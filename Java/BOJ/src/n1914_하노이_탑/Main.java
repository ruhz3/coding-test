package n1914_하노이_탑;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
	static StringBuilder result = new StringBuilder();
	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		if(N <= 20) {
			System.out.println((2 << (N-1)) - 1);
			move(N, 1, 2, 3);
			System.out.println(result.toString());
		}else {
			BigInteger res = new BigInteger("1");
			for(int i = 0; i < N; i++) res = res.multiply(new BigInteger("2"));
			res = res.subtract(new BigInteger("1"));
			System.out.println(res);
		}
		
	}
	
	private static void move(int bottomNum, int from, int tmp, int to) {
		if(bottomNum == 1) {
			result.append(from).append(" ").append(to).append("\n");
		} else {
			move(bottomNum-1, from, to, tmp);
			result.append(from).append(" ").append(to).append("\n");
			move(bottomNum-1, tmp, from, to);
		}
	}
}

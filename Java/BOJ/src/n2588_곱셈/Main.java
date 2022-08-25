package n2588_곱셈;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int A = Integer.parseInt(br.readLine());
		int B = Integer.parseInt(br.readLine());
		int sum = 0;
		int n = 1;
		while(B > 0) {
			int b = B % 10;
			sb.append(A*b).append('\n');
			sum = sum + A*b*n;
			B /= 10;
			n *= 10;
		}
		sb.append(sum);
		
		System.out.println(sb.toString());
	}
}

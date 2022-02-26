package n1720_타일_코드;

/*!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.out.println((long)factorial(30));
		// 00. 입력을 받는다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		// 01. 타일의 조합 가지 수 마다, 코드의 개수를 세어준다. 
		long codeNum = 0;
		for(int a = 0; a <= N/2; a++) {
			for(int b = 0; b <= N-a; b+=2) {
				int c = N - a * 2 - b;
				if(c < 0) continue;
				// 02. b타입 타일은 가로 두줄이 한 쌍이며, count는 대칭을 제외한 '같은 것을 포함하는 순열'을 계산한다. 
				codeNum += count(a, b/2, c);
			}
		}
		System.out.println(codeNum);
	}
	 
	public static long count(int a, int b, int c) {
		// n! / p! / q! / r!
		// 00. 같은 것을 포함한 순열 값을 계산한다 .
		int sum = a + b + c;
		long result = factorial(sum) / factorial(a) / factorial(b) / factorial(c);
		// 01. 이미 대칭인 경우들을 한 번 더해준다.
		if (sum % 2 == 0) {
			if(a % 2 == 0 && b % 2 == 0 && c % 2 == 0) {
				result += factorial(sum/2) / factorial(a/2) / factorial(b/2) / factorial(c/2); 
			}
		} else {
			if((a % 2 != 0) && (b % 2 == 0) && (c % 2 == 0)) {
				result += factorial(sum/2) / factorial((a-1)/2) / factorial(b/2) / factorial(c/2); 
			}
			else if((b % 2 != 0) && (c % 2 == 0) && (a % 2 == 0)) {
				result += factorial(sum/2) / factorial(a/2) / factorial((b-1)/2) / factorial(c/2);
			}
			else if((c % 2 != 0) && (a % 2 == 0) && (b % 2 == 0)) {
				result += factorial(sum/2) / factorial(a/2) / factorial(b/2) / factorial((c-1)/2);
			}
		}
		// 02. 경우의 수를 반으로 나눠 반환한다.
		return (result+1)/2;
	}
	
	static long[] cache = new long[31];
	public static long factorial(int n) {
		if(cache[n] != 0) {
			return cache[n];
		}
		if(n <= 0) {
			return 1;
		}
		cache[n] = (long)n * factorial(n-1);
		return cache[n];
	}
}

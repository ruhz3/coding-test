package n16953_A_to_B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		// 00. 입력한다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		// 01. ÷2 연산이나, 1을 떼는 연산은 특정상황에만 가능하므로 Greedy로 풀 수 있다.
		int count = 0;
		boolean isImpossible = false;
		while(B != A) {
			if(B < A || (B%10 != 1 && B%2 != 0)) {
				isImpossible = true;
				break;
			}
			if(B%10 == 1) B /= 10;
			else B /= 2;
			count++;
		}
		
		// 02. 답이 없다면, -1을 출력한다.
		if(isImpossible) System.out.println(-1);
		else System.out.println(count+1);
	}
}

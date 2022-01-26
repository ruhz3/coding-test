package n1978;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static boolean check(int num) {
		// 1은 소수가 아니다!
		if(num == 1) {
			return false;
		}
		// 소수는 1과 자기 자신 외에는 어떤 수도 약수로 갖지 않는다.
		for(int i = 2; i < num; i++) {
			if(num % i == 0) {
				return false;
			}
		}
		return true;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 00. 입력을 받는다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int num, count = 0;
		for(int i = 0; i < N; i++) {
			num = Integer.parseInt(st.nextToken());
			// 01. 소수라면 count한다. 
			if(check(num)) {
				count++;
			}
		}
		// 02. count를 출력한다. 
		System.out.println(count);
	}
}

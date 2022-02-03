package n10989;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[] numCount = new int[10001];
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder result = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		// 00. 10000이 제한이므로, 해당 정수의 개수를 배열로 센다.
		for(int i = 0; i < N; i++) {
			numCount[Integer.parseInt(br.readLine())]++;
		}
		
		// 01. 앞에서 부터 개수만큼 출력한다.
		for(int i = 0; i < 10001; i++) {
			if(numCount[i] > 0) {
				for(int j = 0; j < numCount[i]; j++) {
					result.append(i).append("\n");
				}
			}
		}
		System.out.print(result.toString());
	}
}

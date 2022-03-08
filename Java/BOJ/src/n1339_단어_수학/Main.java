package n1339_단어_수학;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int[] alphabet = new int[26];
	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		// 00. 알파벳 별로 자릿값을 누적한다.
		for (int i = 0; i < N; i++) {
			String word = br.readLine();
			int len = word.length();
			int val = (int) Math.pow(10, len - 1);
			for (int j = 0; j < len; j++) {
				alphabet[word.charAt(j) - 'A'] += val;
				val /= 10;
			}
		}
		// 01. 알파벳의 자릿값 순서대로 정렬한다.
		Arrays.sort(alphabet);
		
		// 02. 자릿값이 높은 알파벳에 높은 숫자를 부여한다.
		int sum = 0;
		int num = 9;
		for (int i = alphabet.length - 1; i > -1; i--) {
			if (num == 0) break;
			sum += alphabet[i] * num;
			num--;
		}
		// 03. 출력한다.
		System.out.println(sum);
	}
}

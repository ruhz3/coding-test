package n17413_단어_뒤집기_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static char[] input;
	static int[] indexes;
	public static void main(String[] args) throws IOException {
		// 00. 입력한다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] input = br.readLine().toCharArray();
		int N = input.length;
		indexes = new int[N];
		
		// 01. 태그 쓰는중, 단어 쓰는중, 아무것도 아닌 상태로 나눈다.
		boolean isTagging = false;
		boolean isWording = false;
		int point = 0;
		
		// 02. 문자열을 순회하며 따져본다.
		for(int i = 0; i < N; i++) {
			// - 태그 쓰는 중이라면, '>'가 들어오는지만 확인한다.
			if(isTagging) {
				isTagging = input[i] != '>';
				continue;
			}
			// - 태그가 안 열려있고, '<' or ' '을 만난다면, 
			if(input[i] == '<' || input[i] == ' ') {
				if(isWording) {
					indexes[point] = i-1;					
					isWording = false;
				}
				isTagging = input[i] == '<';
				continue;
			}
			// - 태그가 안 열려있고, 단어를 안 쓰는 중이라면, 단어를 시작한다.
			if(!isWording) { 
				point = i;
				isWording = true;
			}
		}
		// 03. 마지막 원소를 따로 처리해준다.
		if(isWording) indexes[point] = N-1;
		
		// 04. 인덱스가 적혀있다면, 해당 부분부터 거꾸로 출력해준다.
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++) {
			if(indexes[i] <= 0) {
				sb.append(input[i]);
				continue;
			}
			for(int j = indexes[i]; j >= i; j--) {
				sb.append(input[j]);
			}
			i = indexes[i];
		}
		System.out.println(sb.toString());
	}
}

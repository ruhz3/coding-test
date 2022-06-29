package n14906_스러피;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static char[] text;
	
	public static void main(String[] args) throws IOException {
		// 00. 입력한다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		// 01. solution 함수를 호출하고, 출력형식에 맞게 구성한다.
		StringBuilder sb = new StringBuilder();
		sb.append("SLURPYS OUTPUT\n");
		for(int i = 0; i < N; i++) {
			text = br.readLine().toCharArray();
			// * 예외 발생 시 조건에 맞지 않는 경우이므로, try/catch 한다.
			boolean result = false;
			try {
				result = solution();
			} catch(Exception e) {
				result = false;
			}
			if(result) sb.append("YES\n");
			else sb.append("NO\n");
		}
		sb.append("END OF OUTPUT");
		// 02. 출력한다.
		System.out.println(sb.toString());
	}
	
	private static boolean solution() {
		int len = text.length;
		if(text[0] != 'A') return false;
		// 스림프와 스럼프의 구분 인덱스를 찾는다.
		int idx = 0;
		if(text[1] == 'H') idx = 1;
		else {
			for(int i = len-1; i > 3; i--)
				if(text[i] == 'C') {
					idx = i;
					break;
				}
		}
		// 문자열이 스림프 + 스럼프라면 true를 반환한다. 
		if(!isSlimp(0, idx)) return false;
		if(!isSlump(idx+1, len-1)) return false;
		return true;
	}
	/* 스림프인지 여부를 반환하는 함수*/
	private static boolean isSlimp(int start, int end) {
		if(text[start] != 'A') return false;
		if(end-start+1 == 2) return text[end] == 'H';
		if(text[start+1] == 'B') {
			if(!isSlimp(start+2, end-1)) return false;
		} else {
			if(!isSlump(start+1, end-1)) return false;
		}
		return true;
	}
	/* 스럼프인지 여부를 반환하는 함수*/
	private static boolean isSlump(int start, int end) {
		if(text[start] != 'D' && text[start] != 'E') return false;
		for(int i = start + 1; i < end; i++) {
			if(text[i] != 'F') {
				if(isSlump(i, end)) return true;
				else return false;
			}
		}
		return text[end] == 'G';
	}
}
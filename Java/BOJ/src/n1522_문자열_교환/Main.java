package n1522_문자열_교환;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		int len = s.length();
		
		int aCnt = 0;
		int bCnt = 0;
		// 00. A의 개수를 세어준다.
		for (int i = 0; i < len; i++) {
			if (s.charAt(i) == 'a') aCnt++;
		}
		// 01. 범위 안에 B가 포함된 개수를 세어준다.
		for (int i = 0; i < aCnt; i++) {
			if (s.charAt(i) == 'b') bCnt++;
		}
		
		// 02. 슬라이딩~
		int result = bCnt;
		for (int i = 1; i < len; i++) {
			if (s.charAt(i - 1) == 'b') bCnt--;
			int r = i + aCnt - 1;
			if (r >= len) r -= len;
			if (s.charAt(r) == 'b') bCnt++;
			result = result > bCnt ? bCnt : result;
		}
		
		System.out.println(result);
	}
}

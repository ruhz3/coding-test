package n1759;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static int L;
	static int C;
	static ArrayList<Character> list;
	
	public static void main(String[] args) throws IOException {
		StringBuilder result = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 00. 입력한다.
		st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		list = new ArrayList<Character>();
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < C; i++) {
			list.add(st.nextToken().charAt(0));
		}
		
		// 01. 사전 순으로 입력 문자를 정렬한다.
		Collections.sort(list);
		
		// 02. 재귀로 출력한다.
		printKey(-1, false, 0, "");
	}
	
	public static void printKey(int idx, boolean hasVowel, int consonantCount, String nowKey) {
		// 00. 목표 문자열 수, 최소 모음/자음 수를 만족했다면 출력한다.
		String key = nowKey;
		if((key.length() == L)) {
			if(hasVowel && consonantCount >= 2) System.out.println(nowKey);
			return;
		}
		// 01. 재귀로 다음 문자를 포함해 호출한다.
		for(int i = idx+1; i < C; i++) {
			char word = list.get(i);
			boolean isVowel = (word == 'a' || word == 'e' || word == 'i' || word == 'o' || word == 'u');
			printKey(i, hasVowel || isVowel,
					isVowel ? consonantCount : consonantCount+1,
					nowKey+list.get(i));
		}
	}
}

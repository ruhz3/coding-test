package n1755_숫자_놀이;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Number implements Comparable<Number>{
	String key;
	int num;
	
	public Number(String key, int num) {
		super();
		this.key = key;
		this.num = num;
	}
	@Override
	public int compareTo(Number o) {
		return key.compareTo(o.key);
	}
}

public class Main {
	static String[] keys = {"ze", "on", "tw", "th", "fo", "fi", "si", "se", "ei", "ni"};
	static List<Number> list = new ArrayList<>();
	static int M, N;
	
	public static void main(String[] args) throws IOException {
		// 00. 입력한다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		// 02. 정수와 함께 4자리 key를 저장한다.
		for(int num = M; num <= N; num++) {
			String key = "";
			if(num/10 > 0) key += keys[num/10];
			key += keys[num%10];
			list.add(new Number(key, num));
		}
		
		// 03. 정렬된 상태에서 정수를 담는다.
		Collections.sort(list);
		for(int i = 0, len = list.size(); i < len; i++) {
			sb.append(list.get(i).num).append(" ");
			if(i % 10 == 9) sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
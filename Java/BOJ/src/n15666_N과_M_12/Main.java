package n15666_N과_M_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<Integer> list;
	static HashSet<Integer> set = new HashSet<Integer>(); 
	static int N, M;
	static StringBuilder result = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 00. set로 입력 받아, list로 변환한다(중복 제거).
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			set.add(Integer.parseInt(st.nextToken()));
		}
		list = new ArrayList<Integer>(set);
		Collections.sort(list);
		
		// 01. 재귀로 찾아 출력한다.
		print("", 0, 0);
		System.out.println(result.toString());
	}
	
	private static void print(String ret, int idx, int cnt) {
		if(cnt == M) {
			result.append(ret).append("\n");
			return;
		}
		// i를 전달받은 idx와 크거나 같은 범위에서 순회한다.
		for(int i = idx, len = list.size(); i < len; i++) {
			print(ret + list.get(i) + " ", i, cnt+1);
		}
		return;
	}
}

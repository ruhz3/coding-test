package n14425_문자열_집합;
// 39012KB, 328ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static Set<String> set = new HashSet<>();
	static int N, M;
 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < N; i++) {
			set.add(br.readLine());
		}
		int count = 0;
		for(int i = 0; i < M; i++) {
			if(set.contains(br.readLine())) count++; 
		}
		System.out.println(count);
		
	}
}

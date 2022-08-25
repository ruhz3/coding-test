package n10815_숫자_카드;
// 172860KB, 876ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static Set<Integer> set = new HashSet<>();
	static int N, M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			set.add(Integer.parseInt(st.nextToken()));			
		}
		
		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < M; i++) {
			int key = Integer.parseInt(st.nextToken());
			sb.append(set.contains(key) ? 1 : 0).append(" ");			
		}
		System.out.println(sb.toString());
	}
}

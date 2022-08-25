package n1764_듣보잡;
// 25768KB, 264ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static Set<String> set = new HashSet<>();
	static List<String> list = new ArrayList<>();
	static int N, M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		for(int i = 0; i < N; i++) {
			set.add(br.readLine());
		}
		
		for(int j = 0; j < M; j++) {
			String input = br.readLine();
			if(set.contains(input)) list.add(input);
		}
		
		Collections.sort(list);
		sb.append(list.size()).append('\n');
		for(String elem : list) {
			sb.append(elem).append('\n');
		}
		System.out.println(sb.toString());
	}
}

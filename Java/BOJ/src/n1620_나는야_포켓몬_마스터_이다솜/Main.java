package n1620_나는야_포켓몬_마스터_이다솜;
// 89564KB, 636ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static Map<String, Integer> map = new HashMap<>();
	static String[] arr;
	static int N, M;
 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new String[N+1];
		for(int i = 1; i <= N; i++) {
			String input = br.readLine(); 
			map.put(input, i);
			arr[i] = input;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < M; i++) {
			String key = br.readLine();
			try {
				int index = Integer.parseInt(key);
				sb.append(arr[index]).append('\n');
			} catch (Exception e) {
				sb.append(map.get(key)).append('\n');
			}
		}
		System.out.println(sb.toString());
	}
}
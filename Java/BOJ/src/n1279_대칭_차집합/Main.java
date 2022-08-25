package n1279_대칭_차집합;
// 77216KB, 532ms

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
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			set.add(st.nextToken());
		}
		
		int count = 0;
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < M; i++) {
			if(set.contains(st.nextToken())) count++;
		}
		System.out.println(N + M - count * 2);
	}
}
/* Set<Integer>로 풀이*/
// 85356KB, 588ms

//public class Main {
//	static Set<Integer> set = new HashSet<>();
//	static int N, M;
//	
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st;
//		
//		st = new StringTokenizer(br.readLine(), " ");
//		N = Integer.parseInt(st.nextToken());
//		M = Integer.parseInt(st.nextToken());
//		
//		st = new StringTokenizer(br.readLine(), " ");
//		for(int i = 0; i < N; i++) {
//			set.add(Integer.parseInt(st.nextToken()));
//		}
//		
//		int count = 0;
//		st = new StringTokenizer(br.readLine(), " ");
//		for(int i = 0; i < M; i++) {
//			if(set.contains(Integer.parseInt(st.nextToken()))) count++;
//		}
//		System.out.println(N + M - count * 2);
//	}
//}

/* Array로 풀이*/
// 162080KB, 508ms

//public class Main {
//	static final int MAX = 100_000_000;
//	static boolean[] isVisited = new boolean[MAX + 1];
//	static int N, M;
//	
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st;
//		
//		st = new StringTokenizer(br.readLine(), " ");
//		N = Integer.parseInt(st.nextToken());
//		M = Integer.parseInt(st.nextToken());
//		
//		st = new StringTokenizer(br.readLine(), " ");
//		for(int i = 0; i < N; i++) {
//			isVisited[Integer.parseInt(st.nextToken())] = true;
//		}
//		
//		int count = 0;
//		st = new StringTokenizer(br.readLine(), " ");
//		for(int i = 0; i < M; i++) {
//			if(isVisited[Integer.parseInt(st.nextToken())]) count++;
//		}
//		System.out.println(N + M - count * 2);
//	}
//}

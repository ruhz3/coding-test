package n10816_숫자_카드_2;
// 207996KB, 1000ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static Map<Integer, Integer> map = new HashMap<>();
	static int N, M;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			int key = Integer.parseInt(st.nextToken());
			int cnt = map.getOrDefault(key, 0);
			map.put(key, cnt+1);
		}
		
		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < M; i++) {
			int key = Integer.parseInt(st.nextToken());
			int cnt = map.getOrDefault(key, 0);
			sb.append(cnt).append(" ");
		}
		System.out.println(sb.toString());
	}
}

/* 시간 초과*/
//public class Main {
//	static int[] arr;
//	static int N;
//	
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringBuilder sb = new StringBuilder();
//		StringTokenizer st;
//		
//		N = Integer.parseInt(br.readLine());
//		arr = new int[N];
//		st = new StringTokenizer(br.readLine(), " ");
//		for(int i = 0; i < N; i++) {
//			arr[i] = Integer.parseInt(st.nextToken());
//		}
//		Arrays.sort(arr);
//		
//		int M = Integer.parseInt(br.readLine());
//		st = new StringTokenizer(br.readLine()," ");
//		for(int i = 0; i < M; i++) {
//			int key = Integer.parseInt(st.nextToken());
//			int index = findIndex(key);
//			sb.append(arr[index] != key? 0 : countNeighbor(index, key)).append(" ");
//		}
//		System.out.println(sb.toString());
//	}
// 
//	private static int findIndex(int key) {
//		int lo = 0; 
//		int hi = N-1;
//		int mid = 0;
//		while (lo <= hi) {
//			mid = (lo + hi) / 2;
//			if (key == arr[mid]) break;
//			else if(key > arr[mid]) lo = mid + 1;
//			else hi = mid - 1;
//		}
//		return mid;
//	}
//	private static int countNeighbor(int index, int key) {
//		int result = 1;
//		int leftIndex = index, rightIndex = index;
//		while(--leftIndex >= 0 && arr[leftIndex] == key) result++;
//		while(++rightIndex < N && arr[rightIndex] == key) result++;
//		return result;
//	}
//}
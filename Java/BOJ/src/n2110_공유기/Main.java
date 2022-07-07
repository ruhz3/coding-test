package n2110_공유기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, C;
	static int[] houses;
	
	public static void main(String[] args) throws IOException {
		// 00. 입력한다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		houses = new int[N];
		for(int i = 0; i < N; i++)
			houses[i] = Integer.parseInt(br.readLine());
		
		// 01. 이분탐색 전, 정렬해준다.
		Arrays.sort(houses);
		
		// 02. 최소 거리를 대입해보며 이분탐색한다. 
		int minGap = 1;
		int maxGap = houses[N-1] - houses[0] + 1; 
		while(minGap < maxGap) {
			int mid = (minGap + maxGap) / 2;
			if(isAvailable(mid)) maxGap = mid;
			else minGap = mid+1;
		}
		System.out.println(minGap - 1);
	}
	
	/* 해당 최소 거리로 설치가 가능한지 여부 반환*/
	private static boolean isAvailable(int gap) {
		int count = 1;
		int prev = houses[0];
		for(int i = 1, len = houses.length; i < len; i++) {
			int house = houses[i];
			if(house - prev >= gap) {
				count++;
				prev = house;
			}
		}
		return count < C;
	}
}

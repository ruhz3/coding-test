package n2343;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
	static int[] videos; 
	static boolean[] partitions;
	static int[] disks;
	static int N, M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		videos = new int[N];
		int sum = 0;
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			videos[i] = Integer.parseInt(st.nextToken());
			sum += videos[i];
		}
		// 00. 초기값
		int left = 1, right = sum;
		int diskNum = 0, diskVolume = 0;
		
		// 01. 분할 정복
		while(left <= right) {
			int mid = (left + right)/2;
			diskNum = getDiskNum(mid);
			if(diskNum != -1 && diskNum <= M) {
				diskVolume = mid;
				right = mid- 1;
			} else {
				left = mid + 1;
			}
		}
		// 02. 출
		System.out.println(diskVolume);	
	}
	
	private static int getDiskNum(int volume) {
		int count = 1;
		int sum = 0;
		for(int i = 0; i < N; i++) {
			if(videos[i] > volume)
				return -1;
			sum += videos[i];
			if(sum > volume) {
				count++;
				sum = videos[i];
			}
		}
		return count;
	}
}

package n2884_알람_시계;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 
 * 13244KB
 * 116ms
 * */
public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();	
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 0; tc < T; tc++) {
			// 00. 입력하면서, 합을 구하고 평균을 계산한다.
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int[] arr = new int[N];
			int sum = 0;
			for(int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				sum += arr[i];
			}
			double average = (double)sum / N;
			
			// 01. 평균 이상인 사람을 세고, 비율을 구한다.
			int count = 0;
			for(int i = 0; i < N; i++) {
				if(arr[i] > average) count++;
			}
			double rate = (double)count / N;
			
			// 02. 결과를 형식에 맞춰 출력한다.
			String result = String.format("%.3f", rate * 100); 
			sb.append(result).append("%\n");
		}
		System.out.println(sb.toString());
	}
}

package n2805_나무_자르기;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		// 00. 입력부터 받자.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[] wireLen = new int[K];
		
		// 01. 입력하며 평균에 사용할  max를 구하자.
		long max = 0;
        st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < K; i++) {
			wireLen[i] = Integer.parseInt(st.nextToken());
			if(wireLen[i] > max) {
				max = wireLen[i];
			}
		}
		
		// 02. 평균으로 첫 시도를 한다. 
		long head = 0;
		long mid = 0;
		long tail = max+1;
		
		// 03. 찾으러 가보자!
		long sum;
		while(head < tail) {
			// 03-1. 해당 길이로 몇 개 만들 수 있는지 세어본다.
			mid = (head + tail) / 2;
			sum = 0;
			for(int i = 0; i < K; i++) {
				if(wireLen[i] - mid > 0)
					sum += (wireLen[i] - mid);
			}
			// 03-2. 이분탐색한다.
			if(sum < N) {
				tail = mid;
			} else {
				head = mid + 1;
			}
		}
		System.out.println(head-1);
	}
}
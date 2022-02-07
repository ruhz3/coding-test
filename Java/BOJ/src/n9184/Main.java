package n9184;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][][] cache = new int [51][51][51];
	
	public static int w(int a, int b, int c) {
		// 결국 하나가 0을 터치하는 방법의 수를 세는 것이다.
		if(a <= 0 || b <= 0 || c <= 0) {
			return 1;
		}
		// 동적 계획법
		if(cache[a][b][c] != 0) {
			return cache[a][b][c];
		}
		
		// 20이 넘는게 하나라도 있으면 20, 20, 20으로 맞춰준다.
		if(a > 20 || b > 20 || c > 20) {
			cache[a][b][c] = w(20, 20, 20);
			return cache[a][b][c];
		}
		// a, b, c가 순서대로 이루어진다면, 
		if(a < b && b < c) {
			cache[a][b][c] = w(a, b, c-1) + w(a, b-1, c-1) - w(a, b-1, c); 
			return cache[a][b][c]; 
		}
		// a, b, c가 순서대로가 아니라면,
		cache[a][b][c] = w(a-1, b, c) + w(a-1, b-1, c) + w(a-1, b, c-1) - w(a-1, b-1, c-1); 
		return cache[a][b][c];  
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder result = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if(a == -1 && b == -1 && c == -1) {
				break;
			}
			result.append("w(").append(a).append(", ").append(b).append(", ").append(c).append(") = ").append(w(a, b, c)).append("\n");
		}
		
		System.out.println(result.toString());
	}
}
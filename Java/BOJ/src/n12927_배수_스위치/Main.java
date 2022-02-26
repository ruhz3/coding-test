package n12927_배수_스위치;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static char[] lights;
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		lights = br.readLine().toCharArray();
		N = lights.length;
		
		int cnt = 0;
		for(int i = 0; i < N; i++) {
			if(lights[i] == 'Y') {
				for(int j = i+1; j <= N; j+=i+1 ) {
					lights[j-1] = (lights[j-1] == 'Y') ? 'N' : 'Y';
				}
				cnt++;
			}
		}
		System.out.println(cnt);
	}
}

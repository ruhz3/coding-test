package n1107_리모컨;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static String dest;
	static int destInt;
	static char[] rm;
	static boolean[] buttons = new boolean[10]; 
	static int N, M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		dest = br.readLine();
		destInt = Integer.parseInt(dest);
		N = dest.length();
		rm = new char[N];
		M = Integer.parseInt(br.readLine());
		Arrays.fill(buttons, true);
		if(M > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < M; i++) {
				buttons[Integer.parseInt(st.nextToken())] = false;
			}
		}	
		// 순수 +, -로 간다.
		int plusMinusCount = Math.abs(destInt - 100);
		int pressCount = pressCount(0);
	}
	
	private static int pressCount(int idx) {
		int result = Integer.MAX_VALUE;
		char key = dest.charAt(idx);
		
		rm[idx] = key;
		result = Math.min(result, pressCount(idx+1));
		rm[idx] = 0;
		
		char downKey = key;
		boolean available = false;
		while(downKey > 0) {
			if(buttons[downKey]) {
				available = true;
				break;
			}
			downKey--;
		}
		if(available) {
			rm[idx] = downKey;
		}
		
		char upKey = key;
		available = false;
		while(upKey > 0) {
			if(buttons[upKey]) {
				available = true;
				break;
			}
			upKey++;
		}
		if(available) {
			rm[idx] = upKey;
		}
		return result;
	}
}

package n1193_분수찾기;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int r, c;
	static boolean isOdd;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int X = Integer.parseInt(br.readLine());
		r = 1;
		c = 1;
		while(--X > 0) {
			run();
		}
		System.out.println(r + "/" + c);
		System.out.println(3 == 1);
		System.out.println(3 > 1);
		System.out.println(3 < 1);
	}
	private static void run() {
		if(!isOdd) {
			if(r == 1) {
				c++;
				isOdd = true;
			} else {
				r--;
				c++;
			}
		} else {
			if(c == 1) {
				r++;
				isOdd = false;
			} else {
				r++;
				c--;
			}
		}
	}
}

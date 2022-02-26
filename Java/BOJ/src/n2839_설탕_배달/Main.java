package n2839_설탕_배달;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		System.out.println(countMinBag(N));
		
	}
	private static int countMinBag(int sugarWeight) {
		int x = 0;
		int res = 0;
		while(true) {
			int n = sugarWeight+2*x;
			if(n%5 == 0) {
				res = n/5;
				if(res - x >= 0) return res;
				else return -1; 
			}
			x++;
		}
	}
}

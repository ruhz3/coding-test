package n9019_DSLR;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static 
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
		}
	}

	private static void operate(int num, char command) {
		int register = 0;
		switch(command){
		case 'D':
			register = num*2;
			if(register > 9999) register %= 10000;
			break;
		case 'S':
			register = num-1;
			if(register < 0) register = 9999;
			break;
		case 'L':
			register = (num / 1000) + (num % 1000) * 10;
			break;
		case 'R':
			register = (num / 10) + (num % 10) * 1000;
			break;
		}
	}
		
}

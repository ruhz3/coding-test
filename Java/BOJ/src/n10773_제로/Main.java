package n10773_제로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	private static Stack<Integer> stack = new Stack<>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int sum = 0;
		int input;
		for(int i = 0; i < N; i++) {
			input = Integer.parseInt(br.readLine());
			if(input != 0) {
				stack.push(input);
				sum += input;
			} else {
				sum -= stack.pop();
			}
		}
		System.out.println(sum);
	}
}

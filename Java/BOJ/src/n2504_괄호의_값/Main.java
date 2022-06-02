package n2504_괄호의_값;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {  
	static Stack<Character> stack = new Stack<Character>();
	static String input;
	static int[] level;
	static int len;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input = br.readLine();
		len = input.length();
		level = new int[len];
		
		// 00. level 배열을 구성한다.
		for(int i = 0; i < len; i++) {
			char ch = input.charAt(i);
			int lv = stack.size();
			if(!stack.isEmpty() && ((ch == ')' && stack.peek() == '(') || (ch == ']' && stack.peek() == '['))) {
				stack.pop();
				lv--;
			} else {
				stack.add(ch);
			}
			level[i] = lv;
		}
		// 01. 스택에 아직 숫자가 남아있다면, 제대로 된 문자열이 아니다.
		if(stack.size() != 0)
			System.out.println("0");
		else
			System.out.println(calculate(0, len, 0));
	}
	
	private static int calculate(int start, int end, int lv) {
		int result = 0;
		
		char openChar = '0';
		int openindex = 0;
		int closeindex = 0;
		boolean isClosed = true;
		for(int i = start; i < end; i++) {
			// 닫혀 있는 데, 해당 레벨을 만난 경우 
			if(isClosed && level[i] == lv) {
				openChar = input.charAt(i);
				openindex = i;
				isClosed = false;
				continue;
			}
			// 열려 있는데, 해당 레벨의 닫는 문자를 만난 경우
			if(!isClosed && level[i] == lv) {
				closeindex = i;
				int value = 0;
				if(openChar == '(') value = 2;
				if(openChar == '[') value = 3;
				
				if(closeindex - openindex == 1) result += value;
				else result += value * calculate(openindex+1, closeindex, lv+1);
				isClosed = true;
				continue;
			}
		}
		return result;
	}
}

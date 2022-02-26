package n1233_사칙연산_유효성_검사;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1233_사칙연산_유효성_검사
 * 메모리 :	19,360 kb
 * 실행시간 :	118 ms
 */

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder result = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int tc = 1; tc <= 10; tc++) {
			result.append("#").append(tc).append(" ");
			
			int N = Integer.parseInt(br.readLine());
			int isValid = 1;
			boolean hasOperator;
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				hasOperator = false;
				
				// 00. 연산자가 가운데에 붙은 이상, 피연산자 하나로는 연산이 성립할 수없다.
				int len = st.countTokens();
				if(len != 4 && len != 2) {
					isValid = 0;
					continue;
				}
				
				// 01. 가운데 노드가 연산자가 아니거나, 리프노드가 연산자라면 연산이 성립할 수 없다. 
				st.nextToken();
				char op = st.nextToken().charAt(0);
				if(op == '+' || op == '-' || op == '*' || op == '/') {
					hasOperator = true;
				}
				if(!hasOperator && len == 4) {
					isValid = 0;
					continue;
				}
				if(hasOperator && len == 2) {
					isValid = 0;
					continue;
				}
			}
			result.append(isValid).append("\n");
		}
		System.out.println(result.toString());
	}
}

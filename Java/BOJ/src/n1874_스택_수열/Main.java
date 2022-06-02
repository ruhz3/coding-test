package n1874_스택_수열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 00. 입력 받는다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] seq = new int[n];
		for (int i = 0; i < n; i++) {
			seq[i] = Integer.parseInt(br.readLine());
		}
		
		// 01. num은 증가 연산만 하며 오름차순을 유지한다. target을 poll하는 것이 반복문의 목적이다.
		StringBuilder sb = new StringBuilder();
		Stack<Integer> stack = new Stack<Integer>();
		int target;
		int num = 1;
		for (int i = 0; i < n; i++) {
			target = seq[i];
			while (true) {
				// 02-1. target이 현재 offer 할 수 있는 번호보다 크다면 offer한다.
				// 02-2. target이 현재 offer할 수 있는 값과 같다면, offer-poll이 연속으로 일어날 것이므로 넣었다 뺀 셈 친다.
				// 02-3. target이 현재 offer 할 수 있는 번호보다 작다면 stack 안에서 찾아야 하므로 꺼내야 한다.
				if (target > num) {
					stack.push(num++);
					sb.append("+");
				} else if (target == num) {
					num++;
					sb.append("+-");
					break;
				} else {
					// 03-1. 때마침 target이 맨 위에 있다면 꺼낸다.
					// 03-2. 꺼내야 되는데 위에 다른 것을 꺼내고 꺼내야되는 상황이라면 실패다. 
					if (target == stack.peek()) {
						stack.pop();
						sb.append("-");
						break;
					} else {
						System.out.println("NO");
						return;
					}
				}

			}
		}
		String answer = sb.toString();
		for(int i = 0; i < answer.length(); i++) {
			System.out.println(answer.charAt(i));
		}
	}
}

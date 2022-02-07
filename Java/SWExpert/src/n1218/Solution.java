package n1218;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/*
 * 1289_원재의 메모리 복구
 * 메모리 :	19,184 kb
 * 실행시간 :	117 ms
 */

public class Solution {
	public static void main(String[] args) throws IOException {
		int[] bracketCount = new int[4];
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		// 괄호를 여는 기호에 번호 부여
		map.put('(', 0);
		map.put('[', 1);
		map.put('{', 2);
		map.put('<', 3);
		// 괄호를 딛는 기호에 번호 부여
		map.put(')', 4);
		map.put(']', 5);
		map.put('}', 6);
		map.put('>', 7);

		StringBuilder result = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean isFalse;
		for (int tc = 1; tc <= 10; tc++) {
			result.append("#").append(tc).append(" ");
			bracketCount[0] = 0;
			bracketCount[1] = 0;
			bracketCount[2] = 0;
			bracketCount[3] = 0;
			isFalse = false;
			
			int len = Integer.parseInt(br.readLine());
			String input = br.readLine();

			for (int i = 0; i < len; i++) {
				int get = map.get(input.charAt(i));
				
				// 00. 기호 번호가  0, 1, 2, 3이면 숫자를 세고, 4, 5, 6, 7이면 숫자를 빼자.
				if (get < 4) {
					bracketCount[get]++;
				} else {
					bracketCount[get % 4]--;
					isFalse = bracketCount[get % 4] < 0;
				}
				if (isFalse)
					break;
			}
			// 01. 열린 괄호가 없는데 닫으려 하는 경우를 걸러낸다.
			if (isFalse) {
				result.append(0).append("\n");
				continue;
			}

			// 02. 닫히지 않은 괄호가 있는 경우를 걸러낸다.
			for (int i = 0; i < 4; i++) {
				isFalse = bracketCount[i] != 0;
				if (isFalse)
					break;
			}
			
			// 04. 아무것도 걸리지 않았다면, 정상 경우이다.
			if (!isFalse) {
				result.append(1).append("\n");
			} else {
				result.append(0).append("\n");
			}
		}
		System.out.println(result.toString());
	}
}

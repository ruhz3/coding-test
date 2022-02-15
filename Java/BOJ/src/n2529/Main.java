package n2529;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder result = new StringBuilder();

	static int k;
	static char[] flow;
	static int[] smallNum;
	static int[] bigNum;
	static boolean isDone;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		k = Integer.parseInt(br.readLine());

		flow = new char[k];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < k; i++) {
			flow[i] = st.nextToken().charAt(0);
		}
		// 00. 큰 수 찾는다.
		bigNum = new int[k + 1];
		isDone = false;
		findBigNum(0);
		// 01. 작은 수 찾는다.
		smallNum = new int[k + 1];
		isDone = false;
		findSmallNum(0);
		// 02. 출력한다.
		System.out.println(result.toString());
	}

	public static void findSmallNum(int idx) {
		// * 탈출 조건
		if (idx == k+1 || isDone) {
			if(!isDone) {
				for(int i = 0; i < k+1; i++)
					result.append(smallNum[i]);
				result.append("\n");
				isDone = true;
			}
			return;
		}
		// 00. 처음엔 다른 검사가 필요 없다. 
		if (idx == 0) {
			for (int i = 0; i <= 9; i++) {
				smallNum[idx] = i;
				findSmallNum(idx + 1);
			}
			return;
		}
		// 01. 이후에는 이전 숫자와 flow를 따져 넣는다.
		int prev = smallNum[idx - 1];
		char op = flow[idx - 1];
		boolean hasNothingToPut = true;
		if (op == '<') {
			for (int i = prev + 1; i <= 9; i++) {
				// 02. i가 이전 원소에 있는지 검사해보자.
				boolean isExist = false;
				for (int j = 0; j < idx; j++) {
					if (smallNum[j] == i) {
						isExist = true;
						break;
					}
				}
				// 03. 없다면, 이전 원소와 비교해 부등호 흐름에 맞춰 넣는다.
				if (!isExist) {
					hasNothingToPut = false;
					smallNum[idx] = i;
					findSmallNum(idx + 1);
				}
			}
			// 04. 아무것도 넣을게 없었다면, 가지치기한다.
			if (hasNothingToPut) return;
		}
		// 05. '>'도 똑같이 진행한다.
		else {
			for (int i = 0; i < prev; i++) {
				boolean isExist = false;
				for (int j = 0; j < idx; j++)
					if (smallNum[j] == i) {
						isExist = true;
						break;
					}
				if (!isExist) {
					smallNum[idx] = i;
					findSmallNum(idx + 1);
				}
			}
			if (hasNothingToPut) return;
		}
	}

	public static void findBigNum(int idx) {
		// * 탈출 조건
		if (idx == k+1 || isDone) {
			if(!isDone) {
				for(int i = 0; i < k+1; i++)
					result.append(bigNum[i]);
				result.append("\n");
				isDone = true;
			}
			return;
		}
		// 00. 처음엔 다른 검사가 필요 없다. 
		if (idx == 0) {
			for (int i = 9; i >= 0; i--) {
				bigNum[idx] = i;
				findBigNum(idx + 1);
			}
			return;
		}
		// 01. 이후에는 이전 숫자와 flow를 따져 넣는다.
		int prev = bigNum[idx - 1];
		char op = flow[idx - 1];
		boolean hasNothingToPut = true;
		if (op == '<') {
			for (int i = 9; i >= prev+1; i--) {
				// 02. i가 이전 원소에 있는지 검사해보자.
				boolean isExist = false;
				for (int j = 0; j < idx; j++) {
					if (bigNum[j] == i) {
						isExist = true;
						break;
					}
				}
				// 03. 없다면, 이전 원소와 비교해 부등호 흐름에 맞춰 넣는다.
				if (!isExist) {
					hasNothingToPut = false;
					bigNum[idx] = i;
					findBigNum(idx + 1);
				}
			}
			// 04. 아무것도 넣을게 없었다면, 가지치기한다.
			if (hasNothingToPut) return;
		}
		// 05. '>'도 똑같이 진행한다.
		else {
			for (int i = prev-1; i >= 0; i--) {
				boolean isExist = false;
				for (int j = 0; j < idx; j++)
					if (bigNum[j] == i) {
						isExist = true;
						break;
					}
				if (!isExist) {
					bigNum[idx] = i;
					findBigNum(idx + 1);
				}
			}
			if (hasNothingToPut) return;
		}
	}
}

package n2529_부등호;

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

	public static void findSmallNum(int index) {
		// * 탈출 조건
		if (index == k+1 || isDone) {
			if(!isDone) {
				for(int i = 0; i < k+1; i++)
					result.append(smallNum[i]);
				result.append("\n");
				isDone = true;
			}
			return;
		}
		// 00. 처음엔 다른 검사가 필요 없다. 
		if (index == 0) {
			for (int i = 0; i <= 9; i++) {
				smallNum[index] = i;
				findSmallNum(index + 1);
			}
			return;
		}
		// 01. 이후에는 이전 숫자와 flow를 따져 넣는다.
		int prev = smallNum[index - 1];
		char op = flow[index - 1];
		boolean hasNothingToPut = true;
		if (op == '<') {
			for (int i = prev + 1; i <= 9; i++) {
				// 02. i가 이전 원소에 있는지 검사해보자.
				boolean isExist = false;
				for (int j = 0; j < index; j++) {
					if (smallNum[j] == i) {
						isExist = true;
						break;
					}
				}
				// 03. 없다면, 이전 원소와 비교해 부등호 흐름에 맞춰 넣는다.
				if (!isExist) {
					hasNothingToPut = false;
					smallNum[index] = i;
					findSmallNum(index + 1);
				}
			}
			// 04. 아무것도 넣을게 없었다면, 가지치기한다.
			if (hasNothingToPut) return;
		}
		// 05. '>'도 똑같이 진행한다.
		else {
			for (int i = 0; i < prev; i++) {
				boolean isExist = false;
				for (int j = 0; j < index; j++)
					if (smallNum[j] == i) {
						isExist = true;
						break;
					}
				if (!isExist) {
					smallNum[index] = i;
					findSmallNum(index + 1);
				}
			}
			if (hasNothingToPut) return;
		}
	}

	public static void findBigNum(int index) {
		// * 탈출 조건
		if (index == k+1 || isDone) {
			if(!isDone) {
				for(int i = 0; i < k+1; i++)
					result.append(bigNum[i]);
				result.append("\n");
				isDone = true;
			}
			return;
		}
		// 00. 처음엔 다른 검사가 필요 없다. 
		if (index == 0) {
			for (int i = 9; i >= 0; i--) {
				bigNum[index] = i;
				findBigNum(index + 1);
			}
			return;
		}
		// 01. 이후에는 이전 숫자와 flow를 따져 넣는다.
		int prev = bigNum[index - 1];
		char op = flow[index - 1];
		boolean hasNothingToPut = true;
		if (op == '<') {
			for (int i = 9; i >= prev+1; i--) {
				// 02. i가 이전 원소에 있는지 검사해보자.
				boolean isExist = false;
				for (int j = 0; j < index; j++) {
					if (bigNum[j] == i) {
						isExist = true;
						break;
					}
				}
				// 03. 없다면, 이전 원소와 비교해 부등호 흐름에 맞춰 넣는다.
				if (!isExist) {
					hasNothingToPut = false;
					bigNum[index] = i;
					findBigNum(index + 1);
				}
			}
			// 04. 아무것도 넣을게 없었다면, 가지치기한다.
			if (hasNothingToPut) return;
		}
		// 05. '>'도 똑같이 진행한다.
		else {
			for (int i = prev-1; i >= 0; i--) {
				boolean isExist = false;
				for (int j = 0; j < index; j++)
					if (bigNum[j] == i) {
						isExist = true;
						break;
					}
				if (!isExist) {
					bigNum[index] = i;
					findBigNum(index + 1);
				}
			}
			if (hasNothingToPut) return;
		}
	}
}

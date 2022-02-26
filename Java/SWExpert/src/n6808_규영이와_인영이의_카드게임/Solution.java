package n6808_규영이와_인영이의_카드게임;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 6808_규영이와_인영이의_카드게임
 * 메모리 :	23,508 kb
 * 실행시간 :	128 ms
 */

public class Solution {
	static int[] input = new int[9];
	static int[] arr = new int[9];
	
	public static void main(String[] args) throws IOException {
		StringBuilder result = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < 9; i++) {
				input[i] = Integer.parseInt(st.nextToken());
			}
			// 00. 인영이의 패도 같이 구성해준다.
			for(int i = 1, k = 0; i <= 18; i++) {
				boolean isExist = false;
				for(int j = 0; j < 9; j++) {
					if(input[j] == i) {
						isExist = true;
						break;
					}
				}
				if(!isExist) arr[k++] = i;
			}
			// 01. 인영이의 패를 nextPermutation으로 바꿔가며 승부를 결정한다.
			int kyCnt = 0;
			int iyCnt = 0;
			do {
				int kyScore = 0;
				int iyScore = 0;
				for(int i = 0; i < 9; i++) {
					if(input[i] > arr[i]) kyScore += input[i] + arr[i];
					else if(input[i] < arr[i]) iyScore += input[i] + arr[i];
				}
				if(kyScore > iyScore) kyCnt++;
				else if(kyScore < iyScore) iyCnt++;
			}while(nextPermutation());
			
			// 02. 결과를 출력한다.
			result.append("#").append(tc).append(" ")
					.append(kyCnt).append(" ")
					.append(iyCnt).append("\n");
		}
		System.out.println(result.toString());
		
	}
	/* next permutation */
	private static boolean nextPermutation() {
		// 00. 뒤에서 시작했을 때 오름차순이 끝나는 값을 찾아준다.
		int i = 8;
		while(i > 0 && arr[i - 1] >= arr[i]) --i;
		if(i == 0) return false;
		
		// 01. 위에서 찾은 값 바로 앞 수와 바꿀 수를 결정한다.
		int j = 8;
		while(arr[i - 1] >= arr[j]) --j;
		
		// 02. 바꿔준다.
		int tmp = arr[i - 1];
		arr[i - 1] = arr[j];
		arr[j] = tmp;
		
		// 03. 뒷 부분을 오름차순으로 정렬해준다.
		int k = 8;
		while(i < k) {
			tmp = arr[i];
			arr[i++] = arr[k];
			arr[k--] = tmp;
		}
		return true;
	}
}

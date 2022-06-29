package n2473_세_용액;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2 {
	static int N;
	static long[] pos, neg;
	static int pSize, nSize;
	static long minAbs = Long.MAX_VALUE;
	static long[] answer = new long[3];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 00. 음수, 양수를 나눠 입력한다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		pos = new long[N];
		neg = new long[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			long n = Long.parseLong(st.nextToken());
			if(n > 0) pos[pSize++] = n;
			else neg[nSize++] = n * -1;
		}
		
		// 01. 각 배열을 사이즈에 맞게 자르고 정렬한다.
		pos = Arrays.copyOf(pos, pSize);
		neg = Arrays.copyOf(neg, nSize);
		Arrays.sort(pos);
		Arrays.sort(neg);
				
		// 02. 뽑을 숫자가 3개 밖에 안 되므로, 경우의 수를 나눠 찾아본다.
		// 양수 3개, 음수 0개
		if(pSize >= 3) updateAnswer(pos[0], pos[1], pos[2]);
		
		// 양수 2개, 음수 1개
		if(pSize >= 2 && nSize>= 1) {
			for(int i = 0; i < pSize-1; i++) {
				for(int j = i+1; j < pSize; j++) {
					long a = pos[i];
					long b = pos[j];
					updateAnswer(a, b, binarySearch(neg, a + b) * -1);
				}
			}			
		}
		// 양수 1개, 음수 2개
		if(pSize >= 1 && nSize >= 2) {
			for(int i = 0; i < nSize-1; i++) {
				for(int j = i+1; j < nSize; j++) {
					long a = neg[i];
					long b = neg[j];
					updateAnswer(a * -1, b * -1, binarySearch(pos, a + b));
				}
			}			
		}
		// 양수 0개, 음수 3개
		if(nSize >= 3) updateAnswer(neg[0] * -1, neg[1] * -1, neg[2] * -1);
		
		// 03. 오름차순으로 정렬해 출력한다.
		Arrays.sort(answer);
		for(long num : answer) System.out.print(num + " ");
	}
	
	/* 가장 가까운 값을 찾아주는 함수*/
	private static long binarySearch(long[] list, long value) {		 
		long result = 0;
		int start = 0;
		int end = list.length - 1;
		int mid = 0;
		boolean isFound = false;
		while(start <= end) {
			mid = (start + end) / 2;
			long n = list[mid];
			if(n < value) {
				start = mid + 1;
			} else if(n > value){
				end = mid - 1;
			} else {
				result = n;
				isFound = true;
				break;
			}
		}
		// 정확한 값을 찾지 못했다면, 답은 최종 위치 근처에 있다.
		if(!isFound) {
			long gap1 = (start >= 0 && start < list.length) ? Math.abs(value - list[start]) : Long.MAX_VALUE;
			long gap2 = (end >= 0 && end < list.length) ? Math.abs(value - list[end]) : Long.MAX_VALUE;
			result = gap1 < gap2 ? list[start] : list[end];
		}
		return result;
	}
	
	/* 세 숫자로 최대를 갱신하는 함수 */
	private static void updateAnswer(long n1, long n2, long n3) {
		long abs = Math.abs(n1 + n2 + n3);
		if(abs >= minAbs) return;
		minAbs = abs;
		answer[0] = n1;
		answer[1] = n2;
		answer[2] = n3;
	}
}

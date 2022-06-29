package n2473_세_용액;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static List<Long> positives = new ArrayList<>();
	static List<Long> negatives = new ArrayList<>();
	static long minAbs = Long.MAX_VALUE;
	static long[] answer = new long[3];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 00. 입력한다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			long n = Long.parseLong(st.nextToken());
			if(n > 0) positives.add(n);
			else negatives.add(n * -1);
		}
		
		// 01. 수를 정렬한다.
		Collections.sort(positives);
		Collections.sort(negatives);
		int pSize = positives.size();
		int nSize = negatives.size();
		
		// 02. 경우에 따라 찾아보자.
		// (3, 0)
		if(pSize >= 3) updateAnswer( positives.get(0), positives.get(1), positives.get(2));
		// (2, 1)
		if(pSize >= 2 && nSize>= 1) {
			for(int i = 0; i < pSize-1; i++) {
				for(int j = i+1; j < pSize; j++) {
					long a = positives.get(i);
					long b = positives.get(j);
					updateAnswer(a, b, binarySearch(negatives, a + b) * -1);
				}
			}			
		}
		// (1, 2)
		if(pSize >= 1 && nSize >= 2) {
			for(int i = 0; i < nSize-1; i++) {
				for(int j = i+1; j < nSize; j++) {
					long a = negatives.get(i);
					long b = negatives.get(j);
					updateAnswer(a * -1, b * -1, binarySearch(positives, a + b));
				}
			}			
		}
		// (0, 3)
		if(nSize >= 3) updateAnswer(negatives.get(0) * -1, negatives.get(1) * -1, negatives.get(2) * -1);
		Arrays.sort(answer);
		for(long num : answer) System.out.print(num + " ");
	}
	private static long binarySearch(List<Long> list, long value) {		 
		long result = 0;
		int start = 0;
		int end = list.size() - 1;
		int mid = 0;
		
		boolean isFound = false;
		while(start <= end) {
			mid = (start + end) / 2;
			long n = list.get(mid);
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
		if(!isFound) {
			long gap1 = (start >= 0 && start < list.size()) ? Math.abs(value - list.get(start)) : Long.MAX_VALUE;
			long gap2 = (end >= 0 && end < list.size()) ? Math.abs(value - list.get(end)) : Long.MAX_VALUE;
			result = gap1 < gap2 ? list.get(start) : list.get(end);
		}
		return result;
	}
	private static void updateAnswer(long n1, long n2, long n3) {
		long abs = Math.abs(n1 + n2 + n3);
		if(abs >= minAbs) return;
		minAbs = abs;
		answer[0] = n1;
		answer[1] = n2;
		answer[2] = n3;
	}
}

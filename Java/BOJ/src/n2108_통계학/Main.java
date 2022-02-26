package n2108_통계학;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;


public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder result = new StringBuilder();
		
		// 00. 입력을 받는다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int input, sum = 0;
		ArrayList<Integer> NumList = new ArrayList<Integer>();
		
		int maxCount = 0;
		int[] countArray = new int[8001];
		ArrayList<Integer> MaxList = new ArrayList<Integer>();
		
		for(int i = 0; i < N; i++) {
			input = Integer.parseInt(br.readLine());
			countArray[input + 4000]++;
			if(countArray[input + 4000] == maxCount) {
				MaxList.add(input);
			} else if(countArray[input + 4000] > maxCount) {
				MaxList.clear();
				MaxList.add(input);
				maxCount = countArray[input + 4000];
			}
			sum += input;
			NumList.add(input);
		}
		
		// 01. 산술평균을 구한다.
		result.append((int)Math.round((double)sum/NumList.size()));
		result.append("\n");
		
		// 02. 중앙값을 구한다.
		Collections.sort(NumList);
		result.append(NumList.get(NumList.size()/2));
		result.append("\n");
		
		// 03. 최빈값을 구한다.
		Collections.sort(MaxList);
		if(MaxList.size() > 1) {
			result.append(MaxList.get(1));
		} else {
			result.append(MaxList.get(0));
		}
		result.append("\n");
		
		// 04. 범위를 구한다.
		result.append(NumList.get(NumList.size()-1) - NumList.get(0));

		// 05. 결과를 출력한다.
		System.out.print(result.toString());
	}
}

package n3040;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i < 9; i++) {
			list.add(Integer.parseInt(br.readLine()));
		}
		Collections.sort(list);
		int i = 0;
		int j = 0;
		// 00. 반복문을 이용한 순열 구현 (가짜 두명 i, j를 추정해본다).
		OUTER: for(i = 0; i < 9-1; i++) {
			for(j = i+1; j < 9; j++) {
				int sum = 0;
				for(int k = 0; k < 9; k++) {
					if((k != i) && (k != j)) {
						sum += list.get(k);
					}
				}
				if(sum == 100) {
					break OUTER;
				}
			}
		}
		StringBuilder result = new StringBuilder();
		for(int k = 0; k < 9; k++) {
			if((k != i) && (k != j)) {
				result.append(list.get(k)).append("\n");
			}
		}
		System.out.println(result.toString());
	}
}


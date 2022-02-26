package n10814_나이순_정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		// * 1~200세 각각 최대 길이의 테이블을 가지며, 그 끝을 따로 배열로 관리한다.  
		String[][] table = new String[201][10000];
		int[] len = new int[201];
		
		// 00. 입력한다. 
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int age;
		String name;
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			age = Integer.parseInt(st.nextToken());
			name = st.nextToken();
			table[age][len[age]++] = name; /*중요!*/
		}
		
		// 01. 출력한다. 
		for(int i = 0; i < 201; i++) {
			for(int j = 0; j < len[i]; j++) {
				System.out.println(i + " " + table[i][j]);
			}
		}
	}
}

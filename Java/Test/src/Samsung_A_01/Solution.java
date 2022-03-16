package Samsung_A_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Gate {
	int pos;
	int peopleNum;
	public Gate(int pos, int peopleNum) {
		this.pos = pos;
		this.peopleNum = peopleNum;
	}
}

public class Solution {
	static Gate[] gates = new Gate[3];
	static int[] order = new int[3];
	static boolean[] isVisited;
	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 00. 입력 받는다.
		StringBuilder result = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1 ; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			isVisited = new boolean[N+1];
			for(int i = 0; i < 3; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int pos = Integer.parseInt(st.nextToken());
				int peopleNum = Integer.parseInt(st.nextToken());
				gates[i] = new Gate(pos, peopleNum);
			}
			
			// 01. 게이트 오픈 순서를 조합해 열어준다. 
			int min = Integer.MAX_VALUE;
			for(int first = 0; first < 3; first++) {
				for(int second = 0; second < 3; second++) {
					if(first == second) continue;
					for(int third = 0; third < 3; third++) {
						if(first == third || second == third) continue;
						order[0] = first;
						order[1] = second;
						order[2] = third;
						Arrays.fill(isVisited, false);
						int res = gateOpen(0);
						min = Math.min(min, res);
					}
				}
			}
			// 02. 출력한다.
			result.append("#").append(tc).append(" ").append(min).append("\n");
		}
		System.out.println(result.toString());
	}
	
	private static int gateOpen(int count) {
		if(count >= 3) return 0;
		
		int pos = gates[order[count]].pos;
		int num = gates[order[count]].peopleNum;
		
		int distance = 0;
		int sum = 0;
		int leftIdx = 0;
		int rightIdx = 0;
		
		while(num > 0) {
			leftIdx = pos - distance;
			rightIdx = pos + distance;
			if(leftIdx >= 1 && !isVisited[leftIdx]) {
				isVisited[leftIdx] = true;
				sum += distance+1;
				num--;
			}
			if(num <= 0) break;
			if(rightIdx <= N && !isVisited[rightIdx]) {
				isVisited[rightIdx] = true;
				sum += distance+1;
				num--;
			}
			distance++;
		}
		
		boolean[] tmp = isVisited.clone();
		int result = gateOpen(count+1);
		isVisited = tmp;
		boolean leftVisited = leftIdx >= 1 && isVisited[leftIdx];
		boolean rightAvailable = rightIdx <= N && !isVisited[rightIdx];
		if(leftVisited && rightAvailable) {
			isVisited[leftIdx] = false;
			isVisited[rightIdx] = true;
			result = Math.min(result, gateOpen(count+1));
		}

		return result + sum;
	}
}

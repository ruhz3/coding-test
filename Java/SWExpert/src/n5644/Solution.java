package n5644;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 5644_무선 충전
 * 메모리 :	23,508 kb
 * 실행시간 :	128 ms
 */

/* 사람 */
class Person {
	int[] rowDir = { 0, -1, 0, 1, 0 };
	int[] colDir = { 0, 0, 1, 0, -1 };
	int[] queue;
	int now = 0;
	int r;
	int c;

	public Person(int r, int c) {
		this.r = r;
		this.c = c;
	}

	public boolean move() {
		if(now == queue.length)
			return false;
		int key = queue[now];
		r += rowDir[key];
		c += colDir[key];
		now++;
		return true;
	}
}

/* 충전기 */
class AP {
	int r;
	int c;
	int coverage;
	int performance;

	public AP(int r, int c, int coverage, int performance) {
		this.r = r;
		this.c = c;
		this.coverage = coverage;
		this.performance = performance;
	}

	public boolean isIncluding(int r, int c) {
		return (Math.abs(this.r - r) + Math.abs(this.c - c)) <= coverage;
	}
}

/* main */
public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		boolean[] available_A; // A가 현재 연결할 수 있는 AP를 체크 한 배열
		boolean[] available_B;
		Person A;
		Person B;
		
		StringBuilder result = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			A = new Person(1, 1);
			B = new Person(10, 10);
			
			// 00. 입력한다.
			st = new StringTokenizer(br.readLine(), " ");
			int M = Integer.parseInt(st.nextToken()); // 총 이동시간
			int N = Integer.parseInt(st.nextToken()); // BC의 개수
			A.queue = new int[M];
			B.queue = new int[M];
			AP[] APs = new AP[N];
			available_A = new boolean[N];
			available_B = new boolean[N];

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < M; i++) A.queue[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < M; i++) B.queue[i] = Integer.parseInt(st.nextToken());
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int c = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				int coverage = Integer.parseInt(st.nextToken());
				int performance = Integer.parseInt(st.nextToken());
				APs[i] = new AP(r, c, coverage, performance);
			}
			
			// 01. 게임 시작
			int battery = 0;
			do {
				// 02. 현재 A, B가 연결 가능한 AP 목록을 가져온다.
				for (int i = 0; i < N; i++) {
					available_A[i] = APs[i].isIncluding(A.r, A.c);
					available_B[i] = APs[i].isIncluding(B.r, B.c);
				}
				// 03.연결 시 가장 충전을 잘할 수 있는 조합을 찾아본다.
				int maxSum = 0;
				for (int i = 0; i < N; i++) {
					// 04. 먼저 가능한 A를 담는다. 
					int sum = 0;
					if (available_A[i]) sum = APs[i].performance;
					// 05. 다음 가능한 B를 담는다.
					for (int j = 0; j < N; j++) {
						// 06. A랑 같이 써야 하거나, B가 연결이 안 되어 있다면 더 해줄 필요 없다.
						if ((available_A[i] && i == j) || !available_B[j])
							maxSum = Math.max(maxSum, sum);
						else if(available_B[j])
							maxSum = Math.max(maxSum, sum+ APs[j].performance);
					}
				}
				battery += maxSum;
			} while(A.move() && B.move());
			result.append("#").append(tc).append(" ").append(battery).append("\n");
		}
		System.out.println(result.toString());
	}
}

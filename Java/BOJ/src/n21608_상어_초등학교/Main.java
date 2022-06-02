package n21608_상어_초등학교;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Student {
	boolean[] check;
	int[][] map;
	int[] rowDir = { -1, 0, 1, 0 }; // 상, 우, 하, 좌
	int[] colDir = { 0, 1, 0, -1 }; // 상, 우, 하, 좌
	int N;
	
	int id;
	int r, c;

	public Student(int[][] map, int N, int id) {
		check = new boolean[N*N+1];
		this.N = N;
		this.map = map;
		this.id = id;
	}

	public void sit(int r, int c) {
		this.r = r;
		this.c = c;
		map[r][c] = id;
	}

	public void paint() {
		for (int dir = 0; dir < 4; dir++) {
			int nr = r + rowDir[dir];
			int nc = c + colDir[dir];
			if (nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] > 0)
				continue;
			map[nr][nc]--;
		}
	}

	public void erase() {
		for (int dir = 0; dir < 4; dir++) {
			int nr = r + rowDir[dir];
			int nc = c + colDir[dir];
			if (nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] > 0)
				continue;
			map[nr][nc] = 0;
		}
	}

	public int evaluate() {
		int score = 0;
		for (int dir = 0; dir < 4; dir++) {
			int nr = r + rowDir[dir];
			int nc = c + colDir[dir];
			if (nr < 0 || nr >= N || nc < 0 || nc >= N)
				continue;
			if (map[nr][nc] > 0 && check[map[nr][nc]])
				score++;
		}
		return (int) Math.pow(10, score - 1);
	}
}

public class Main {
	static int[][] classRoom;
	static ArrayList<Student>students = new ArrayList<Student>();
	static int N;
	// 상, 우, 하, 좌
	static int[] rowDir = { -1, 0, 1, 0 };
	static int[] colDir = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		classRoom = new int[N][N];

		// 00. 입력한다.
		for (int i = 0; i < 9; i++) {
			st = new StringTokenizer(br.readLine());
			Student s = new Student(classRoom, N, Integer.parseInt(st.nextToken()));
			s.check[Integer.parseInt(st.nextToken())] = true;
			s.check[Integer.parseInt(st.nextToken())] = true;
			s.check[Integer.parseInt(st.nextToken())] = true;
			s.check[Integer.parseInt(st.nextToken())] = true;
			students.add(s);
		}

		// 01. 0번째 학생은 1, 1 고정이다.
		students.get(0).sit(1, 1);

		// 02. 다음 1번째 학생부터 N번째 학생 까지 앉는다.
		int len = students.size();
		for (int i = 1; i < len; i++) {
			// 03. 현재 i번째 학생이 좋아하는 학생들을 표시한다.
			for (int j = 0; j < i; j++) {
				if (students.get(i).check[students.get(j).id])
					students.get(j).paint();
			}

			int min = 1;
			int minR = 0;
			int minC = 0;
			int isEmptyCount = 0;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					// 04. 우선순위가 크거나 같다면 일단 빈 자리를 계산한다.
					int newCount = 0;
					if (classRoom[r][c] <= min) {
						for (int dir = 0; dir < 4; dir++) {
							int nr = r + rowDir[dir];
							int nc = c + colDir[dir];
							if (nr < 0 || nr >= N || nc < 0 || nc >= N)
								continue;
							if (classRoom[nr][nc] <= 0)
								newCount++;
						}
					}
					// 05. 우선순위가 크다면 모두 현재 값으로 교체한다.
					if (classRoom[r][c] < min) {
						minR = r;
						minC = c;
						min = classRoom[r][c];
						isEmptyCount = newCount;
						continue;
					}
					// 06. 우선순위가 같으면 빈 자리 수를 비교해준다.
					if (classRoom[r][c] == min && newCount > isEmptyCount) {
						minR = r;
						minC = c;
						min = classRoom[r][c];
						isEmptyCount = newCount;
						continue;
					}
				}
			}
			students.get(i).sit(minR, minC);
			for (int j = 0; j < i; j++) {
				students.get(j).erase();
			}
		}
		int sum = 0;
		for (int i = 0; i < len; i++) {
			sum += students.get(i).evaluate();
		}
		System.out.println(sum);
	}
}

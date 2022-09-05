package A20220829_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

class House {
	int x, y;
	int range;
	public House(int x, int y, int range) {
		super();
		this.x = x;
		this.y = y;
		this.range = range;
	}
}

class Solution {
	static int[][] map = new int[30][30];
	static ArrayList<House> list = new ArrayList<>();
	static Set<Integer> set = new HashSet<>();
	static boolean[] isVisited;
	static int N;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			// 00. 테스트 케이스마다 초기화한다.
			list.clear();
			set.clear();
			for(int i = 0; i < 30; i++) Arrays.fill(map[i], 0);
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			isVisited = new boolean[N];
			
			// 01. 입력 받는다.(map에 범위로 표시하고, list에 집 좌표와 범위를, set에는 겹치는 좌표의 종류를 저장)
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int range = Integer.parseInt(st.nextToken());
				stamp(x+15, y+15, range, 1 << i);
				list.add(new House(x+15, y+15, range));
			}
			
			// 02. 만약  모든 좌표가 겹치는 점이 있다면, 그 부분 중 거리 최솟값인 좌표를 찾는다.
			boolean needAnother = true;
			int minValue = Integer.MAX_VALUE;
			for(int i = 0; i < 30; i++) {
				for(int j = 0; j < 30; j++) {
					if(map[i][j] == (1 << N) - 1) {
						needAnother = false;
						int distSum = 0;
						for(House house : list) {
							distSum += getDist(house.x, house.y, i, j);
						}
						minValue = Math.min(minValue, distSum);
					}
					set.add(map[i][j]);
				}
			}
			
			// 03. 만약 모두 겹치는 점이 없다면, 두 개의 충전소를 사용한다.
			boolean noAnswer = true;
			if(needAnother) {
			
				// 04. 첫번째 충전소를 설치한다.
				for(int i = 0; i < 30; i++) {
					for(int j = 0; j < 30; j++) {
						// 04-00. 현재 위치에 충전소를 설치하면 모두 커버가 가능할지 set으로 미리 판단한다. 
						if(map[i][j] == 0 || map[i][j] == -1) continue;
						boolean isPossible = false;
						for(int elem : set) {
							if((elem | map[i][j]) == (1 << N) - 1) {
								isPossible = true;
								break;
							}
						}
						if(!isPossible) continue;
						// 04-01. 현재 위치에 충전소를 설치했을 때, 혜택 받는 집들과의 거리를 구한다.
						int aSum = 0;
						int aNum = map[i][j];
						for(int d = 0; d < N; d++) {
							if(aNum % 2 == 1) {
								House house = list.get(d);
								aSum += getDist(house.x, house.y, i, j);
							}
							aNum /= 2;
						}
						
				// 05. 두번째 충전소를 설치한다.
						for(int p = i; p < 30; p++) {
							for(int q = j; q < 30; q++) {
								// 05-00. 만약, 두번째 충전소와 현재 충전소로 모두 커버 가능하다면 거리를 구한다.
								if(map[p][q] == -1) continue;
								if((map[p][q] | map[i][j]) == (1 << N) - 1) {
									noAnswer = false;
									int bSum = 0;
									int bNum = map[p][q];
									for(int d = 0; d < N; d++) {
										if(bNum % 2 == 1) {
											House house = list.get(d);
											bSum += getDist(house.x, house.y, p, q);
										}
										bNum /= 2;
									}
									// 05-01. 구한 거리가 최솟값인지 확인한다.
									minValue = Math.min(minValue, aSum + bSum);
								}
							}
						}
					}
				}
			}
			
			// 06. 답이 없었다면 결과는 -1이다.
			if(needAnother && noAnswer) minValue = -1;
			
			// 07. 결과를 출력한다.
			sb.append('#').append(tc).append(' ').append(minValue).append('\n');
		}
		System.out.println(sb.toString());
	}
	
	/* 거리를 반환하는 함수*/
	private static int getDist(int x1, int y1, int x2, int y2) {
		return Math.abs(x1-x2) + Math.abs(y1-y2);
	}
	
	/* 집 자리에는 -1, 그 주위로는 자신의 인덱스(비트마스킹)를 기록하는 함수*/
	private static void stamp(int r, int c, int range, int stamp) {
		for(int i = -range; i <= range; i++) {
			for(int j = -range; j <= range; j++) {
				if(Math.abs(i)+Math.abs(j) > range) continue;
				if(r+i < 0 || r+i >= 30 || c+j < 0 || c+j >= 30) continue;
				map[r+i][c+j] += stamp;
			}
		}
		map[r][c] = -1;
	}
}

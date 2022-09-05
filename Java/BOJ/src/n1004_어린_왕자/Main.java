package n1004_어린_왕자;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Planet {
	int x, y;
	int radius;
	boolean isVisited;
	
	public Planet(int x, int y, int radius) {
		super();
		this.x = x;
		this.y = y;
		this.radius = radius;
	}
}

public class Main {
	static ArrayList<Planet> list = new ArrayList<>();
	static int sx, sy, dx, dy;
	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			// 00. 시작점과 도착점 좌표를 입력 받는다.
			st = new StringTokenizer(br.readLine(), " ");
			sx = Integer.parseInt(st.nextToken());
			sy = Integer.parseInt(st.nextToken());
			dx = Integer.parseInt(st.nextToken());
			dy = Integer.parseInt(st.nextToken());
			
			// 01. 행성정보를 입력 받는다.
			list.clear();
			N = Integer.parseInt(br.readLine());
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int radius = Integer.parseInt(st.nextToken());
				list.add(new Planet(x, y, radius));
			}
			
			// 02. 반지름을 세어 결과를 출력한다.
			int result = count(sx, sy) + count(dx, dy);
			sb.append(result).append('\n');
		}
		System.out.println(sb.toString());
	}
	
	/* 통과해야하는 행성 개수를 세는 함수*/
	private static int count(int x, int y) {
		int count = 0;
		for(Planet planet : list) {
			int dist = 0;
			dist += (planet.x - x) * (planet.x - x);
			dist += (planet.y - y) * (planet.y - y);
			if(dist >= planet.radius * planet.radius) continue;
			if(planet.isVisited) {
				count--;
			} else {
				planet.isVisited = true;
				count++;
			}
		}
		return count;
	}
}
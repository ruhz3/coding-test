package n20056_마법사_상어와_파이어볼;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static List<Fireball> list = new LinkedList<>();
	static Map<Integer, Fireball> map = new HashMap<>();
	static int N, M, K;
	static int[] rowDir = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] colDir = {0, 1, 1, 1, 0, -1, -1, -1};
	
	static class Fireball {
		int r, c;
		int mass, dir, speed;
		int addCount;
		boolean dirCheck;
		
		public Fireball(int r, int c, int mass, int speed, int dir) {
			super();
			this.r = r;
			this.c = c;
			this.mass = mass;
			this.speed = speed;
			this.dir = dir;
			dirCheck = true;
		}
		public void move() {
			int d = speed % N;
			r += rowDir[dir] * d;
			c += colDir[dir] * d;
			if(r <= 0) r += N;
			else if(r > N) r %= N;
			if(c <= 0) c += N;
			else if(c > N) c %= N;
		}
		public void add(Fireball fb) {
			mass += fb.mass;
			speed += fb.speed;
			dirCheck = dirCheck && (dir + fb.dir)%2 == 0;
			addCount++;
		}
		public void cal() {
			mass /= 5;
			speed /= (addCount + 1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		// 00. 입력한다.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		// 01. Fireball을 List에 저장한다.
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			Fireball fb = new Fireball(r, c, m, s, d);
			list.add(fb);
		}
		
		// 02. K번 파이어볼들을 동작시킨다.
		while(K-- > 0) {
			// 02-00. 파이어볼을 이동시킨다.
			for(Fireball ball : list) {
				ball.move();
			}
			// 02-01. 맵에 배치하며 겹치는 놈들은 합쳐준다.
			for(int i = list.size()-1; i >= 0; i--) {
				Fireball ball = list.get(i);
				int key = ball.r * 100 + ball.c;
				Fireball fb = map.getOrDefault(key, null);
				if(fb != null) {
					fb.add(ball);
					list.remove(i);
				} else {
					map.put(key, ball);
				}
			}
			// 02-02. 합쳤을 때의 값들을 계산한다.
			for(int i = list.size()-1; i >= 0; i--) {
				Fireball ball = list.get(i);
				if(ball.addCount == 0) continue;
				ball.cal();
				if(ball.mass > 0) { 
					int start = ball.dirCheck ? 0 : 1;
					for(int dir = start; dir < 8; dir += 2) {
						list.add(new Fireball(
								ball.r, ball.c,
								ball.mass, ball.speed, dir));
					}
				}
				list.remove(i);
			}
			map.clear();
		}
		
		// 03. 남은 질량을 출력한다.
		int sum = 0;
		for(Fireball ball : list) {
			sum += ball.mass;
		}
		System.out.println(sum);
		
	}
}

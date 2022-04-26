package n17143_낚시왕;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Shark {
	int r, c;
	int speed;
	int dir;
	int size;
	public Shark(int r, int c, int speed, int dir, int size) {
		super();
		this.r = r;
		this.c = c;
		this.speed = speed;
		this.dir = dir;
		this.size = size;
	}
	@Override
	public String toString() {
		return "Shark [r=" + r + ", c=" + c + ", speed=" + speed + ", dir=" + dir + ", size=" + size + "]";
	}
	
}

public class Main {
	static LinkedList<Shark> sharks = new LinkedList<Shark>();
	static int R, C, M;
	static int[] rowDir = {0, -1, 1, 0, 0};
	static int[] colDir = {0, 0, 0, 1, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			sharks.add(new Shark(r, c, s, d, z));
		}
		for(Shark shark : sharks) {
			moveShark(shark);
		}
		
	}
	
	private static void moveShark(Shark shark) {
		int dir = shark.dir;
		int speed = shark.speed;
		
		if(dir == 1) {	// 상
			speed %= 2*(R-1);
			if(speed <= (shark.r-1)) shark.r -= speed;
			else if(speed <= shark.r-1 + R-1) {
				shark.r  = speed - (shark.r-1) + 1;
				shark.dir = 2;
			}
			else shark.r = R - (speed - (shark.r-1) - (R-1));
			return;
		}
		if(dir == 2) {  // 하
			speed %= 2*(R-1);
			if(speed <= R - shark.r) shark.r += speed;
			else if(speed <= R - shark.r + R-1) {
				shark.r = R - (speed - (R-shark.r));
				shark.dir = 1;
			}
			else shark.r = speed - (R-1) - (R - shark.r);
			return;
		}
		if(dir == 3) {  // 우
			speed %= 2*(C-1);
			if(speed <= C - shark.c) shark.c += speed;
			else if(speed <= C - shark.c + C-1) {
				shark.c = C - (speed - (C-shark.c));
				shark.dir = 4;
			}
			else shark.c = speed - (C-1) - (C - shark.c);
			return;
		}
		if(dir == 4) {  // 좌
			speed %= 2*(C-1);
			if(speed <= (shark.c-1)) shark.c -= speed;
			else if(speed <= shark.c-1 + C-1) {
				shark.c  = speed - (shark.c-1) + 1;
				shark.dir = 3;
			}
			else shark.c = C - (speed - (shark.c-1) - (C-1));
			return;
		}
	}
}

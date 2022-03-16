package n17070_파이프_옮기기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Pipe {
	int x, y;
	public Pipe(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	static int N;
	static int[][] map = new int[16][16];
	static int[][][] cache = new int[16][16][3];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		for(int i = 0; i < 16; i++) {
			for(int j = 0; j < 16; j++) {
				Arrays.fill(cache[i][j], -1);
			}
		}
		System.out.println(run(new Pipe(1, 0), new Pipe(0, 0), new Pipe(N-1, N-1)));
	}
	
	private static int run(Pipe head, Pipe tail, Pipe goal) {
		int type = 0;

		if (head.x != tail.x && head.y != tail.y) { type = 0;	// 대각선
		}
		else if (head.x != tail.x && head.y == tail.y) {
			type = 1;	// 가로
		}
		else if (head.x == tail.x && head.y != tail.y) {
			type = 2;	// 세로
		}
		
		int ret = cache[head.x][head.y][type];
		if (ret != -1) return ret;

		int result = 0;
		if (head.x == goal.x && head.y == goal.y) return 1;
		// 대각선인 경우
		if (type == 0) {			
			// 세로 이동
			if (map[head.y + 1][head.x] == 0 && head.y + 1 != N) {
				head.y++;
				tail.y++;
				tail.x++;
				result += run(head, tail, goal);
				head.y--;
				tail.y--;
				tail.x--;
			}
			// 가로 이동
			if (map[head.y][head.x + 1] == 0 && head.x + 1 != N) {
				head.x++;
				tail.x++;
				tail.y++;
				result += run(head, tail, goal);
				head.x--;
				tail.x--;
				tail.y--;
			}
			// 대각선 이동
			if (map[head.y + 1][head.x + 1] == 0 && map[head.y + 1][head.x] == 0 && map[head.y][head.x + 1] == 0 && head.x + 1 != N && head.y + 1 != N) {
				head.y++;
				tail.y++;
				head.x++;
				tail.x++;
				result += run(head, tail, goal);
				head.y--;
				tail.y--;
				head.x--;
				tail.x--;
			}
		}
		// 가로 모양인 경우
		else if (type == 1)
		{
			// 가로 이동
			if (map[head.y][head.x + 1] == 0 && head.x + 1 != N)
			{
				head.x++;
				tail.x++;
				result += run(head, tail, goal);
				head.x--;
				tail.x--;
			}
			// 대각선 이동
			if (map[head.y + 1][head.x + 1] == 0 && map[head.y + 1][head.x] == 0 && map[head.y][head.x + 1] == 0 && head.x + 1 != N && head.y + 1 != N)
			{
				head.y++; tail.x++; head.x++;
				result += run(head, tail, goal);
				head.y--; tail.x--; head.x--;
			}
		}
		//  세로 모양
		else if (type == 2) {
			// 세로 이동
			if (map[head.y + 1][head.x] == 0 && head.y + 1 != N)
			{
				head.y++;
				tail.y++;
				result += run(head, tail, goal);
				head.y--;
				tail.y--;
			}

			// 대각선 이동
			if (map[head.y + 1][head.x + 1] == 0 && map[head.y + 1][head.x] == 0 && map[head.y][head.x + 1] == 0 && head.x + 1 != N && head.y + 1 != N)
			{
				head.y++;
				head.x++;
				tail.y++;
				result += run(head, tail, goal);
				head.y--;
				head.x--;
				tail.y--;
			}
		}
		cache[head.x][head.y][type] = result;
		return result;
	}
}

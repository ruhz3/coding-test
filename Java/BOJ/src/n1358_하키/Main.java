package n1358_하키;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int width, height;
	static int x, y;
	static int radius;
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		width = Integer.parseInt(st.nextToken());
		height = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		radius = height/2;
		int count = 0;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int px = Integer.parseInt(st.nextToken());
			int py = Integer.parseInt(st.nextToken());
			if(check(px, py)) count++;
		}
		System.out.println(count);
	}
	private static boolean check(int p, int q) {
		if((x <= p && p <= x+width) && (y <= q && q <= y+height)) return true;
		if((x-p)*(x-p)+(y+radius-q)*(y+radius-q) <= radius * radius) return true;
		if((x+width-p)*(x+width-p)+(y+radius-q)*(y+radius-q) <= radius * radius) return true;
		return false;
	}
}

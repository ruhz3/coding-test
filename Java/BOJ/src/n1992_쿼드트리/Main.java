package n1992_쿼드트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static char[][] img;
	static int N;
	
	public static void main(String[] args) throws IOException{
		init();
		System.out.println(getQuadTree(0, 0, N, N));
	}
	private static void init() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		img = new char[N][];
		for(int i = 0; i < N; i++)
			img[i] = br.readLine().toCharArray();
	}
	private static String getQuadTree(int sr, int sc, int er, int ec) {
		if(check(sr, sc, er, ec)) return String.valueOf(img[sr][sc]);
		int hr = (sr + er)/2;
		int hc = (sc + ec)/2;
		StringBuilder sb = new StringBuilder();
		sb.append("(")
			.append(getQuadTree(sr, sc, hr, hc))
			.append(getQuadTree(sr, hc, hr, ec))
			.append(getQuadTree(hr, sc, er, hc))
			.append(getQuadTree(hr, hc, er, ec))
			.append(")");
		return sb.toString();
	}
	private static boolean check(int sr, int sc, int er, int ec) {
		char key = img[sr][sc];
		for(int r = sr; r < er; r++)
			for(int c = sc; c < ec; c++)
				if(img[r][c] != key) return false;
		return true;
	}
}

package n1074_Z;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		// 00. 초기값을 설정한다.
		int level = (int)Math.pow(2, N);
		int sr = 0;
		int sc = 0;
		int er = level-1;
		int ec = level-1;
		int num = 0;
		
		// 01. 분할정복한다.
		while(level > 0) {
			level /= 2;
			int rowMid = (sr + er) / 2;
			int colMid = (sc + ec) / 2;
			boolean rowFlag = (r <= rowMid);
			boolean colFlag = (c <= colMid);
			
			// 0번 영역
			if(rowFlag && colFlag) {
				er = rowMid;
				ec = colMid;
				num += 0 * level * level;
			}
			// 1번 영역
			else if(rowFlag && !colFlag) {
				sc = colMid+1;
				er = rowMid;
				num += 1 * level * level;
			}
			// 2번 영역
			else if(!rowFlag && colFlag) {
				sr = rowMid+1;
				ec = colMid;
				num += 2 * level * level;
			}
			// 3번 영역
			else {
				sr = rowMid+1;
				sc = colMid+1;
				num += 3 * level * level;
			}
		}
		System.out.println(num);
	}
}

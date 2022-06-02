package n2477_참외밭;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		int[][] input = new int[6][2];
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int K = Integer.parseInt(br.readLine());
		for(int i = 0; i < 6; i++) {
			st = new StringTokenizer(br.readLine());
			input[i][0] = Integer.parseInt(st.nextToken());
			input[i][1] = Integer.parseInt(st.nextToken());
		}
		
		int maxHeight = 0;
		int maxWidth = 0;
		int maxHeightindex = 0;
		int maxWidthindex = 0;
		for(int i = 0; i < 6; i++) {
			if(input[i][0] <= 2) {
				if(input[i][1] > maxWidth) {
					maxWidth = input[i][1];
					maxWidthindex = i;
				}
			} else {
				if(input[i][1] > maxHeight) {
					maxHeight = input[i][1];
					maxHeightindex = i;
				}
			}
		}
		int width = Math.abs(input[(maxHeightindex - 1 + 6) % 6][1] - input[(maxHeightindex + 1) % 6][1]);
		int height = Math.abs(input[(maxWidthindex - 1 + 6) % 6][1] - input[(maxWidthindex + 1) % 6][1]);
		System.out.println((maxHeight * maxWidth - width * height) * K);
	}
}

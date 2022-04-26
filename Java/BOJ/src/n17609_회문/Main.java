package n17609_회문;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static String[] strings;
	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		strings = new String[N];
		
		for(int i = 0; i < N; i++) {
			String string = br.readLine();
			int len = string.length();
			int count = len;
			int start = 0;
			int end = len - 1;
			int type = 0;
			
			while(start <= len/2 && len/2 <= end) {
				// 서로 다른 놈을 만났다면,
				if(string.charAt(start) != string.charAt(end)) {
					// 01. 왼쪽을 삭제하고 세어본다.
					boolean leftDeleted = true;
					for(int j = 0; j < (count - 1)/2; j++) {
						if(string.charAt(start + 1 + j) != string.charAt(end - j)) {
							leftDeleted = false;
							break;
						}
					}
					// 02. 오른쪽을 삭제하고 세어본다.
					boolean rightDeleted = true;
					for(int j = 0; j < (count - 1)/2; j++) {
						if(string.charAt(start + j) != string.charAt(end - 1 - j)) {
							rightDeleted = false;
							break;
						}
					}
					// 03. 어떤 문자열인지 판단하고 탈출한다.
					if(leftDeleted || rightDeleted) type = 1;
					else type = 2;
					break;
				}				
				count -= 2;
				start++;
				end--;
			}
			sb.append(type).append("\n");
		}
		System.out.println(sb.toString());
	}
}
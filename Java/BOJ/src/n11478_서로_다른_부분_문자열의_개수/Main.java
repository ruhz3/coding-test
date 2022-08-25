package n11478_서로_다른_부분_문자열의_개수;
// 564340KB, 988ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
	static Set<String> set = new HashSet<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		int len = input.length();
		for(int start = 0; start < len; start++) {
			for(int end = start; end < len; end++) {
				set.add(input.substring(start, end+1));		
			}
		}
		System.out.println(set.size());
	}
}

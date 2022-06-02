package n2493_탑;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Pair{
	int index;
	int height;
	public Pair(int index, int height) {
		this.index = index;
		this.height = height;
	}
}

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		LinkedList<Pair> stairs = new LinkedList<Pair>();
		
		StringBuilder result = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			// 00. 설치할 타워의 높이와 인덱스를 Pair로 만들어 넣는다.
			Pair p = new Pair(i, Integer.parseInt(st.nextToken()));
			boolean isTallest = true;
			while(!stairs.isEmpty()) {
				// 02. 나보다 크다면 벽의 인덱스를 출력하고, 자신의 인덱스를 기록한다.
				if(p.height < stairs.peek().height) {
					result.append(stairs.peek().index+1).append(" ");
					stairs.addFirst(p);
					isTallest = false;
					break;
				}
				// 03. 나보다 작다면 내 뒤에 가려지므로 삭제한다.
				else {
					stairs.poll();
				}
			}
			// 04. 내가 제일크다면 0을 출력한다.
			if(isTallest) {
				result.append(0).append(" ");
				stairs.addFirst(p);
			}
		}
		System.out.println(result.toString());
	}
}
 
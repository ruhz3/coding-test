package n1655_가운데를_말해요;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
	static PriorityQueue<Integer> leftPQ = new PriorityQueue<>(Comparator.reverseOrder());
	static PriorityQueue<Integer> rightPQ = new PriorityQueue<>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// 00. 첫번째는 무조건 중간값이다.
		int N = Integer.parseInt(br.readLine());
		int mid = Integer.parseInt(br.readLine());
		sb.append(mid).append('\n');
		
		// 01. 다음 원소부터는 중간값과 비교한다.
		for(int i = 1; i < N; i++) {
			
			// 01-00. 일단 중간값과 대소를 비교해 해당 우선순위 큐에 넣는다. 
			int n = Integer.parseInt(br.readLine());
			if(mid <= n) rightPQ.offer(n);
			else leftPQ.offer(n);
			
			// 01-01. 우선순위 큐 개수를 따져 중간값 조정이 필요하다면 꺼내 비교한다.
			int leftSize = leftPQ.size();
			int rightSize = rightPQ.size();
			if(rightSize > leftSize + 1) {
				leftPQ.offer(mid);
				mid = rightPQ.poll();
			} else if(leftSize > rightSize) {
				rightPQ.offer(mid);
				mid = leftPQ.poll();
			}
			
			// 01-02. 결과를 기록한다.
			sb.append(mid).append('\n');
		}
		
		// 02. 결과를 출력한다.
		System.out.println(sb.toString());
	}
}

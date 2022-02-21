package n1715;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	static PriorityQueue<Integer> pQueue;
    static int N;
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));        
        // 00. 입력한다.
        N = Integer.parseInt(br.readLine());
        pQueue = new PriorityQueue<Integer>();
        for (int i = 0; i < N; i++) {
        	pQueue.add(Integer.parseInt(br.readLine()));
        }
        // 01. 우선순위 큐, Greedy
        int sum = 0;
        while (pQueue.size() > 1) {
            int k = pQueue.poll() + pQueue.poll();
            sum += k;
            pQueue.add(k);
        }
        // 02. 출력한다.
        System.out.println(sum);
    }
}

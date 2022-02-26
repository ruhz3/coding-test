package n15787_기차가_어둠을_헤치고_은하수를;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
	static Integer[] trains;
	static int N, M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		trains = new Integer[N];
		for(int i = 0; i < N; i++) {
			trains[i] = 0;
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int command = Integer.parseInt(st.nextToken());
			int trainNum = Integer.parseInt(st.nextToken());
			int seatNum = 0;
			if(st.hasMoreTokens()) {
				seatNum = Integer.parseInt(st.nextToken());
			}
			action(command, trainNum, seatNum);
		}
		
		HashSet<Integer> set = new HashSet<Integer>();
		set.addAll(Arrays.asList(trains));
		System.out.println(set.size());
	}
	
	private static void action(int command, int trainNum, int seatNum) {
		switch(command) {
		case 1:
			getOn(command, trainNum-1, seatNum-1);
			break;
		case 2:
			getOff(command, trainNum-1, seatNum-1);
			break;
		case 3:
			moveBack(command, trainNum-1);
			break;
		case 4:
			moveFront(command, trainNum-1);
			break;
		default:
			break;
		}
		return;
	}
	private static void getOn(int command, int trainNum, int seatNum) {
		int d = 1 << seatNum;
		if((trains[trainNum] & d) != d) {
			trains[trainNum] += d;
		}
		return;
	}
	private static void getOff(int command, int trainNum, int seatNum) {
		int d = 1 << seatNum;
		if((trains[trainNum] & d) == d) {
			trains[trainNum] -= d;
		}
		return;
	}
	private static void moveBack(int command, int trainNum) {
		int limit = (1 << (20-1));
		if(trains[trainNum] >= limit) {
			trains[trainNum] -= limit;
		}
		trains[trainNum] = trains[trainNum] << 1;
		return;
	}
	private static void moveFront(int command, int trainNum) {
		trains[trainNum] = trains[trainNum] >> 1;
		return;
	}
}

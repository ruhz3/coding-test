package n14891_톱니바퀴;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Gear {
	LinkedList<Integer> list;
	Gear left;
	Gear right;
	
	public Gear(String s) {
		list = new LinkedList<Integer>();
		for(int i = 0, len = s.length(); i < len; i++)
			list.add(s.charAt(i) - '0');
	}
	public void rotate(int waveDir, int rotateDir) {
		// 양쪽 재귀 호출 
		if(left != null && waveDir != -1 && (left.getRight() != this.getLeft()))
			left.rotate(1, rotateDir * -1);
		if(right != null && waveDir != 1 && (right.getLeft() != this.getRight()))
			right.rotate(-1, rotateDir * -1);
		
		// 본인 회전
		if(rotateDir == 1) list.addFirst(list.pollLast());
		else list.addLast(list.pollFirst());
	}
	public int getLeft() {return list.get(6);}
	public int getRight() {return list.get(2);}
	public int getTop() {return list.get(0);}
}

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		Gear[] gears = new Gear[4];
		for(int i = 0; i < 4; i++) {
			gears[i] = new Gear(br.readLine());
		}
		
		for(int i = 0; i < 4; i++) {
			if(i > 0) gears[i-1].right = gears[i];
			if(i < 3) gears[i+1].left = gears[i];
		}
		
		int K = Integer.parseInt(br.readLine());
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int num = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			gears[num-1].rotate(0, dir);
		}
		
		int sum = 0;
		for(int i = 0; i < 4; i++) {
			sum += Math.pow(2, i) * gears[i].getTop();
		}
		System.out.println(sum);
	}
}

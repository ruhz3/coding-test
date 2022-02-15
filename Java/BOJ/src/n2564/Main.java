package n2564;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < 4; i++) {
			list.add(new ArrayList<Integer>());
		}
		// 00. 입력한다.
		st = new StringTokenizer(br.readLine(), " ");
		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		
		// 01. 리스트에 넣는다.
		int N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			list.get(Integer.parseInt(st.nextToken()) - 1).add(Integer.parseInt(st.nextToken()));
		}
		// 02. 동근이의 위치를 받는다.
		st = new StringTokenizer(br.readLine(), " ");
		int dir = Integer.parseInt(st.nextToken())-1;
		int coord = Integer.parseInt(st.nextToken()); 
		
		// 03. 동근이의 위치를 기준으로 리스트를 뒤진다.
		int sum = 0;
		for(int i = 0; i < 4; i++) {
			ArrayList<Integer> l = list.get(i);
			for(int elem : l) {
				if(dir == i) {
					sum += Math.abs(coord - elem);
				} else if((dir + i == 1) || (dir + i == 5) ) {
					if(i < 2) sum += H + Math.min(coord + elem, W-coord + W-elem);
					else sum += W + Math.min(coord + elem, H-coord + H-elem);
				} else {
					if(dir == 0) {
						if(i == 2) sum += coord + elem;
						else sum += coord + elem;
					} else if(dir == 1) {
						if(i == 2) sum += coord + H - elem;
						else sum += W - coord + H - elem;
					} else if(dir == 2) {
						if(i == 0) sum += coord + elem;
						else sum += H - coord + elem;
					} else {
						if(i == 0) sum += coord + W - elem;
						else sum += H - coord + W - elem;
					}
				}
			}
		}
		System.out.println(sum);
	}
}

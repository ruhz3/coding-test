package n10814_나이순_정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		HashMap<Integer, ArrayList<String>> hashMap = new HashMap<Integer, ArrayList<String>>();
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int age;
		String name;
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			age = Integer.parseInt(st.nextToken());
			name = st.nextToken();
			if(hashMap.containsKey(age)) {
				hashMap.get(age).add(name);
			} else {
				ArrayList<String> list = new ArrayList<String>();
				list.add(name);
				hashMap.put(age, list);
			}
		}
		Iterator<Integer> iter = hashMap.keySet().iterator();
		while(iter.hasNext()) {
			int key = iter.next();
			ArrayList<String> obj = hashMap.get(key);
			for(int i = 0; i < obj.size(); i++) {
				System.out.println(key + " " + obj.get(i));
			}
		}
	}
}

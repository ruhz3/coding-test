package n1991_트리_순회;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder pre = new StringBuilder();
	static StringBuilder in = new StringBuilder();
	static StringBuilder post = new StringBuilder();
	static int[][] tree;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		tree = new int[N][2];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int node = st.nextToken().charAt(0) - 'A';
			int left = st.nextToken().charAt(0) - 'A';
			int right = st.nextToken().charAt(0) - 'A';
			tree[node][0] = left == '.' - 'A'? 0 : left;
			tree[node][1] = right == '.' - 'A'? 0 : right;
		}
		preorder(0); // 0 = 'A'
		inorder(0);
		postorder(0);
		
		System.out.println(pre.toString());
		System.out.println(in.toString());
		System.out.println(post.toString());
	}
	private static void preorder(int idx) {
		char c = (char) ('A' + idx);
		pre.append(c);
		if(tree[idx][0] != 0) preorder(tree[idx][0]);
		if(tree[idx][1] != 0) preorder(tree[idx][1]);
	}
	private static void inorder(int idx) {
		char c = (char) ('A' + idx);
		if(tree[idx][0] != 0) inorder(tree[idx][0]);
		in.append(c);
		if(tree[idx][1] != 0) inorder(tree[idx][1]);
	}
	private static void postorder(int idx) {
		char c = (char) ('A' + idx);
		if(tree[idx][0] != 0) postorder(tree[idx][0]);
		if(tree[idx][1] != 0) postorder(tree[idx][1]);
		post.append(c);
	}
}

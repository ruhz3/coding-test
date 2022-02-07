package n5639;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Node {
	Node parent;
	Node leftChild;
	Node rightChild;
	int value;
	
	public Node(int value) {
		this.value = value;
	}
	
	public void add(Node n) {
		// 입력 값이 현재 노드 보다 작으면, 왼쪽 자식에게 물어보거나 없다면 자식삼자.
		if (n.value < value) {
			if(leftChild == null) {
				leftChild = n;
				n.parent = this;
			} else {
				leftChild.add(n);
			}
		// 입력 값이 현재 노드 보다 크면, 오른쪽 자식에게 물어보거나 없다면 자식삼자.
		} else {
			if(rightChild == null) {
				rightChild = n;
				n.parent = this;
			} else {
				rightChild.add(n);
			}
		}
	}
	public void print() {
		if(leftChild != null) {
			leftChild.print();
		}
		if(rightChild != null) {
			rightChild.print();
		}
		System.out.println(value);
	}
}

public class Main {
	static Node root;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder result = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] check = new int[1000001];
		// 00. 루트 노드를 먼저 만들어준다.
		root = new Node(Integer.parseInt(br.readLine()));
		
		// 01. 자식 노드를 생성하고 루트 노드에 붙인다. 
		while(true) {
			String input = br.readLine();
			if(input == null || input.equals(""))
				break;
			root.add(new Node(Integer.parseInt(input)));
		}
		// 02. 출력한다.
		root.print();
		
	}
}

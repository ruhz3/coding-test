package n17478;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static final String talk1 = "\"재귀함수가 뭔가요?\"";
	static final String talk2 = "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.";
	static final String talk3 = "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.";
	static final String talk4 = "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"";
	static final String answer= "\"재귀함수는 자기 자신을 호출하는 함수라네\"";
	static final String talk5= "라고 답변하였지.";
	static final String pre = "____";
	
	public static void chat(int count) {
		// 00. 머릿말 준비
		StringBuilder symbol = new StringBuilder();
		for(int i = 0; i < count; i++) {
			symbol.append(pre);
		}
		// 01. 출력
		System.out.println(symbol.toString() + talk1);
		if(count == N) {
			System.out.println(symbol.toString() + answer);
			System.out.println(symbol.toString() + talk5);
			return;
		}
		System.out.println(symbol.toString() + talk2);
		System.out.println(symbol.toString() + talk3);
		System.out.println(symbol.toString() + talk4);
		chat(count+1);
		System.out.println(symbol.toString() + talk5);
		return;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		System.out.println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.");
		chat(0);
	}
}

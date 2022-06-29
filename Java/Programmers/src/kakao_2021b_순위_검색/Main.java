package kakao_2021b_순위_검색;

import java.util.StringTokenizer;

public class Main {
	static String[] info = {
			"java backend junior pizza 150",
			"python frontend senior chicken 210",
			"python frontend senior chicken 150",
			"cpp backend senior pizza 260",
			"java backend junior chicken 80",
			"python backend senior chicken 50" };
	static String[] query = {
			"java and backend and junior and pizza 100",
			"python and frontend and senior and chicken 200",
			"cpp and - and senior and pizza 250",
			"- and backend and senior and - 150",
			"- and - and - and chicken 100",
			"- and - and - and - 150" };

	public static void main(String[] args) {
		int[] result = new Solution().solution(info, query);
		for(int num : result) {
			System.out.println(num + " ");
		}
	}
}
class Applicant {
	String lang;
	String position;
	String level;
	String food;
	int score;
	public Applicant(String lang, String position, String level, String food, String score) {
		super();
		this.lang = lang;
		this.position = position;
		this.level = level;
		this.food = food;
		this.score = Integer.parseInt(score);
	}
	public boolean check(String lang, String position, String level, String food, String score) {
		if(!lang.equals("-") && !lang.equals(this.lang)) return false;
		if(!position.equals("-") && !position.equals(this.position)) return false;
		if(!level.equals("-") && !level.equals(this.level)) return false;
		if(!food.equals("-") && !food.equals(this.food)) return false;
		if(!score.equals("-") && this.score < Integer.parseInt(score)) return false;
		return true;
	}
}
class Solution {
	public int[] solution(String[] info, String[] query) {
		Applicant[] list = new Applicant[info.length];
		int[] answer = new int[query.length];
		
		// 00. 지원자 정보를 Applicant의 List로 만들어준다.
		int infoIdx = 0;
		for(String text : info) {
			StringTokenizer st = new StringTokenizer(text);
			list[infoIdx++] = new Applicant(st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken());
		}
		// 01. 
		int queryIdx = 0;
		for(String text : query) {
			text = text.replace(" and ", " ");
			StringTokenizer st = new StringTokenizer(text);
			String lang = st.nextToken();
			String position = st.nextToken();
			String level = st.nextToken();
			String food = st.nextToken();
			String score = st.nextToken();
			int count = 0;
			for(Applicant applicant : list) {
				if(applicant.check(lang, position, level, food, score)) count++;
			}
			answer[queryIdx++] = count;
		}
		return answer;
	}
}
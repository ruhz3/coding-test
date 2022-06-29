package kakao_2021b_신규_아이디_추천;

public class Main {
	static String new_id = "=.=";

	public static void main(String[] args) {
		System.out.println(new Solution().solution(new_id));
	}
}

class Solution {
	public String solution(String new_id) {
		StringBuilder sb;
		String answer = new_id;
//		1단계 new_id의 모든 대문자를 대응되는 소문자로 치환합니다.
		answer = answer.toLowerCase();

//		2단계 new_id에서 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거합니다.
		sb = new StringBuilder();
		for (int i = 0, len = answer.length(); i < len; i++) {
			char c = answer.charAt(i);
			if (('a' <= c && c <= 'z') || ('0' <= c && c <= '9') || c == '-' || c == '_' || c == '.')
				sb.append(c);
		}
		answer = sb.toString();

//		3단계 new_id에서 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환합니다.
		sb = new StringBuilder();
		char prev = '*';
		for (int i = answer.length() - 1; i >= 0; i--) {
			char c = answer.charAt(i);
			if (c == '.' && prev == '.')
				continue;
			sb.insert(0, c);
			prev = c;
		}
		answer = sb.toString();

//		4단계 new_id에서 마침표(.)가 처음이나 끝에 위치한다면 제거합니다.
		int head = 0;
		int tail = answer.length() - 1;
		if (!answer.isEmpty()) {
			if (answer.charAt(0) == '.')
				head++;
			if (answer.charAt(tail) == '.')
				tail--;
		}
		if (head > tail)
			answer = "";
		else
			answer = answer.substring(head, tail + 1);

//		5단계 new_id가 빈 문자열이라면, new_id에 "a"를 대입합니다.
		if (answer.isEmpty())
			answer = "a";

//		6단계 new_id의 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거합니다.
//		     만약 제거 후 마침표(.)가 new_id의 끝에 위치한다면 끝에 위치한 마침표(.) 문자를 제거합니다.
		int limit = 15;
		if (answer.length() > limit) {
			if (answer.charAt(limit - 1) == '.')
				limit--;
			answer = answer.substring(0, limit);
		}

//		7단계 new_id의 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙입니다.
		int l = answer.length();
		if (l < 3) {
			sb = new StringBuilder().append(answer);
			char c = answer.charAt(l - 1);
			for (int i = 0; i < 3 - l; i++)
				sb.append(c);
			answer = sb.toString();
		}
		return answer;
	}
}
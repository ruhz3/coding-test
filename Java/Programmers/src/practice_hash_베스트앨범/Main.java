package practice_hash_베스트앨범;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
	static String[] genres = {"classic", "pop", "classic", "classic", "pop"};
	static int[] plays = {500, 600, 150, 800, 2500};
	public static void main(String[] args) {
		int[] result = new Solution().solution(genres, plays);
		for(int elem : result) {
			System.out.print(elem + " ");
		}
	}
}

class Solution {
	List<Genre> list = new ArrayList<>();
	Map<String, Integer> count = new HashMap<>();
	Map<String, Song[]> map = new HashMap<>();
	class Genre implements Comparable<Genre> {
		String name;
		@Override
		public int compareTo(Genre o) {
			return count.get(o.name) - count.get(name);
		}
	}
	class Song {
		int play;
		int id;
		public Song(int play, int id) {
			this.play = play;
			this.id = id;
		}
	}
	
	public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        int N = genres.length;
        
        for(int i = 0; i < N; i++) {
        	String genre = genres[i];
        	int play = plays[i];
        	count.put(genre, count.getOrDefault(genre, 0) + 1);
        	Song[] arr = map.getOrDefault(genre, new Song[2]);
        	if(arr[1].play < play) {
        		arr[0] = arr[1];
        		arr[1] = new Song(play, i);
        	} else if(arr[0].play < play) {
        		arr[0] = new Song(play, i);
        	}
        	map.put(genre, arr);
        }
        Collections.sort(list);
        for(int i = 0, len = list.size(); i < len; i++) {
        	
        }
        return answer;
    }
}
package weekly_최소직사각형;

class Solution {
    public int solution(int[][] sizes) {
        int maxWidth = 0;
        int maxHeight = 0;
        for(int[] size : sizes) {
        	maxWidth = Math.max(size[0] > size[1] ? size[0] : size[1], maxWidth);
        	maxHeight = Math.max(size[0] > size[1] ? size[1] : size[0], maxHeight);
        }
        return maxWidth * maxHeight;
    }
}
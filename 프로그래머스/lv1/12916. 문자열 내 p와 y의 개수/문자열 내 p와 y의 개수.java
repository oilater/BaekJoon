class Solution {
	boolean solution(String s) {
        s = s.toUpperCase();
		return s.chars().filter(v -> v == 'P').count() == s.chars().filter(v -> v == 'Y').count();
	}
}
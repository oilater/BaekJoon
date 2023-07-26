import java.util.*;
class Solution {
    public long solution(long n) {
        String answer = "";
		String[] arr = String.valueOf(n).split("");
		Arrays.sort(arr, Collections.reverseOrder());
		for (String str : arr) {
			answer += str;
		}
		return Long.parseLong(answer);
    }
}
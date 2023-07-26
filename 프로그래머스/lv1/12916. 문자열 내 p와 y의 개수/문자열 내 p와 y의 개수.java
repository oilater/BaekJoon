class Solution {
	boolean solution(String s) {
		s = s.toUpperCase();
		char[] arr = s.toCharArray();
		int pCount = 0, yCount = 0;
		for (char c : arr) {
			if (c == 'P')
				pCount++;
			if (c == 'Y')
				yCount++;
		}

		if (pCount == yCount)
			return true;
		else
			return false;
	}
}
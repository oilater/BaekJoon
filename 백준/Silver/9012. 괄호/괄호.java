import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			Stack<Character> stack = new Stack<>();
			char[] arr = br.readLine().toCharArray();
			for (int j = 0; j < arr.length; j++) {
				if (arr[j] == '(') {
					stack.push(arr[j]);
				}

				if (arr[j] == ')') {
					if (!stack.isEmpty() && stack.peek() == '(') {
						stack.pop();
					} else {
						stack.push(arr[j]);
					}
				}
			}

			if (stack.isEmpty()) {
				sb.append("YES").append('\n');
			} else {
				sb.append("NO").append('\n');
			}
		}

		System.out.println(sb);
	}
}
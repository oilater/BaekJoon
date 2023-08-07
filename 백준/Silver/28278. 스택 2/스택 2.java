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
		Stack<Integer> stack = new Stack<>();
		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int order = Integer.parseInt(st.nextToken());

			if (order == 1) {
				int X = Integer.parseInt(st.nextToken());
				stack.push(X);
			}

			if (order == 2) {
				if (!stack.isEmpty()) {
					sb.append(stack.pop()).append('\n');
				} else {
					sb.append(-1).append('\n');
				}
			}

			if (order == 3) {
				sb.append(stack.size()).append('\n');
			}

			if (order == 4) {
				if (stack.isEmpty()) {
					sb.append(1).append('\n');
				} else {
					sb.append(0).append('\n');
				}
			}

			if (order == 5) {
				if (!stack.isEmpty()) {
					sb.append(stack.peek()).append('\n');
				} else {
					sb.append(-1).append('\n');
				}
			}
		}
		System.out.println(sb);
	}
}
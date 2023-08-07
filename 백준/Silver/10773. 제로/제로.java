import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Integer> stack = new Stack<>();
		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int order = Integer.parseInt(st.nextToken());

			if (order != 0) {
				stack.push(order);
			} else {
				stack.pop();
			}

		}
		int sum = 0;
		for (Integer i : stack) {
			sum += i;
		}
		System.out.println(sum);
	}
}
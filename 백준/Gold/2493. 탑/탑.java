import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int[] arr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		arr = new int[N];
		Stack<int[]> stack = new Stack<>();
		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			int value = Integer.parseInt(st.nextToken());

			// 처음 해주는 작업
			if (stack.isEmpty()) {
				stack.add(new int[] { value, i + 1 });
			} else {

				// 자신보다 작거나 같은 높이의 탑은 전부 제거
				while (!stack.isEmpty() && stack.peek()[0] <= value) {
					stack.pop();
				}

				if (stack.isEmpty()) {
					arr[i] = 0;
				} else {
					arr[i] = stack.peek()[1];
				}

				stack.add(new int[] { value, i + 1 });

			}
		}
		
		for (int el : arr) {
			sb.append(el).append(' ');
		}
		System.out.println(sb);
		
	}
}
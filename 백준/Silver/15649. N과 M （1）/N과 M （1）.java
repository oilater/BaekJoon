import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] picked;
	static int[] arr;
	static boolean[] isSelected;
	static int N;
	static int M;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		isSelected = new boolean[N + 1];
		arr = new int[N + 1];
		picked = new int[M];
		for (int i = 1; i <= N; i++) {
			arr[i] = i;
		}
		permutation(0);
	}

	// idx 는 순서이면서 뽑는 개수
	static void permutation(int idx) {

		if (idx == M) {
			StringBuilder sb = new StringBuilder();
			for (int i : picked) {
				sb.append(i).append(" ");
			}
			System.out.println(sb.toString());
			return;
		}

		for (int i = 1; i <= N; i++) {
			if (isSelected[i])
				continue;

			picked[idx] = arr[i];
			isSelected[i] = true;
			permutation(idx + 1);
			isSelected[i] = false;

		}

	}
}
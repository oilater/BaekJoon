import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Main {
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int ans = 0;
		for (int i = 1; i <= N; i++) {
			String a = String.valueOf(i);
			String[] arr = a.split("");
			int sum = i;
			for (int j = 0; j < arr.length; j++) {
				sum += Integer.parseInt(arr[j]);
			}
			
			if(sum == N) {
				ans = i;
				break;
			}
		}
		System.out.println(ans);
	}
}

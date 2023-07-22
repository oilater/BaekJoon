import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		while (n != 1) {
			int cnt = 0;
			for (int i = 1; i <= n; i++) {
				if (n % i == 0)
					cnt++;
				if (cnt == 2) {
					n /= i;
					System.out.println(i);
					break;
				}
			}
		}

	}
}

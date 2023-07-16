import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		// 윗면
		for (int i = 1; i <= n; i++) {
			for (int j = i; j <= n - 1; j++) {
				System.out.print(" ");
			}
			for (int k = 1; k <= 2 * i - 1; k++) {
				System.out.print("*");
			}
			System.out.println();
		}
		// 아랫면
		for (int i = n-1; i >= 1; i--) {

			for (int j = n - 1; j >= i; j--) {
				System.out.print(" ");
			}
			
			for (int k = 2 * i - 1; k >= 1; k--) {
				System.out.print("*");
			}
			if(i != 1) System.out.println();
		}
	}
}
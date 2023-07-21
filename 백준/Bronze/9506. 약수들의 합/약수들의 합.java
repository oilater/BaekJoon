import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = 0;
		int sum = 0;
		while (n != -1) {
			int cnt = 0;
			n = Integer.parseInt(br.readLine());
			if(n == -1) break;
			int[] arr = new int[n];
			for (int i = 1; i < n; i++) {
				if (n % i == 0) {
					sum += i;
					arr[cnt++] = i;
				}
			}
			int[] arr2 = Arrays.copyOf(arr, cnt);
			if (sum == n) {
				System.out.print(n + " = ");
				for (int i = 0; i < arr2.length; i++) {
					if (i != arr2.length - 1) {
						System.out.print(arr2[i]);
						System.out.print(" + ");
					} else {
						System.out.print(arr2[i]);
						System.out.println();
					}
				}
			} else {
				System.out.println(n + " is NOT perfect.");
			}
			sum = 0;
		}

	}

}

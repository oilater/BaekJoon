import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		
		int[] inputs = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			int a = Integer.parseInt(st.nextToken());
			inputs[i] = a;
		}
		int ans = 0;
		for (int i = 0; i < inputs.length; i++) {
			int count = 0;
			for (int j = 1; j <= inputs[i]; j++) {
				if(inputs[i] % j == 0) count++;
			}
			if(count == 2) ans++;
		}
		
		System.out.println(ans);
	}
}
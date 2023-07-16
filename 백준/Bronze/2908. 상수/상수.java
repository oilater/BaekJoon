import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String[] a = st.nextToken().split("");
		String[] b = st.nextToken().split("");
		
		int max = 0;
		StringBuilder sb = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		for (int i = 2; i >= 0; i--) {
			sb.append(a[i]);
			sb2.append(b[i]);
		}
		System.out.println(Integer.max(Integer.parseInt(sb.toString()), Integer.parseInt(sb2.toString())));
	}
}
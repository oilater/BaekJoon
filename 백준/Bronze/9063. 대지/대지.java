import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int[] w = new int[n];
		int[] h = new int[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			w[i] = Integer.parseInt(st.nextToken());
			h[i] = Integer.parseInt(st.nextToken());
		}
		// 가로 최소값 최대값 구하기
		int wMin = Integer.MAX_VALUE;
		int wMax = Integer.MIN_VALUE;
		int hMin = Integer.MAX_VALUE;
		int hMax = Integer.MIN_VALUE;
		for (int i = 0; i < w.length; i++) {
			if(w[i] < wMin) wMin = w[i];
			if(w[i] > wMax) wMax = w[i];
			if(h[i] < hMin) hMin = h[i];
			if(h[i] > hMax) hMax = h[i];
		}
		if(n == 1) System.out.println(0);
		else System.out.println((wMax - wMin) * (hMax - hMin));
	}
}
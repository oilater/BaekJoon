import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int e = Integer.parseInt(st.nextToken());
		int f = Integer.parseInt(st.nextToken());
	
		int X = 0, Y = 0;
		if(a == c) {
			X = e;
		} else if(c == e) {
			X = a;
		} else if(a == e) {
			X = c;
		}
		
		if(b == d) {
			Y = f;
		} else if(b == f) {
			Y = d;
		} else if(d == f) {
			Y = b;
		}
		System.out.println(X + " " + Y);
	}
}
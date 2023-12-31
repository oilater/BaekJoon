import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static boolean[] visited;
	static ArrayList<Integer>[] A;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		A = new ArrayList[n+1];
		visited = new boolean[n+1];
		
		for (int i = 1; i <= n; i++) {
			A[i] = new ArrayList<Integer>();
		}
	
		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			A[s].add(e);
			A[e].add(s);
		}
		
		int cnt = 0;
		for (int i = 1; i <= n; i++) {
			if(!visited[i]) {
				cnt++;
				DFS(i);
			}
		}
		System.out.println(cnt);
	}
	
	static void DFS(int v) {
		if(visited[v]) {
			return;
		}
		
		visited[v] = true;
		for (int i : A[v]) {
			if(visited[i] == false) {
				DFS(i);
			}
		}
	}
}

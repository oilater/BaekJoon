import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static ArrayList<Integer>[] adjList;
    static boolean[] visited;
    static int[] parents;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        adjList = new ArrayList[N+1];
        visited = new boolean[N+1];
        parents = new int[N + 1];
        for (int i = 1; i < N+1; i++) {
            adjList[i] = new ArrayList<>();
        }

        StringTokenizer st = null;
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adjList[a].add(b);
            adjList[b].add(a);
        }

        dfs(1);

        for (int i = 2; i <= N; i++) {
            sb.append(parents[i]).append('\n');
        }

        System.out.println(sb);
    }

    private static void dfs(int p) {
        visited[p] = true;

        for (int s : adjList[p]) {
            if (!visited[s]) {
                parents[s] = p;
                dfs(s);
            }
        }
    }
}
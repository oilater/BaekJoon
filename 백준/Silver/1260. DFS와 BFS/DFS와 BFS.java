import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, V;
    static List<Integer>[] graph;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
        visited = new boolean[N + 1];
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) { // 그래프 생성
            graph[i] = new ArrayList<>(); // 정점은 인덱스 번호다.
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        // 정점 번호가 작은 것부터 방문해야 하므로 정렬해준다.
        for (int i = 1; i <= N; i++) {
            Collections.sort(graph[i]);
        }

        dfs(V);
        sb.append('\n');
        Arrays.fill(visited, false);
        bfs(V);

        System.out.println(sb); // 출력
    }

    private static void dfs(int start) {
        visited[start] = true;
        sb.append(start).append(' ');
        for (int el : graph[start]) {
            if(!visited[el]) dfs(el);
        }
    }

    private static void bfs(int start) {
        Queue<Integer> q = new ArrayDeque<>();

        visited[start] = true;
        q.offer(start);
        while(!q.isEmpty()) {
            int el = q.poll();
            sb.append(el).append(' ');
            for (int next : graph[el]) {
                if(!visited[next]) {
                    visited[next] = true;
                    q.offer(next);
                }
            }
        }
    }
}
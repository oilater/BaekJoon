import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, R;
    static List<Integer>[] arr;
    static int[] visited;
    static int order = 1;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        arr = new ArrayList[N+1];
        visited = new int[N+1];
        for (int i = 1; i < N+1; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            arr[from].add(to);
            arr[to].add(from);
        }

        for (int i = 1; i <= N; i++) {
            Collections.sort(arr[i]);
        }

        bfs(R);

        for (int i = 1; i <= N; i++) {
            sb.append(visited[i]).append('\n');
        }

        System.out.println(sb); // print
    }

    private static void bfs(int n) {
        Queue<Integer> que = new ArrayDeque<>();
        visited[n] = order++;
        que.add(n);



        while (!que.isEmpty()) {
            int start = que.poll();
            for (int to : arr[start]) {
                if (visited[to] == 0) {
                    que.add(to);
                    visited[to] = order++;
                }
            }
        }
    }

}
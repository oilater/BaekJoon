import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M, R;
    static int cnt = 1;
    static int[] visited;
    static ArrayList<Integer>[] adjList;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[N+1];
        visited = new int[N+1];

        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adjList[a].add(b);
            adjList[b].add(a);
        }

        // 정렬
        for (int i = 1; i <= N; i++) {
            Collections.sort(adjList[i], Collections.reverseOrder());
        }

        dfs(R);

        for (int i = 1; i <= N; i++) {
            sb.append(visited[i]).append('\n');
        }
        
        System.out.println(sb);
    }

    private static void dfs(int i) {
        visited[i] = cnt++;
        for (int num: adjList[i]) {
            if (visited[num] == 0) {
                dfs(num);
            }
        }
        }
    }
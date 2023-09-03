import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static List<Integer>[] adjList;
    static boolean[] visited;
    static int welcome;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        visited = new boolean[N+1];
        adjList = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) { // 리스트 초기화
            adjList[i] = new ArrayList<>();
        }
        StringTokenizer st;
        for (int i = 0; i < M; i++) { // 친구관계 리스트화
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adjList[a].add(b);
            adjList[b].add(a);
        }

        bfs();

    }

    private static void bfs() {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(1);
        visited[1] = true;

        int level = 0;
        while (level != 2) {
            int size = q.size();
            while(size-- > 0) {
                int p = q.poll();
                for (int el : adjList[p]) {
                    if(!visited[el]) {
                        q.add(el);
                        visited[el] = true;
                    }
                }
            }
            level++;
        }

        for (int i = 2; i <= N; i++) {
            if(visited[i]) welcome++;
        }
        System.out.println(welcome);
    }
}
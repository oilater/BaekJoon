import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static class Node {
        int r, c;
        public Node (int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int T, N;
    static int[][] map;
    static int[][] sumArr;
    static int ans;
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        while (true) {
            T++;
            N = Integer.parseInt(br.readLine());
            if (N == 0) break;
            // 맵 입력 받기
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // sumArr 최댓값으로 초기화 (단, 시작점은 0으로 초기화)
            sumArr = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (i == 0 && j == 0) continue;
                    sumArr[i][j] = 987_654_321;
                }
            }

            sumArr[0][0] = map[0][0]; // sumArr 시작점 초기화

            bfs();
            sb.append("Problem").append(' ').append(T).append(":").append(' ').append(ans).append('\n');
        }
        System.out.println(sb);
    }

    private static void bfs() {
        Queue<Node> que = new ArrayDeque<>(); // 큐 생성

        que.add(new Node(0, 0)); // 시작점 넣기

        while (!que.isEmpty()) {
            Node node = que.poll(); // 꺼내기
            int r = node.r;
            int c = node.c;

            if (r == N-1 && c == N-1) {
                ans = sumArr[N-1][N-1];
            }

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (!isInRange(nr, nc)) continue; // 범위 벗어나면 continue;
                if (sumArr[nr][nc] <= sumArr[r][c] + map[nr][nc]) continue;
                sumArr[nr][nc] = sumArr[r][c] + map[nr][nc];
                que.add(new Node(nr, nc));
            }
        }
    }

    private static boolean isInRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

}
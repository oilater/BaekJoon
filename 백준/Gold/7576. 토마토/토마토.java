import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static int N, M;
    static int[][] arr;

    static class Node {
        int r, c;
        public Node (int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static Queue<Node> que = new ArrayDeque<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int input = Integer.parseInt(st.nextToken());
                if (input == 1) que.add(new Node(i, j));
                arr[i][j] = input;
            }
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] != 0) cnt++;
            }
        }

        if (cnt == N * M) {
            System.out.println(0);
            return;
        }

        System.out.println(bfs());
    }
    private static int bfs() {
        while (!que.isEmpty()) {
            Node n = que.poll();
            for (int d = 0; d < 4; d++) {
                int nr = n.r + dr[d];
                int nc = n.c + dc[d];
                if (isInRange(nr, nc) && arr[nr][nc] == 0) {
                    arr[nr][nc] = arr[n.r][n.c] + 1;
                    que.add(new Node(nr, nc));
                }
            }
        }

        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 0) {
                    return -1;
                }
                ans = Math.max(ans, arr[i][j]);

            }
        }

        return ans - 1;
    }

    private static boolean isInRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }
}
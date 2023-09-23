import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static int N, M, K; // 세로, 가로, 배추 심어진 위치 개수
    static int[][] arr; // 배추밭
    static boolean[][] visited;

    static int cnt;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            arr = new int[N][M];
            visited = new boolean[N][M];
            cnt = 0; // 테케마다 카운트 값 초기화
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int c = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                arr[r][c] = 1;
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (!visited[i][j] && arr[i][j] == 1) {
                        cnt++;
                        dfs(i, j);
                    }
                }
            }
            sb.append(cnt).append('\n');
        }
        System.out.println(sb);
    }

    private static void dfs(int i, int j) {
        visited[i][j] = true;

        for (int d = 0; d < 4; d++) {
            int ni = i + dr[d];
            int nj = j + dc[d];
            if (ni >= 0 && ni < N && nj >= 0 && nj < M && !visited[ni][nj] && arr[ni][nj] == 1) {
                dfs(ni, nj);
            }
        }
    }
}
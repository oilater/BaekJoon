import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] dr = {1, 0};
    static int[] dc = {0, 1};

    static int N;
    static int[][] arr;
    static boolean[][] visited;

    static String answer = "Hing";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0);
        System.out.println(answer);
    }

    private static void dfs(int r, int c) {
        visited[r][c] = true;

        if (arr[r][c] == -1) {
            answer = "HaruHaru";
            return;
        }

        for (int d = 0; d < 2; d++) {
            int nr = r + dr[d] * arr[r][c];
            int nc = c + dc[d] * arr[r][c];
            if (isInRange(nr, nc) && !visited[nr][nc]) {
                dfs(nr, nc);
            }

        }

        // 오른쪽이나 아래로만 갈 수 있다.

    }

    private static boolean isInRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}
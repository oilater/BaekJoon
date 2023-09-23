import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1 ,1};

    static int M, N;
    static char[][] arr;
    static boolean[][] visited;
    static boolean answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new char[N][M];
        visited = new boolean[N][M];
        // 배열 입력 받기
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = str.charAt(j) == '0' ? '1' : '0';
            }
        }

        // 꼭대기에서 아래까지 탐색 - 도달하면 YES, 도달 못하면 NO
        for (int i = 0; i < M; i++) {
            if(arr[0][i] == '1') dfs(0, i);

        }

        if (answer) System.out.println("YES");
        else System.out.println("NO");
    }

    private static void dfs (int r, int c) {
        visited[r][c] = true;

        if (r == N-1) {
            answer=  true;
            return;
        }

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (isInRange(nr, nc) && arr[nr][nc] == '1' && !visited[nr][nc]) {
                dfs(nr, nc);

            }
        }
    }

    private static boolean isInRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }
}
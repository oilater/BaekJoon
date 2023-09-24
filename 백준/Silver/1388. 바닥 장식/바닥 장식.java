import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static int N, M;
    static char[][] arr;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new char[N+2][M+2];

        for (int i = 1; i <= N; i++) {
            String str = br.readLine();
            for (int j = 1; j <= M; j++) {
                arr[i][j] = str.charAt(j-1);
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (arr[i][j] == '|') {
                    dfs(i, j, '|');
                    ans++;
                } else if (arr[i][j] == '-'){
                    dfs(i, j, '-');
                    ans++;
                }
            }

        }
        System.out.println(ans);
    }

    private static void dfs(int r, int c, char shape) {

        int start = 0;
        int end = 0;

        if (shape == '|') {
            start = 0;
            end = 1;
        } else if (shape == '-'){
            start = 2;
            end = 3;
        }

        arr[r][c] = '1';

        for (int d = start; d <= end; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if(arr[nr][nc] == shape) {
                arr[nr][nc] = '1';
                dfs(nr, nc, shape);
            }
        }
    }
}
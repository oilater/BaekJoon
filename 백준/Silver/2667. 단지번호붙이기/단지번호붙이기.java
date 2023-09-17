import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    static int N;
    static boolean[][] visited;
    static char[][] arr;
    static List<Integer> homeList = new ArrayList<>();
    static int executeCnt;
    static int oneCnt = 1;
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new char[N][N];
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            char[] tmp = br.readLine().toCharArray();
            arr[i] = tmp;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == '1' && !visited[i][j]) {
                    executeCnt++;
                    dfs(i, j);
                    homeList.add(oneCnt);
                }
                oneCnt = 1;
            }
        }
        Collections.sort(homeList);
        sb.append(executeCnt).append('\n');
        for (int i = 0; i < homeList.size(); i++) {
            sb.append(homeList.get(i)).append('\n');
        }
        System.out.println(sb); // 출력
    }

    private static void dfs(int r, int c) {
        visited[r][c] = true;



        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (isInRange(nr, nc) && arr[nr][nc] == '1' && !visited[nr][nc]) {
                oneCnt++;
                dfs(nr, nc);
            }
        }
    }

    private static boolean isInRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}
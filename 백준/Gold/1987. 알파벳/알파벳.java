import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 알파벳 사용했는지 기억해야 한다.
 * 이런 경우 char 배열로 만들면 접근이 너무 복잡해짐
 * boolean으로 하자
 *
 */
public class Main {
    // 상하좌우 4방향 델타
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static int R, C;
    static char[][] arr;
    static boolean[] visited = new boolean[26];

    static int max; // 지나간 최대 칸 수 - 정답값

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new char[R][]; // 배열 생성 및 입력
        for (int i = 0; i < R; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        // (0,0)부터 방문 체크 후 dfs를 돌리자
        visited[arr[0][0] - 'A'] = true; // 첫 좌표 방문체크
        dfs(0, 0, 1); // dfs

        System.out.println(max); // 출력
    }

    private static void dfs(int r, int c, int depth) {
        max = Math.max(max, depth); // max 값 업데이트

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
            if (visited[arr[nr][nc] - 'A']) continue;

            visited[arr[nr][nc] - 'A'] = true;
            dfs(nr, nc, depth + 1);
            visited[arr[nr][nc] - 'A'] = false;
        }

    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 시작 시간: 2:18
 * 종료 시간:
 * 풀이 시간:
 *
 * 문제
 * 19 * 19
 * 검은 바둑알 1
 * 흰색 바둑알 2
 * 빈칸 0
 * 육목 이상은 불가능
 * 한 쪽이 이긴 경우 이긴 알(1 or 2)의 번호와 좌상의 바둑알의 r, c 좌표 출력
 * 승부가 아직 안났다면 0 출력
 *
 * 생각나는 풀이
 * (1,1)부터 차례대로 돌며 dfs 검사
 * 검사해야 할 방향 : 우, 하, 우상, 우하 만 검사하면 된다. (가지치기)
 * 현재 돌과 같은 돌이 인접했다면 재귀 호출
 * count == 5 가 되면 지금까지 검사한 델타 방향으로 한 칸 더 검사
 * dir을 어떻게 기억해야 하지? static 변수 dir을 만들어놓자
 *
 */
public class Main {
    // 4방 델타 하 우 우상 우하
    static int[] dr = {1, 0, -1, 1};
    static int[] dc = {0, 1, 1, 1};

    static int[][] map = new int[21][21]; // 패딩 처리 -> 범위 체크 불필요
    static boolean[][] visited = new boolean[21][21];
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        for (int i = 1; i <= 19; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 19; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= 19; i++) {
            for (int j = 1; j <= 19; j++) {
                visited[i][j] = true; // 현재 점 방문 체크
                if (map[i][j] != 0) {
                    dfs(i, j, 1, 0);
                    dfs(i, j, 1, 1);
                    dfs(i, j, 1, 2);
                    dfs(i, j, 1, 3);
                }
                visited[i][j] = false;
            }
        }
        System.out.println(0);
    }

    /**
     * @param r : 현재 행
     * @param c : 현재 열
     * @param cnt : 지금까지 검사한 돌의 개수
     */
    private static void dfs(int r, int c, int cnt, int dir) {
        if (cnt == 5) {
            // 지금까지의 방향으로 한 칸 더 검사
            int nr = r + dr[dir];
            int nc = c + dc[dir];
            if (map[r][c] == map[nr][nc]) return; // 다음 검사할 돌도 같다면 육목이므로 return
            if (map[r][c] == map[r - dr[dir] * 5][c- dc[dir] * 5]) return;
            // 검사 통과했다면 출력 후 종료

            sb.append(map[r][c]).append('\n').append(r - dr[dir]*4).append(' ').append(c-dc[dir]*4);
            System.out.println(sb);
            System.exit(0);
        }


        int nr = r + dr[dir];
        int nc = c + dc[dir];
        if (isInRange(nr, nc) && !visited[nr][nc] && map[r][c] == map[nr][nc]) {
            visited[nr][nc] = true;
            dfs(nr, nc, cnt + 1, dir);
            visited[nr][nc] = false;
        }
    }

    private static boolean isInRange(int r, int c) {
        return r >= 1 && r <= 19 && c >= 1 && c <= 19;
    }
}
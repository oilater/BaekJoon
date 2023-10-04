import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 출발지 S 부터 도착지 G까지 가기 위해 도로 복구 작업 해야 함
 * 도로 파여진 깊이에 비례하여 복구 시간 증가
 * 복구 시간이 가장 짧은 경로에 대한 총 복구 시간 구하기
 * => 그래프에 그리면서 가는 게 좋을듯?
 * bfs로 풀기 => 최단 시간 보장 => 각 칸의 숫자만큼 더하기
 *
 * arr: 최대 100 x 100
 */
public class Solution {
    static class Node {
        int r, c;
        public Node (int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int N;
    static int[][] map;
    static int[][] sumArr;
    static int ans; // 정답값 : 총 복구 시간이 가장 작은 경로의 복구 시간
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            ans = 987_654_321; // 테케별 정답값 초기화

            N = Integer.parseInt(br.readLine());
            // 맵 입력
            map = new int[N][N];
            sumArr = new int[N][N];

            for (int i = 0; i < N; i++) {
                String str = br.readLine();
                for (int j = 0; j < N; j++) {
                    map[i][j] = str.charAt(j) - '0';
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (i == 0 && j == 0) continue;
                    sumArr[i][j] = 987_654_321;
                }
            }

            bfs();
            sb.append("#").append(tc).append(' ').append(ans).append('\n');
        }
        System.out.println(sb);

    }

    private static void bfs() {
        Queue<Node> que = new ArrayDeque<>();
        que.add(new Node (0, 0));

        while (!que.isEmpty()) {
            Node node = que.poll();
            int r = node.r;
            int c = node.c;

            if (r == N-1 && c == N-1) {
                ans = Math.min(ans, sumArr[r][c]);
                continue;
            }

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (!isInRange(nr, nc)) continue;
                // 지금 가려는 값이 더 작다면 업데이트, 큐에 추가
                if (sumArr[nr][nc] > sumArr[r][c] + map[nr][nc]) {
                    sumArr[nr][nc] = sumArr[r][c] + map[nr][nc];
                    que.add(new Node(nr, nc));
                }

            }
        }
    }

    private static boolean isInRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}
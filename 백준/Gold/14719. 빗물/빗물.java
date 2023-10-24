import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 생각나는 풀이
 * 맵을 거꾸로 생각하자
 * 0~W-1돌면서 입력값만큼 1로 바꿔주기
 *
 * 처음부터 for문 돌면서 빈칸을 찾으면 좌, 우에 벽이 있는지 검사 (끝까지 가보기)
 * 좌우에 벽이 있다면 ans++
 */
public class Main {
    static int H, W;
    static int[][] map;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        map = new int[H][W];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) {
            int input = Integer.parseInt(st.nextToken());
            for (int j = 0; j < input; j++) {
                map[j][i] = 1;
            }
        } // 맵 입력 끝

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (map[i][j] == 0) {
                    checkSide(i, j); // 좌우 검사
                }
            }
        }

        System.out.println(ans); // 출력
    }

    private static void checkSide(int r, int c) {
        // 좌 검사
        for (int d = 1; d <= W; d++) {
            int nc = c - d;
            if(isOutOfRange(nc)) return; // 범위 벗어나면 return
            if (map[r][nc] == 1) break;
        }

        // 우 검사
        for (int d = 1; d <= W; d++) {
            int nc = c + d;
            if(isOutOfRange(nc)) return;
            if (map[r][nc] == 1) break;
        }

        // 여기까지 왔으면 빗물 고일 수 있음
        ans++; // 고인물 + 1
        map[r][c] = 1; // 빗물 고이기
    }

    private static boolean isOutOfRange(int c) {
        return c < 0 || c >= W;
    }
}
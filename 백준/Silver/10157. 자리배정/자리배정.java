import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제 해석
 * C * R 공연장
 * 각각의 좌석은 x, y
 * 왼쪽 아래부터 시계방향으로 쭉 돌아간다.
 * 상 -> 우 -> 하 -> 좌   (반복)
 * 빈좌석이 없으면 회전해서 옆으로 간다.
 *
 * 입력
 * 첫줄 : C   R
 *
 *
 * 범위
 * 5 <= C, R <= 1000
 * 1 <= K <= 100,000,000
 *
 * 생각 나는 풀이
 * 델타를 선언하자 : 상 우 하 좌 순으로
 * 범위를 벗어나거나 다음 숫자가 0이 아니라면 방향을 바꿔서 계속 진행
 * => 방향 변수 dir 을 만들어 델타에 이용하자
 *
 * K 는 바로 O(1)로 접근하도록 해야 한다.
 * 호출하면 바로 그 좌석의 x, y 좌표가 나와야 하기 때문에 각 좌표의 x,y 값을 저장할 클래스를 만들어놓자.
 */
public class Main {
    static int[][] arr;
    static int C, R, K;

    // 방향 델타 (상 우 하 좌 순서)
    static int[][] deltas = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static int nowDir = 0; // 현재 방향

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        arr = new int[R][C];
        K = Integer.parseInt(br.readLine()); // 어떤 관객의 대기번호 K

        if(K > C * R) {
            System.out.println(0);
            return;
        }

        findSeat(0, 0, 1);


    }

    private static void findSeat(int r, int c, int cnt) {
        arr[r][c] = cnt;

        if(cnt == K) {
            System.out.printf("%d %d", c + 1, r + 1);
            return;
        }

        int[] delta = deltas[nowDir];
        int nextR = r + delta[0];
        int nextC = c + delta[1];

        if(isInRange(nextR, nextC) && arr[nextR][nextC] == 0) {
            findSeat(nextR, nextC, cnt+1);
        } else {
            nowDir = (nowDir + 1) % 4;
            findSeat(r, c, cnt);
        }
    }

    private static boolean isInRange(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }
}
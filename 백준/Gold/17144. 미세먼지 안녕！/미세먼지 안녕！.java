import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    // 위쪽 공기청정기의 방향 델타 (당기기)
    static int[] drUp = {-1, 0, 1, 0};
    // 아랫쪽 공기청정기의 방향 델타 (당기기)
    static int[] drDn = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};

    static int[][] room; // R * C 크기의 방
    static int[][] spreadInfo; // 확산 정보 담아줄 배열

    static int cleanerUp_r = -1, cleanerDn_r = -1; // 위 아래 공기청정기의 행 좌표 값 (열좌표값은 항상 0)
    static int R, C, T; // 행 크기, 열 크기, T초

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        room = new int[R][C];
        spreadInfo = new int[R][C];

        // 방 입력
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                int input = Integer.parseInt(st.nextToken());
                room[i][j] = input;
                // 공기청정기 행 값 담아주기
                if (input == -1 && cleanerUp_r == -1) cleanerUp_r = i;
                else if (input == -1 && cleanerUp_r != -1) cleanerDn_r = i;
            }
        }

        // T초 동안 다음의 작업 반복
        for (int time = 0; time < T; time++) {
            spread(); // step 01. 미세먼지 확산
            rotate(); // step 02. 공기 순환
        }

        // 총합 구해서 출력
        System.out.println(getDustSum());
    }

    // 확산 메서드
    private static void spread() {
        spreadInfo = new int[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (room[i][j] > 0) {
                    getSpreadInfo(i, j); // 미세먼지 확산 정보 배열 업데이트 (확산)
                }
            }
        }

        // 미세먼지 확산 정보 적용
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                room[i][j] += spreadInfo[i][j];
            }
        }
    }

    private static void getSpreadInfo(int r, int c) {
        int spreadCnt = 0; // 확산한 방향
        int val = room[r][c] / 5; // 확산될 양
        for (int d = 0; d < 4; d++) {
            int nr = r + drUp[d]; // drUp, drDn 중 아무거나 써도 상관 x
            int nc = c + dc[d];
            if (isOutOfRange(nr, nc) || room[nr][nc] == -1) continue;
            spreadInfo[nr][nc] += val;
            spreadCnt++;
        }
        spreadInfo[r][c] -= val * spreadCnt;
    }

    private static int getDustSum() {
        int dustSum = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (room[i][j] > 0) dustSum += room[i][j];
            }
        }
        return dustSum;
    }

    private static void rotate() {
        rotateUp(); // 위쪽 공기청정기 순환
        rotateDown(); // 아래쪽 공기청정기 순환
    }

    private static void rotateUp() {
        int dir = 0;
        // 시작점 좌표
        int r = cleanerUp_r-1;
        int c = 0;
        while (dir < 4) {
            int nr = r + drUp[dir];
            int nc = c + dc[dir];
            // 경계 내에 있다면?
            if (nr <= cleanerUp_r && nr >= 0 && nc < C) {
                // 다음 위치가 공기 청정기라면?
                if (room[nr][nc] == -1) {
                    room[r][c] = 0;
                    break;
                }
                // 다음값 당겨오기
                room[r][c] = room[nr][nc];
                // 다음 위치로 이동
                r = nr;
                c = nc;
            }
            // 경계를 벗어난다면? 다음 방향
            else dir++;
        }
    }

    private static void rotateDown() {
        int dir = 0; // 초기 방향 설정
        // 시작점 설정
        int r = cleanerDn_r + 1;
        int c = 0;

        while (true) {
            int nr = r + drDn[dir];
            int nc = c + dc[dir];
            if (nc < C && nr < R && nr >= cleanerDn_r) { // 경계를 벗어나지 않는다면?
                if (room[nr][nc] == -1) { // 다음 칸이 공기청정기라면?
                    room[r][c] = 0; // 현재 위치에 0을 넣고
                    break; // 현재 시간의 순환을 종료
                }
                // 다음 칸이 공기청정기가 아니라면?
                room[r][c] = room[nr][nc]; // 다음값 당겨오기
                r = nr; // 이동
                c = nc;
            } else
                dir++; // 경계를 벗어난다면 방향 전환
        }


    }

    private static boolean isOutOfRange(int r, int c) {
        return r < 0 || r >= R || c < 0 || c >= C;
    }
}
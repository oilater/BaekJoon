import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 시작 시각: 10:30
 * 종료 시각:
 * 풀이 시간:
 *
 * 문제 설명
 * map : N * N
 * 각 칸에는 숫자 or 지뢰가 들어가있다.
 * 빈칸에는 숫자 0이 들어감
 *
 * map에 적힌 각 숫자는 그 칸과 인접한 8개의 칸 중 지뢰가 들어있는 칸이 몇개인지 나타냄
 *
 * 한 칸에 여러 개의 지뢰가 묻혀있음 1<= 지뢰 <= 9
 * 따라서 칸에 적힌 숫자 : 인접한 여덟개의 칸들에 들어있는 지뢰의 총 개수
 *
 * map에서 지뢰에 대한 정보만 주어졌을 때 map을 완성해보자
 *
 * 입력
 * 첫줄: 1 <= N <= 1000
 * 다음 N개의 줄: map 정보 ('.' or 숫자로 이루어진 문자열
 * .는 지뢰가 없는 경우, 숫자는 그 칸의 지뢰 개수
 *
 * 출력
 * 완성된 map 출력
 * 10 이상인 경우 M 출력
 * 숫자 혹은 M 혹은 * (지뢰)로만 출력
 *
 * 생각나는 풀이
 * 팔방 탐색 문제 : dr, dc 이용
 * for 문을 돌며 각 위치에서 팔방탐색
 * 인접 8칸의 지뢰 개수를 더해서 . 자리에 넣어줌
 * 대신 . 위에 있을 때만 탐색
 * 지뢰 개수가 10이 넘어간다면 M을 넣어줌
 *
 */
public class Main {
    static int N; // map 의 길이
    static char[][] map; // 지뢰판
    static char[][] copy; // 새로운 2차원 배열을 만들어주자

    // 방향 델타 (좌상, 상, 우상, 우, 우하, 하, 좌하, 좌 ) (시계방향)
    static int[] dr = {-1, -1, -1, 0, 1, 1, 1, 0};
    static int[] dc = {-1, 0, 1, 1, 1, 0 ,-1, -1};
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][];
        copy = new char[N][N];
        // 맵 입력
        for (int i = 0; i < N; i++) {
            char[] tmp = br.readLine().toCharArray();
            map[i] = tmp;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == '.') { // . 위에 있다면 메서드 호출
                    findMine(i, j);
                } else { // 지뢰 위에 있다면 * 로 바꿔줌
                    copy[i][j] = '*';
                }
            }
        }

        // 출력
        for (char[] charArr : copy) {
            sb.append(charArr).append('\n');
        }
        System.out.println(sb);

    }

    // 주변의 지뢰를 찾아 총합을 넣어줄 메서드
    private static void findMine(int r, int c) {
        int mineNum = 0; // 탐색 후 넣어줄 지뢰의 총합 개수

        // 8방 탐색
        for (int d = 0; d < 8; d++) {
            // 탐색할 좌표의 열, 행
            int newR = r + dr[d];
            int newC = c + dc[d];

            if(isInRange(newR, newC) && map[newR][newC] >= '1' && map[newR][newC] <= '9') {
                mineNum += map[newR][newC] - '0'; // 문자열을 정수로 변환해서 mineNum에 넣어줌
            }
        }

        if(mineNum >= 10) { // mineNum 이 10보다 크거나 같다면
            copy[r][c] = 'M';
        } else { // 10보다 작다면
            copy[r][c] = (char) (mineNum + '0'); // mineNum에 있는 정수를 다시 문자열로 변환해서 넣어줌
        }

    }

    // 범위 체크
    private static boolean isInRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}

/**
 * 어려웠던 부분
 * int 와 char을 마음대로 못바꾸겠다
 * 헷갈림
 * 새로운 2차원 배열을 안만드니까 배열이 계속 업데이트 되면서 잘못된 값이 들어감
 */
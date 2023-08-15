import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제
 * - . 평지 (전차가 들어갈 수 있음)
 * - * 벽돌로 만든 벽
 * - # 강철로 만든 벽
 * - - 물 (전차 출입 불가능)
 * - ^ 위쪽을 바라보는 전차
 * - v 아래쪽 바라보는 전차
 * - < 왼쪽을 바라보는 전차
 * - > 오른쪽 바라보는 전차
 * - 각 전차의 아래는 평지
 *
 * 사용자 입력
 * U : 전차의 방향 위쪽으로 바꾸고, 한 칸 위가 평지면 그 칸으로 이동
 * D : 전차의 방향 아래로 바꾸고, 아래가 평지면 이동
 * L : 전차 방향 왼쪽으로 바꾸고, 왼쪽이 평지면 이동
 * R : 전차 방향 오른쪽으로 바꾸고 한칸 오른쪽 평지면 이동
 * S : 전차가 바라보고 있는 방향으로 포탄을 발사
 *
 * 전차는 맵 안에서만 이동할 수 있음
 * 포탄은 벽돌 벽, 강철 벽에 충돌하거나 맵 밖으로 나갈 때까지 직진
 * 포탄이 벽에 부딪히면 포탄은 소멸, 만약 벽돌 벽 * 에 부딪히면 벽은 파괴되고 평지 . 가 됨
 * 강철 벽에 부딪히면 아무일도 안일어남
 * 맵밖으로 포탄 나가면 아무일도 안일어남
 *
 * 바뀐 맵 상태 출력하기
 *
 * 입력
 * 첫줄 : T 테스트 케이스
 * 둘째줄 : H W
 * 다음 H개의 줄: W 길이의 문자열
 * 전차는 단 하나만 있음
 * 다음 줄 : 사용자 입력 개수 N
 * 다음 줄 : 길이가 N인 문자열
 *
 * 제한사항
 * 2 <= H, W <= 20
 * 0 < N <= 100
 *
 * 생각나는 풀이
 * 맵 입력하면서 현재 탱크 위치 및 방향 클래스로 저장
 * 사용자 입력에 따라 탱크 위치 수정 및 맵 정보 바꾸기
 * 현재 탱크 정보 고려하면서 입력 따라가면서 구현하기
 * 첫 탱크 위치는 평지로 바꿔놓고 사용자 키 입력 다 끝나면 그 위치에 탱크 정보 입력하기
 *
 * 시작 시간 : 10 : 32
 */
public class Solution {
    static Tank tank;

    static class Tank {
        int r; // 현재 탱크의 열 위치
        int c; // 현재 탱크의 행 위치
        int dir; // 현재 탱크의 방향 정보
        Tank (int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }

        // move 메서드
        void move(int dir) {
            // 탱크 방향 먼저 바꿔주기
            this.dir = dir;
            // 탱크 위치 옮겨주기
            int newR = r + dr[dir];
            int newC = c + dc[dir];

            if(!isInRange(newR, newC) || map[newR][newC] != '.') return;
            // 이부분 tank.r 로 해줘야 하나?
            tank.r = newR;
            tank.c = newC;

        }

        // shoot 메서드
        void shoot() {
                // 먼저 다음에 갈 곳을 지정
                int newR = r + dr[dir];
                int newC = c + dc[dir];

                // while의 조건을 이런 식으로 하는구나
                while(isInRange(newR, newC)) { // 맵 안에 있는 동안
                    if(map[newR][newC] == '*') { // 벽돌 벽이라면
                        map[newR][newC] = '.'; // 평지로 변경
                        break;
                    }

                    if(map[newR][newC] == '#') break; // 강철벽이라면

                    // 하나씩 올려주기
                    newR += dr[dir];
                    newC += dc[dir];
                }
        }
    }
    static int H, W; // 맵의 높이와 너비

    static char[][] map; // 맵
    // 상 하 좌 우

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[] userInput = {'U', 'D', 'L', 'R'};
    static char[] tankShape = {'^', 'v', '<', '>'};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수
        for (int test = 1; test <= T; test++) {
            st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            map = new char[H][]; // 맵 H * W;

            // 맵 정보 입력
            // tankFind라는 boolean flag를 사용하면 맵의 위치는 다 받으면서, 탱크를 찾았을 때 바로 작업을 그만할 수 있는 효율성이 생김
            boolean tankFind = false;
            for (int i = 0; i < H; i++) {
                map[i] = br.readLine().toCharArray();
                if (tankFind) continue;
                for (int j = 0; j < W; j++) {
                    for (int dir = 0; dir < 4; dir++) {
                        if(map[i][j] == tankShape[dir]) {
                            tank = new Tank(i, j, dir); // r, c, direction
                            map[i][j] = '.';
                            tankFind = true;
                            break;
                        }
                    }
                }
            }

            // 이제 시작 탱크 위치, 방향을 알았고 맵 정보를 다 입력했다.
            // 이제 사용자 입력을 받자
            int N = Integer.parseInt(br.readLine()); // 사용자 입력 개수
            String input = br.readLine();
            for (int i = 0; i < N; i++) {
                command(input.charAt(i));
            }

            // 마지막 탱크 위치 및 방향 맵에 저장하기
            map[tank.r][tank.c] = tankShape[tank.dir];

            sb.append('#').append(test).append(' ');
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    sb.append(map[i][j]);
                }
                sb.append('\n');
            }

        }
        System.out.println(sb); // 출력
    }

    private static void command(char cmd){
        for (int i = 0; i < 4; i++) {
            // 탱크가 움직인다면
            if(cmd == userInput[i]) {
                tank.move(i);
                return;
            }
        }
        // 탱크가 포를 발사한다면
        tank.shoot();
    }

    private static boolean isInRange(int r, int c) {
        return r >= 0 && c >= 0 && r < H && c < W;
    }
}
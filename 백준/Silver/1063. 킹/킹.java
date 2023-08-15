import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 시작 시간 : 3:27
 * 종료 시간 :
 *
 *
 * 문제
 * 8 * 8 크기 체스판
 * 왼쪽 열 A ~ 오른쪽열 H
 * 위쪽 행 1 ~ 아래쪽행 8
 *
 * 방향
 * T, R, B, L, LT, RT, RB, LB
 *
 * 돌과 같은 곳으로 이동할 때에는 돌을 킹이 움직인 방향으로 한 칸 이동시킨다.
 * 킹이나 돌이 체스판으로 나갈 경우에는 그 이동 건너뛰고 다음 이동한다.
 *
 * 입력
 * 킹, 돌의 위치, 움직이는 횟수 N
 * A1 A2 5
 * 둘째부터 N개의 줄 => 킹이 움직이는 정보
 *
 * 출력
 * 첫줄 : 킹의 마지막 위치
 * 둘째 줄 : 돌의 마지막 위치
 *
 * 생각나는 풀이
 * 일단 ABCDEFGH 이걸 숫자로 바꾸고 싶다.
 * 근데 그럼 출력할 때 안될 듯은 아니고 어차피 행렬 기준으로 판단하면 될 듯
 * 킹의 r, c 위치 정보 담은 클래스 => 킹과 돌을 생성
 * 빡구현
 *
 */

public class Main {

    static class King {
        int r;
        int c;
        public King(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    // 입력 정보 비교할 방향 저장
    static String[] inputs = {"T", "R", "B", "L", "LT", "RT", "RB", "LB"};

    // 팔방 델타
    static int[] dr = {1, 0, -1, 0, 1, 1, -1, -1};
    static int[] dc = {0, 1, 0, -1, -1, 1, 1, -1};

    // 킹과 돌 객체 생성
    static King king;
    static King stone;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String K = st.nextToken(); // 킹의 위치 A1
        String S = st.nextToken(); // 돌의 위치 A2

        king = new King(0, 0);
        stone = new King(0, 0);

        // 각 돌 위치 저장하자
            king.r = K.charAt(1) - '0';
            king.c = K.charAt(0) - 'A';

            stone.r = S.charAt(1) - '0';
            stone.c = S.charAt(0) - 'A';

        int N = Integer.parseInt(st.nextToken()); // 이동할 횟수
        for (int i = 0; i < N; i++) {
            String dis = br.readLine();
            findDir(dis);
        }

        // 출력 준비
        sb.append((char)(king.c + 'A')).append(king.r).append('\n');
        sb.append((char)(stone.c + 'A')).append(stone.r);

        // 출력
        System.out.println(sb);

    }

    private static void findDir(String dir) {
        for (int i = 0; i < inputs.length; i++) {
            if(inputs[i].equals(dir)) {
                move(i);
            }
        }

    }

    private static void move(int dir) {
        // 킹의 원래 위치 저장해놓기
        int y = king.r;
        int x = king.c;
        // 킹의 예상 이동 지점
        int kingR = king.r + dr[dir];
        int kingC = king.c + dc[dir];

        // 새로운 킹의 위치가 범위 안에 포함된다면
        if(isInRange(kingR, kingC)) {
            king.r = kingR;
            king.c = kingC;
            // 이동했는데 킹과 돌의 위치가 같다면?
            if (kingR == stone.r && kingC == stone.c) {
                // stone 도 한 칸 이동해줘야 한다.
                if (isInRange(stone.r + dr[dir], stone.c + dc[dir])) {
                    stone.r += dr[dir];
                    stone.c += dc[dir];
                } else {
                    // 돌이 범위를 벗어났다면 킹의 위치 또한 다시 제자리로
                    king.r = y;
                    king.c = x;
                }
            }
        }
    }

    // 체스판 안에 있는지 검사
    private static boolean isInRange(int r, int c) {
        return r >= 1 && r <= 8 && c >= 0 && c <8;
    }
}
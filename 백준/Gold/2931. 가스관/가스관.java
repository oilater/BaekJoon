import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 블록 |
 * 블록 -
 * 블록 +
 * 블록 1 우하
 * 블록 2 상우
 * 블록 3 우좌
 * 블록 4 좌하
 * 문제해결 프로세스:
 * 빈칸만 탐색해서 사방의 도로를 확인하기
 */
public class Main {
    static int R, C;
    static char[][] arr;

    // delta
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static char[] roads = {'|', '-', '+', '1', '2', '3', '4'}; // 7개
    static Map<Character, Boolean[]> map; // Map - 키 : 도로 모양 , 값 : 상하좌우 이동 가능 여부 true/false 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new char[R][C]; // 격자 채우기
        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                char input = str.charAt(j);
                arr[i][j] = input;
            }
        }
        // HashMap 세팅  (상, 하, 좌, 우 이동가능 여부) => 여기 배열 순서 주의해줘야 함!
        map = new HashMap<>();

        map.put('|', new Boolean[] {true, true, false, false}); // 상 하 이동가능
        map.put('-', new Boolean[] {false, false, true, true}); // 좌 우 이동가능
        map.put('+', new Boolean[] {true, true, true, true}); // 상 하 좌 우 이동가능
        map.put('1', new Boolean[] {true, false, true, false}); // 하 우 이동가능
        map.put('2', new Boolean[] {false, true, true, false}); // 상 우 이동가능
        map.put('3', new Boolean[] {false, true, false, true}); // 상 좌 이동가능
        map.put('4', new Boolean[] {true, false, false, true}); // 하 좌 이동가능

        // 빈칸 탐색
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (arr[i][j] == '.') {
                    findAround(i, j); // 빈칸일 때 4방 탐색하기
                }
            }
        }

    }

    private static void findAround(int r, int c) {
        int roadCnt = 0; // 현재 칸이 도로가 있던 칸인지 확인하려 만든 변수 (연결 가능성 체크)
        boolean[][] visited = new boolean[R][C]; // 주변 인접도로 체크용

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d]; // 인접 칸 구하기
            int nc = c + dc[d];
            if(isInRange(nr, nc) && arr[nr][nc] != '.') { // 범위 안에 있고, 빈칸이 아니라면

                char aroundRoad = arr[nr][nc]; // 인접 도로의 모양
                switch (aroundRoad) {
                    case '|' :
                        if (map.get('|')[d]) {
                            roadCnt++; // true 값이면 연결 가능성 1 추가
                            visited[nr][nc] = true;
                        }
                        break;
                    case '-':
                        if (map.get('-')[d]) {
                            roadCnt++;
                            visited[nr][nc] = true;
                        }
                        break;
                    case '+':
                        if (map.get('+')[d]) {
                            roadCnt++;
                            visited[nr][nc] = true;
                        }
                        break;
                    case '1':
                        if (map.get('1')[d]) {
                            roadCnt++;
                            visited[nr][nc] = true;
                        }
                        break;
                    case '2':
                        if (map.get('2')[d]) {
                            roadCnt++;
                            visited[nr][nc] = true;
                        }
                        break;
                    case '3':
                        if (map.get('3')[d]) {
                            roadCnt++;
                            visited[nr][nc] = true;
                        }
                        break;
                    case '4':
                        if (map.get('4')[d]) {
                            roadCnt++;
                            visited[nr][nc] = true;
                        }
                        break;
                }





            }
        }

        if (roadCnt >= 2) {
            char roadShape = '?';
            boolean[] tmp = new boolean[4]; // 현위치 기준 4방 탐색해서 상하좌우 순으로 넣어줌

            // 현 위치 기준 4방 boolean 탐색
            for (int dir = 0; dir < 4; dir++) {
                int nr2 = r + dr[dir];
                int nc2 = c + dc[dir];
                if(isInRange(nr2, nc2)) {
                    tmp[dir] = visited[nr2][nc2];
                }
            }

            if (tmp[0] && tmp[1] && tmp[2] && tmp[3]) roadShape = '+';
            else if (tmp[0] && tmp[1]) {
                roadShape = '|';
            } else if (tmp[2] && tmp[3]) {
                roadShape = '-';
            } else {
                for (int i = 0; i < map.size(); i++) {
                    int cnt = 0;
                    for (int j = 0; j < map.get(roads[i]).length; j++) {
                        if (map.get(roads[i])[j] != tmp[j]) {
                            cnt++;
                        }
                    }
                    if (cnt == 4) {
                        roadShape = roads[i];
                    }
                }
            }


            System.out.println((r+1) + " " + (c+1) + " " + roadShape);
            System.exit(0);
        }


    }

    private static boolean isInRange(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.*;

/**
 * 비어있는 곳 .
 * 물 *
 * 돌 X
 *
 * 비버의 굴 D
 * 고슴도치 위치 S
 *
 * 매분마다 고슴도치는 4방 중 한 곳 이동 가능
 * 물도 매분마다 인접 빈 칸으로 물이 참(적어도 한 변을 공유한 곳)
 * 물과 고슴도치는 돌 통과 불가능
 * 고슴도치는 물찬 구역으로 못감, 물도 비버의 소굴로 이동 X
 *
 * 고슴도치가 비버의 굴로 이동하기 위해 필요한 최소 시간을 구하시오
 * 물이 찰 에정인 칸으로 이동 불가
 *
 * 문제 해결 프로세스
 * 일단 물부터 사방으로 퍼지게 하자 막힌 곳 제외
 * 물이 없는 곳으로 고슴도치 이동하기
 * 이게 한 싸이클이고 시간 1분 증가
 * D 지점에 도착한 최소 시간 구하기
 * 도달하지 못하면 KAKTUS 출력
 *
 */
public class Main {
    static int[] dr = {-1, 1, 0 , 0};
    static int[] dc = {0, 0, -1, 1};

    static class Node {
        int r, c;
        char shape;
        public Node (int r, int c, char shape) {
            this.r = r;
            this.c = c;
            this.shape = shape;
        }
    }
    static int R, C;
    static char[][] map;

    static boolean[][] waterVisited;
    static boolean[][] dochiVisited;
    static List<Node> waters = new ArrayList<>(); // 물 위치
    static Node dochi; // 고슴도치의 위치
    static Node goal; // 목적지
    static int ans = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        waterVisited = new boolean[R][C];
        dochiVisited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                char input = str.charAt(j);
                map[i][j] = input;
                if (input == 'D') goal = new Node(i, j, map[i][j]);
                else if (input == 'S') dochi = new Node(i, j, map[i][j]);
                else if (input == '*') waters.add(new Node(i, j, map[i][j]));
            }
        }

        bfs();
        if (ans == -1) {
            System.out.println("KAKTUS");
            return;
        }

        System.out.println(ans);
    }

    // 레벨 별 bfs
    private static void bfs() {
        Queue<Node> que = new ArrayDeque<>();

        // 물 큐에 넣기
        for (int i = 0; i < waters.size(); i++) {
            que.add(waters.get(i));
            waterVisited[waters.get(i).r][waters.get(i).c] = true;
        }

        // 고슴도치 큐에 넣기
        que.add(dochi);
        dochiVisited[dochi.r][dochi.c] = true;

        int time = 0; // 시간
        while (!que.isEmpty()) {
            int size = que.size();
            while (size-- > 0) {
                Node node = que.poll();
                int r = node.r;
                int c = node.c;
                char shape = node.shape;

                if (goal.r == node.r && goal.c == node.c) {
                    ans = time;
                    return;
                }
                /**
                 * 범위를 벗어나면 continue
                 * shape 가 'S' 인 경우, '*' 인 경우로 나눠서 풀자
                 * 빈칸인 곳 위주로 움직일 수 있음
                 * 1. S 인 경우,
                 *  1-1. D가 아니며 빈칸이 아닌 경우 continue
                 *  1-2. 큐에 넣기 , 맵 S로 바꿔주기
                 *
                 * 2. * 인 경우
                 *  2-1. 돌 또는 D 또는 이미 물인 경우 Continue;
                 *  2-2. 큐에 넣기, 맵 바꿔주기 물로
                 */

                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if (!isInRange(nr, nc)) continue; // 범위를 벗어나면 continue;

                    if (shape == 'S') { // 현재 Node가 고슴도치인 경우
                        if (map[nr][nc] == '*' || map[nr][nc] == 'X' || dochiVisited[nr][nc]) continue;
                        dochiVisited[nr][nc] = true;
                        que.add(new Node(nr, nc, 'S'));
                    } else {
                        if (map[nr][nc] == 'X' || map[nr][nc] == 'D' || waterVisited[nr][nc]) continue;
                        map[nr][nc] = '*';
                        waterVisited[nr][nc] = true;
                        que.add(new Node(nr, nc, '*'));
                    }
                }
            }
            time++; // 1분씩 증가
        }
    }
    private static boolean isInRange(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    private static void print() {
        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
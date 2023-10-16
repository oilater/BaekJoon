import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 문제
 * 1~N 명의 사람들을 링크와 스타트 팀으로 나누어야 한다.
 * 한팀에 뽑히는 사람들의 수는 1~N-1명
 * 조합코드를 이용한 부분집합으로 풀 수 있지 않을까?
 *
 * 조합이 완성되었다면 팀간의 차이 검사
 *
 *
 */
public class Main {
    static int N;
    static int[][] arr;
    static boolean[] picked;

    static List<Integer> link; // 링크 팀
    static List<Integer> start; // 스타트 팀


    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1][N+1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 조합을 이용한 부분집합
        for (int cnt = 1; cnt <= N-1; cnt++) {
            picked = new boolean[N+1];
            combination(1, 0, cnt);
        }

        System.out.println(ans);
    }

    // 뽑히면 true, 안뽑히면 false
    private static void combination(int start, int cnt, int r) {
        if (cnt == r) { // 팀이 뽑혔다면
            getDiff(); // 팀간의 능력치 차이 구해주기
            return;
        }

        for (int i = start; i <= N; i++) {
            picked[i] = true;
            combination(i+1, cnt+1, r);
            picked[i] = false;
        }
    }

    // 팀 간 능력치 차이 구하기 1 ~ N
    private static void getDiff() {
        link = new ArrayList<>();
        start = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            if (picked[i]) link.add(i); // true면 link 팀에 넣기
            else start.add(i); // false라면 start 팀에 넣기
        }

        int linkPower = 0; // 링크 팀 능력치 합
        int startPower = 0; // 스타트 팀 능력치 합

        // 링크 팀 능력치 구하기
        for (int i = 0; i < link.size(); i++) {
            for (int j = i; j < link.size(); j++) {
                linkPower += arr[link.get(i)][link.get(j)] + arr[link.get(j)][link.get(i)];
            }
        }
        // 스타트 팀 능력치 구하기
        for (int i = 0; i < start.size(); i++) {
            for (int j = i; j < start.size(); j++) {
                startPower += arr[start.get(i)][start.get(j)] + arr[start.get(j)][start.get(i)];
            }
        }
        ans = Math.min(ans, Math.abs(linkPower - startPower)); // 정답값 최소로 업데이트
    }
}
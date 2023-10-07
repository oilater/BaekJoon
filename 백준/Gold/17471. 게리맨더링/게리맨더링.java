import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 선거구를 A, B로 나누어야 한다.
 * How? 하나의 boolean 배열에서 1~N 범위에서 true, false 로 나누자
 * true인 곳은 A, false인 곳은 B 리스트에 넣자
 * - 각 선거구는 적어도 하나의 구역을 포함해야 한다.
 *
 * 출력
 * 각 A, B 선거구의 인구 차이의 최소값을 출력, 선거구를 나눌 수 없다면 -1 출력
 */
public class Main {
    static int N; // 구역(정점)의 개수 N
    static int[] population; // 1~N 구역의 각각의 인구수

    static List<Integer>[] adjList; // List 타입의 인접 리스트 생성

    static boolean[] divide; // 구역 나눠진 걸 표시할 boolean 배열 divide

    static List<Integer> A; // A 구역
    static List<Integer> B; // B 구역

    static int ans = 987_654_321; // 정답값 초기화
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        // 인접 리스트 생성
        adjList = new ArrayList[N+1]; // 1~N 이용
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        divide = new boolean[N+1]; // 구역 나눠질 배열 생성

        // 각 구역의 인구 넣을 배열 생성
        population = new int[N+1]; // 1~N 이용
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 각 구역의 인구수 입력
        for (int i = 1; i <= N; i++) {
            population[i] = Integer.parseInt(st.nextToken());
        }

        // 인접리스트(각 구역의 인접 정보) 입력
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); // 현재 구역에 인접한 구역의 수
            for (int j = 0; j < n; j++) {
                int to = Integer.parseInt(st.nextToken());
                adjList[i].add(to);
            }
        }

        // 조합을 이용한 부분집합으로 구역을 나누자
        // 1 ~ N-1개까지 나눠야 함
        for (int cnt = 1; cnt <= N-1 ; cnt++) {
            combination(1, 0, cnt); // 나누자
        }

        // ans가 여전히 최댓값이라면 두 선거구로 나눌 수 없는 경우이므로 -1 출력,
        // 아니라면 최솟값으로 업데이트 된 ans 출력
        System.out.println(ans == 987_654_321 ? -1 : ans);
    }

    /**
     * 구역이 다 나누어졌다면 해줘야 할 것은?
     * A, B 선거구에 있는 구역들이 서로 연결되어 있는지 확인해야 한다.
     * How?
     * A선거구부터 BFS를 돌리자.
     * A 선거구의 길이 만큼 방문배열을 만들어서,
     * 인접리스트 이용해 방문 체크
     * 체크가 다 안되었다면 false 반환 -> 다음 선택으로 넘어가기
     * 체크가 다 되었다면 B 선거구 BFS 돌리기
     * B선거구의 길이 만큼 방문배열을 만들어 방문 체크
     * 체크 다 안되었다면 false 반환 -> 다음 선거구 선택으로 넘어가기
     * 체크가 역시 다 되었다면 True 반환
     * true 라면 A, B 선거구의 인구의 차이값 구해서 ans에 Math.min 으로 업데이트 (최솟값이니까)
     * ans 출력 후 종료
     */
    private static void combination(int start, int cnt, int r) {
        if (cnt == r) { // 구역이 다 나누어졌다면
            A = new ArrayList<>(); // A 구역 생성
            B = new ArrayList<>(); // B 구역 생성
            for (int i = 1; i <= N; i++) {
                if (divide[i]) A.add(i); // true라면 A 선거구에 해당 구역 넣기
                else B.add(i); // false라면 B 선거구에 해당 구역 넣기
            }

            if (bfs(A)) { // A 선거구 먼저 검사
                if(bfs(B)) { // B 선거구도 검사를 통과한다면
                    // 이제는 각 선거구의 인구 차를 구할 수 있다
                    ans = Math.min(ans, getDifference()); // 최솟값 갱신
                }
            }
            return;
        }

        for (int i = start; i <= N; i++) {
            divide[i] = true;
            combination(i + 1, cnt + 1, r);
            divide[i] = false;
        }
    }

    private static int getDifference() {
        int sumA = 0;
        int sumB = 0;
        for (int i = 0; i < A.size(); i++) {
            sumA += population[A.get(i)];
        }

        for (int i = 0; i < B.size(); i++) {
            sumB += population[B.get(i)];
        }

        return Math.abs(sumA - sumB);
    }

    private static boolean bfs(List<Integer> list) {
        boolean[] visited = new boolean[N+1]; // 방문 확인 배열 생성
        Queue<Integer> que = new ArrayDeque<>(); // 큐 생성
        que.add(list.get(0)); // 큐에 A or B 선거구의 첫번째 구역 넣기
        visited[list.get(0)] = true; // 첫번째 구역 방문 체크
        boolean flag = divide[list.get(0)];

        while (!que.isEmpty()) {
            int node = que.poll(); // 넣었던 구역을 꺼냄
            for (int n : adjList[node]) { // 해당 구역에 인접한 구역들을 순회하며 방문
                if (visited[n]) continue;

                if (flag != divide[n]) continue;
                que.add(n); // 해당 구역에 인접한 구역들 큐에 넣기
                visited[n] = true; // 방문 체크
            }
        }

        // 선거구의 모든 구역이 방문처리 되었다면 해당 선거구의 모든 구역들은 연결되어 있다는 것
        for (int i = 0; i < list.size(); i++) {
            if (!visited[list.get(i)]) return false; // 방문되지 않은 곳이 있으면 false 리턴
        }
        // 통과했다면
        return true;
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 *  K번쨰 사람 제거 반복한다.
 *  3 6 2 7 5 1 4
 *
 * 제거 하면 순열에 넣어줌
 * 먼저 리스트에 넣어놀까?
 * 넣기 즇은 자료구조가 뭐가 있을까?
 * 리스트 ? 큐 ?
 * 큐가 좋을 것 같다.
 *
 * 푸는 방법
 *  k 번째가 아니라면 que에서 poll 후 바로 offer 해준다.
 *  k 번째라면 리스트에 넣어주고 que 에서 제거한다
 *
 *  que가 빌때까지 반복한 후 리스트 출력
 *
 */
public class Main {
    static Queue<Integer> que;
    static int N, K;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        que = new LinkedList<Integer>();
        List<Integer> list = new ArrayList<>();
        
        for (int i = 1; i <= N; i++) {
            que.offer(i);
        }

        int cnt = 1;
        while(!que.isEmpty()) {
            if (cnt != K) {
                que.offer(que.poll());
                cnt++;
            } else {
                list.add(que.poll());
                cnt = 1;
            }
        }

        String ans = list.toString();
        ans = ans.replace("[", "<");
        ans = ans.replace("]", ">");
        System.out.println(ans);

    }


}
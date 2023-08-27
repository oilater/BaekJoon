import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, S;
    static int ansCnt;
    static ArrayList<Integer> list;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        visited = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            list.add(num);
        }

        powerSet(0);
        ansCnt = S != 0 ? ansCnt : ansCnt - 1;
        System.out.println(ansCnt);
    }

    private static void powerSet(int idx) {
        if (idx == list.size()) {
            int sum = 0;
            for (int i = 0; i < list.size(); i++) {
                if(visited[i]) {
                    sum += list.get(i);
                }
            }
            if(sum == S) { // 합이 S와 같다면 카운트 1 증가
                ansCnt++;
            }


            return;
        }

        visited[idx] = false;
        powerSet(idx + 1);
        visited[idx] = true;
        powerSet(idx + 1);

    }
}
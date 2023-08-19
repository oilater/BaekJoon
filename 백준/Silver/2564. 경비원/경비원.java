import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 1 북 2 남 3 서 4 동
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(br.readLine());
        int[] stores = new int[2 * W + 2 * H];
        // 입력
        int point = 1;
        for (int i = 0; i < S; i++) {
            st= new StringTokenizer(br.readLine());
            int dir = Integer.parseInt(st.nextToken());
            int dis = Integer.parseInt(st.nextToken());
            // 각 상점들의 위치 표시

            if(dir == 1) {
                stores[dis] = point++;
            }
            if(dir == 2) {
                stores[W + H + (W - dis)] = point++;
            }
            if(dir == 3) {
                stores[W + H + W + (H - dis)] = point++;
            }
            if(dir == 4) {
                stores[W + dis] = point++;
            }
        }

        st= new StringTokenizer(br.readLine());
        int myDir = Integer.parseInt(st.nextToken());
        int myDis = Integer.parseInt(st.nextToken());

        if(myDir == 2) {
            myDis = W + H + (W - myDis);
        }
        if(myDir == 3) {
            myDis = W + H + W + (H - myDis);
        }
        if(myDir == 4) {
            myDis = W + myDis;
        }

        int answer = 0;
        for (int i = 1; i < point; i++) {
            answer += Math.min(left(i, stores, myDis), right(i, stores, myDis));
        }
        System.out.println(answer);
    }

    private static int left(int target, int[] stores, int now) {
        int cnt = 0;
        // i는 상점의 번호 위치
        while(stores[now] != target) {
            now--;
            if(now == -1) {
                now = stores.length-1;
            }
            cnt++;
        }
        return cnt;
    }

    private static int right(int target, int[] stores, int now) {
        int cnt = 0;
        while(stores[now] != target) {
            now++;
            if(now == stores.length) {
                now = 0;
            }
            cnt++;
        }
        return cnt;
    }
}
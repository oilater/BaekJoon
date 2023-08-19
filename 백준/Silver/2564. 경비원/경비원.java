
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
        int[] stores = new int[S+1];
        // 입력
        for (int i = 0; i <= S; i++) {
            st= new StringTokenizer(br.readLine());
            int dir = Integer.parseInt(st.nextToken());
            int dis = Integer.parseInt(st.nextToken());
            // 각 상점들의 위치 표시 - 북쪽 0 기준
            if(dir == 1) {
                stores[i] = dis;
            }
            if(dir == 2) {
                stores[i] = W + H + (W - dis);
            }
            if(dir == 3) {
                stores[i] = W + H + W + (H - dis);
            }
            if(dir == 4) {
                stores[i] = W + dis;
            }
        }

        int ans = 0;
        for (int i = 0; i <= S; i++) {
            int tmp =0;
            tmp = Math.abs(stores[i] - stores[S]);
            tmp = Math.min(tmp, W  * 2 + H * 2 - tmp);
            ans += tmp;
        }

        System.out.println(ans);
    }
}
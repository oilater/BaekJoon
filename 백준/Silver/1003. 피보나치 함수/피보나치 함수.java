import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 메모이제이션으로 풀기
 *
 */
public class Main {
    static Node[] memos;
    static class Node {
        int zeroCnt, oneCnt;

        public Node (int zeroCnt, int oneCnt) {
            this.zeroCnt = zeroCnt;
            this.oneCnt = oneCnt;
        }
    }

    static int n;
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            n = Integer.parseInt(br.readLine());
            memos = new Node[41];
            memos[0] = new Node(1, 0);
            memos[1] = new Node(0, 1);
            memos[2] = new Node(1, 1);

            for (int i = 3; i <= n; i++) {
                int zero = memos[i-2].zeroCnt + memos[i-1].zeroCnt;
                int one = memos[i-2].oneCnt + memos[i-1].oneCnt;
                memos[i] = new Node(zero, one);
            }

            sb.append(memos[n].zeroCnt).append(' ').append(memos[n].oneCnt).append('\n');
        }
        System.out.println(sb);
    }
    }
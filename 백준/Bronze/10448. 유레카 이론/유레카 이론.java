import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static int T;
    static int N;
    static int ans;
    static int[] picked = new int[3];
    static  List<Integer> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int test_case = 0; test_case < T; test_case++) {
            ans = 0;
            N = Integer.parseInt(br.readLine());
            list = new ArrayList<Integer>();
            int num = 1;
            int tmp = 2;
            while(num < 1000) {
                list.add(num);
                num += tmp++;
            }

            // 중복순열
            permutation(0);
            System.out.println(ans);
        }



    }

    private static void permutation(int cnt) {
        if(cnt == 3) {
            int sum = 0;
            for (int el : picked) {
                sum += el;
            }
            if(sum == N) ans = 1;
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            picked[cnt] = list.get(i);
            permutation(cnt+1);
        }
    }
}
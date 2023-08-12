import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        // M개의 수
        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int num = Integer.parseInt(st.nextToken());
            boolean tf = isExist(num);

            if(tf) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }

    }

    private static boolean isExist(int num) {
        int start = 0;
        int end = arr.length-1;

        while (start <= end) {
            int mid = (start + end) / 2;
            for (int i = start; i <= end; i++) {
                if(arr[mid] < num) start = mid + 1;
                else if (arr[mid] > num) end = mid - 1;
                else return true;
            }
        }
        return false;
    }
}
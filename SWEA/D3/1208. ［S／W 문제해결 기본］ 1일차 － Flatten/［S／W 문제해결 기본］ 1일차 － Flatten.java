import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int[] arr = new int[100];
    static int N;

    static int maxIdx() {
        int max = 0;
        int maxIdx = 0;
        for (int i = 0; i < 100; i++) {
            if (max < arr[i]) {
                max = arr[i];
                maxIdx = i;
            }
        }
        return maxIdx;
    }

    static int minIdx() {
        int min = 101;
        int minIdx = 0;
        for (int i = 0; i < 100; i++) {
            if (min > arr[i]) {
                min = arr[i];
                minIdx = i;
            }
        }
        return minIdx;
    }

    static int Flatten() {
        for (int i = 0; i < N; i++) {
            arr[maxIdx()] -= 1; // 최고점 1 감소
            arr[minIdx()] += 1; // 최저점 1 증가
        }
        return arr[maxIdx()] - arr[minIdx()];
    }

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int tc = 1; tc <= 10; tc++) {
            N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < 100; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            sb.append("#").append(tc).append(' ').append(Flatten()).append('\n');
        }
        System.out.println(sb);
        }
    }
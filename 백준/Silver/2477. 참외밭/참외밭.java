import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[] arr = new int[6];
        int maxWidthI = 0, maxWidth = 0, maxHeightI =0, maxHeight =0;
        int d = 0;
        for (int i = 0; i < 6; i++) {
            st = new StringTokenizer(br.readLine());
            d = Integer.parseInt(st.nextToken());
            arr[i] = Integer.parseInt(st.nextToken());

            // 가장 긴 가로의 위치 및 길이 찾기
            if (d == 1 || d == 2) {
                if (maxWidth < arr[i]) {
                    maxWidth = arr[i];
                    maxWidthI = i;
                }
            }
            // 가장 긴 세로의 위치 및 길이 찾기
            if (d == 3 || d == 4) {
                if (maxHeight < arr[i]) {
                    maxHeight = arr[i];
                    maxHeightI = i;
                }
            }
        }
            int maxSquare = maxHeight * maxWidth;

            int blankHeight = 0, blankWidth = 0;
            int tmp = Math.abs(arr[5] - arr[1]);
            int tmp2 = Math.abs(arr[4] - arr[0]);
            if(maxWidthI == 0) {
                blankHeight = tmp;
            } else if (maxWidthI == 5) {
                blankHeight = tmp2;
            } else {
                blankHeight = Math.abs(arr[maxWidthI + 1] - arr[maxWidthI - 1]);
            }

            if(maxHeightI == 0) {
                blankWidth = tmp;
            } else if (maxHeightI == 5) {
                blankWidth = tmp2;
            } else {
                blankWidth = Math.abs(arr[maxHeightI + 1] - arr[maxHeightI - 1]);
            }

            int blankSquare = blankHeight * blankWidth;

            int ans = (maxSquare - blankSquare) *  N;

        System.out.println(ans);

    }
}
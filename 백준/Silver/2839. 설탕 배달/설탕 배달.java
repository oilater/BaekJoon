import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1. 5kg로 나눠진다면 바로 출력 2. 3과 5로 둘다 나눠지지 않는다면 5kg를 하나씩 빼가면서 3키로로 나눈 값을 출력하자 3. 아예
 * 3키로로 나눠지면 3kg로 나누자 - 근데 이건 나중에 고려해야함 먼저 3kg빼가면서 5키로로 나누는게 좋다.
 * 
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int cnt = 0; // 초기 카운트

		int A = N;
		while (N > 0) {
			if (N % 5 == 0) {
				cnt += N / 5;
				break;
			} else {
				if (N >= 3) {
					N -= 3;
					cnt++;
				} else {
					cnt = -1;
					break;
				}
			}
		}
		System.out.println(cnt);
	}

}
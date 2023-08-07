/**
 * 입력값
 * 1. N개 , 합을 구해야 하는 횟수 M 
 * 2. N개의 수 배열 주어짐
 * 3. 구간 주어짐 - 각각의 합 구하기
 * 
 * 전략
 * 합을 더해주는 합 배열 S를 생성해준다.
 * [1, 2, 3, 4, 5] => [1, 3, 5, 9, 14]
 * 
 * S(n) = S(n-1) + a(n);
 * 
 * 2부터 4까지의 구간합?
 *    S(4) = a(0) + a(1) + a(2) + a(3) + a(4)
 *  - S(1) = a(0) + a(1)
 * ---------------------------------------------
 *  S(4) - S(1) = a(2) + a(3) + a(4)
 * 
 * n ~ m 까지의 구간 합 
 * S(m) - S(n-1) 	
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		int[] S = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			S[i + 1] = S[i] + arr[i];
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			sb.append(S[B] - S[A - 1]).append('\n');
		}

		System.out.println(sb);
	}
}
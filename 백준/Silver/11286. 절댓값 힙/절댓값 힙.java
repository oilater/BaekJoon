import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/*
 * 클래스를 만든다
 * 클래스의 변수는 기존 값
 * 
 */
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		PriorityQueue<Q> pq = new PriorityQueue<>();
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			// 입력값 받기
			int input = Integer.parseInt(br.readLine());
			// Q 인스턴스 생성 (입력값, 입력값의 절댓값)
			Q val = new Q(input, Math.abs(input));

			// Q에 넣어줌
			if (input == 0) {
				if (pq.isEmpty()) {
					sb.append(0).append('\n');
				} else {
					sb.append(pq.poll().num).append('\n');
				}
			} else {
				pq.add(val);
			}

		}

		System.out.println(sb);
	}

	static public class Q implements Comparable<Q> {
		int num = 0;
		int absNum = 0;

		public Q(int num, int absNum) {
			this.num = num;
			this.absNum = absNum;
		}

		@Override
		public int compareTo(Q o) {
			if (this.absNum == o.absNum) {
				return this.num - o.num;
			}

			return this.absNum - o.absNum;
		}

	}
}
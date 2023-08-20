import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 양수 중 하나 선택
 * 세번째부터는 앞의 앞의 수에서 앞의 수를 빼면서 만든다.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        
        List<Integer> list = new ArrayList<>();
        List<Integer> copy = new ArrayList<>();

        int max = 0;
        for (int i = N; i >= 1; i--) { // N부터 1까지 하나씩 두번째 수로 선택하기
            list.add(N); // 리스트에 첫번쨰 수 추가
            list.add(i); // 두번째 수 추가
            while(true) {
                int n = list.get(list.size()-2) - list.get(list.size()-1); // i-1 - i-2 를 구해서 음수인지 체크
                if (n < 0) break;
                else list.add(n);
            }
            if(max < list.size()) { // 최대 크기 업데이트
                max = list.size();
                copy.clear();
                copy.addAll(list); // 최대 크기이면 배열 복사
            }
            list.clear();
        }
        sb.append(max).append('\n');
        for(Integer i : copy) {
            sb.append(i).append(' ');
        }
        sb.setLength(sb.length()-1);
        System.out.println(sb);

        }


    }
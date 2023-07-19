import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a = sc.next();
        int n = Integer.parseInt(sc.next());
        String ans = "";
        for(int i =0; i < n; i++) {
            ans += a;
        }
        System.out.println(ans);
    }
}
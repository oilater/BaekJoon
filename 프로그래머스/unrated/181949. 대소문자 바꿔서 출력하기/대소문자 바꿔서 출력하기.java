import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a = sc.next();
        String ans = "";
        for(int i = 0; i < a.length(); i++) {
            if(Character.toString(a.charAt(i)) == Character.toString(a.charAt(i)).toLowerCase()) ans += Character.toString(a.charAt(i)).toUpperCase();
            if(Character.toString(a.charAt(i)) == Character.toString(a.charAt(i)).toUpperCase()) ans += Character.toString(a.charAt(i)).toLowerCase();
        }
        System.out.println(ans);
    }
}
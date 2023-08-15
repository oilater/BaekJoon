import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1~15 까지 반복
 */

public class Main {

    static String input1;
    static String input2;
    static String input3;
    static String[] inputs;
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        input1 = br.readLine();
        input2 = br.readLine();
        input3 = br.readLine();
        inputs = new String[] {input1, input2, input3};

        for (String el: inputs) {
            String answer = isNum(el);
            if(answer != null) {
                System.out.println(answer);
                break;
            }
        }
    }

    private static String isNum(String str) {
        String ans = "";
        if(!str.equals("Fizz") && !str.equals("Buzz") && !str.equals("FizzBuzz")) {

            int num = 0;
            if(str.equals(input1)) {
                num = Integer.parseInt(str) + 3;
            } else if (str.equals(input2)) {
                num = Integer.parseInt(str) + 2;
            } else if (str.equals(input3)) {
                num = Integer.parseInt(str) + 1;
            }


            if (num % 3 ==0 && num % 5 ==0) {
                ans = "FizzBuzz";
            } else if(num % 3 == 0 && num % 5 != 0) {
                ans = "Fizz";
            } else if(num % 3 != 0 && num % 5 == 0) {
                ans = "Buzz";
            } else {
                ans = String.valueOf(num);
            }

            return ans;
        }
        return null;
    }
    }
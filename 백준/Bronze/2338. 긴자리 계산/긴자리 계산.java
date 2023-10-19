import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String a = br.readLine();
        String b = br.readLine();
        BigInteger A = new BigInteger(String.valueOf(a));
        BigInteger B = new BigInteger(String.valueOf(b));

        System.out.println(A.add(B));
        System.out.println(A.subtract(B));
        System.out.println(A.multiply(B));
    }
}
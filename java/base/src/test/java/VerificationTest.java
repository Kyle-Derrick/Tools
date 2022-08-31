import java.util.Scanner;

public class VerificationTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println(VerificationUtils.verifyPath(sc.nextLine()));
        }
    }
}

import java.util.Random;
import java.util.Scanner;

/**
 * main
 */
public class BabyBites {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Integer expected = Integer.parseInt(scan.nextLine());
        String str = scan.nextLine();

        String t = generator(1000);

        if (test(str, expected)) {
            System.out.println("makes sense");
        } else {
            System.out.println("something is fishy");
        }

        scan.close();
    }

    public static String generator(Integer N) {
        Random rand = new Random();
        String returnStr = "";
        for (int i = 0; i < N; i++) {
            if(rand.nextBoolean()) {
                returnStr += "mumble ";
            } else {
                returnStr += (i+1) + " ";
            }
        }
        return returnStr;
    }

    public static boolean test(String str, Integer expected) {
        String[] parts = str.trim().toLowerCase().split(" ");

        Integer number = 1;
        for (int i = 0; i < parts.length; i++) {
            String part = parts[i];

            if (part.matches("mumble")) {
                number++;
            } else {
                Integer num = Integer.parseInt(part);
                if (!num.equals(number)) {
                    return false;
                }
                number++;
            }
        }
        return number - 1 == expected;
    }
}
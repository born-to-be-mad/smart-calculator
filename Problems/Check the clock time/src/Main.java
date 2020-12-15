import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //  [01]?[0-9]	start with 0 or 1 then any from 0..9
        //  2[0-3]	    start with 2 then any from 0..3
        //  [0-5][0-9]  start with 0..5 then any from 0..9
        // HH:MM 24-hour with leading 0
        String regex = "^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]";

        String time = scanner.nextLine();
        System.out.println(time.matches(regex) ? "YES" : "NO");
    }
}

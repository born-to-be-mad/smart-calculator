import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String source = scanner.nextLine();
        String search = scanner.nextLine();
        int count = 0;
        while (source.contains(search)) {
            source = source.substring(source.indexOf(search) + search.length());
            count++;
        }
        System.out.println(count);
    }
}

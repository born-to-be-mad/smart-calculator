import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

class MaxElementOfStack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

/*        List<String> operations = IntStream.range(0, scanner.nextInt() + 1)
                                           .mapToObj(operand -> scanner.nextLine())
                                           .collect(Collectors.toList());*/
        //operations.forEach(System.out::println);

        Deque<Long> stack = new ArrayDeque<>();

        long max = Long.MIN_VALUE;
        int amountOfOperations = scanner.nextInt();
        for (int i = 0; i <= amountOfOperations; i++) {
            String operation = scanner.nextLine();
            if (operation.matches("push\\s+\\d+")) {
                long newValue = Long.parseLong(operation.split("\\s+")[1]);
                if (newValue > max) {
                    max = newValue;
                }
                stack.push(newValue);
            } else if (operation.equals("pop")) {
                if (stack.poll() == max) {
                    max = Long.MIN_VALUE;
                }
            } else if (operation.equals("max")) {
                if (max == Long.MIN_VALUE) {
                    max = stack.stream().mapToLong(value -> value).max().orElse(Long.MIN_VALUE);
                }
                System.out.println(max);
            }
        }

    }
}

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class MaxElementOfStack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> operations = IntStream.range(0, scanner.nextInt() + 1)
                                           .mapToObj(operand -> scanner.nextLine())
                                           .collect(Collectors.toList());
        //operations.forEach(System.out::println);

        Deque<Long> stack = new ArrayDeque<>();

        operations.forEach(operation -> {
            if (operation.matches("push\\s+\\d+")) {
                stack.push(Long.valueOf(operation.split("\\s+")[1]));
            } else if (operation.equals("pop")) {
                stack.poll();
            } else if (operation.equals("max")) {
                stack.stream().mapToLong(value -> value).max().ifPresent(System.out::println);
            }
        });

    }
}

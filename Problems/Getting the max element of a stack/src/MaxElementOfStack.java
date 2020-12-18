import java.util.Scanner;
import java.util.Stack;

class MaxElementOfStack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

/*        List<String> operations = IntStream.range(0, scanner.nextInt() + 1)
                                           .mapToObj(operand -> scanner.nextLine())
                                           .collect(Collectors.toList());*/
        //operations.forEach(System.out::println);

        Stack<Long> stack = new Stack<>();
        //Deque<Long> stack = new ArrayDeque<>();

        // keep track of max elements
        Stack<Long> maxElementsStack = new Stack<>();

        int amountOfOperations = scanner.nextInt();
        for (int i = 0; i <= amountOfOperations; i++) {
            String operation = scanner.nextLine();
            if (operation.matches("push\\s+\\d+")) {
                long newValue = Long.parseLong(operation.split("\\s+")[1]);
                stack.push(newValue);
                if (stack.size() == 1) {
                    maxElementsStack.push(newValue);
                } else {
                    if (newValue > maxElementsStack.peek()) {
                        maxElementsStack.push(newValue);
                    } else {
                        maxElementsStack.push(maxElementsStack.peek());
                    }
                }
            } else if (operation.equals("pop")) {
                stack.pop();
                maxElementsStack.pop();
            } else if (operation.equals("max")) {
                System.out.println(maxElementsStack.peek());
            }
        }

    }
}

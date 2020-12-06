import java.util.AbstractMap;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import static java.util.stream.Collectors.toMap;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        input = input.substring(input.indexOf("?") + 1);

        Map<String, String> keyValues = Arrays.stream(input.split("&"))
                                              .map(Main::parse)
                                              .collect(toMap(AbstractMap.SimpleEntry::getKey,
                                                             AbstractMap.SimpleEntry::getValue,
                                                             (o1, o2) -> o1,
                                                             LinkedHashMap::new));
        keyValues.forEach((key, value) -> System.out.printf("%s : %s%n", key, value));
        if (keyValues.containsKey("pass")) {
            System.out.printf("%s : %s%n", "password", keyValues.get("pass"));
        }

    }

    private static AbstractMap.SimpleEntry<String, String> parse(String str) {
        String[] keyValue = str.split("=");
        String key = keyValue[0];
        String value = (keyValue.length > 1) ? keyValue[1] : "not found";
        return new AbstractMap.SimpleEntry<>(key, value);
    }
}

package chap11;

import java.util.Optional;
import java.util.Properties;

public class ReadPositiveIntParam {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("a", "5");
        props.setProperty("b", "true");
        props.setProperty("c", "-3");

        System.out.println(readDurationImperative(props, "a"));         // 5
        System.out.println(readDurationImperative(props, "b"));         // 0
        System.out.println(readDurationImperative(props, "c"));         // 0
        System.out.println(readDurationImperative(props, "d"));         // 0


        System.out.println(readDurationWithOptional(props, "a"));       // 5
        System.out.println(readDurationWithOptional(props, "b"));       // 0
        System.out.println(readDurationWithOptional(props, "c"));       // 0
        System.out.println(readDurationWithOptional(props, "d"));       // 0
    }


    public static int readDurationImperative(Properties props, String name) {
        String value = props.getProperty(name);
        if (value != null) {
            try {
                int i = Integer.parseInt(value);
                if (i > 0) {
                    return i;
                }
            } catch (NumberFormatException e) {}
        }

        return 0;
    }

    public static int readDurationWithOptional(Properties props, String name) {
        return Optional.ofNullable(props.getProperty(name))
                .flatMap(ReadPositiveIntParam::stringToInteger) // Optional<String> -> Optional<Optional<Integer>> -> Optional<Integer>
                .filter(i -> i > 0)
                .orElse(0);
    }

    private static Optional<Integer> stringToInteger(String s) {     // 문자열을 정수로 변환하는 메서드
        try {
            return Optional.of(Integer.parseInt(s));        // 숫자인 경우, Optional 객체 내부에 넣어 반환
        } catch (NumberFormatException e) {
            return Optional.empty();                        // 숫자가 아닌 경우, 빈 Optional 객체 반환
        }
    }
}

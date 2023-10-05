package chap8;

import java.util.*;

public class WorkingWithCollections {

    public static void main(String[] args) {
        removeIfExample();
        replaceAllExample();
        sortExample();

        forEachExample();
        mapSortExample();
        getOrDefaultExample();

        computeIfAbsentExample();
        removeExample();
        relaceAllExample();

        replaceExample();
        removeIfMapExample();
    }

    private static void removeIfMapExample() {
        Map<String, Integer> example = new HashMap<>(Map.of("hyun", 27, "yoon", 26, "yeon", 25));

        example.entrySet().removeIf(entry -> entry.getValue() < 26);
        System.out.println(example);            // {hyun=27, yoon=26}
    }

    private static void replaceExample() {
        Map<String, Integer> example = new HashMap<>(Map.of("hyun", 27, "yoon", 26, "yeon", 25));

        Integer hyunAge = example.replace("hyun", 26);
        System.out.println(hyunAge);                        // 27 (변경 전의 값을 가져온다.)
        System.out.println(example);                        // {hyun=26, yeon=25, yoon=26}

        boolean changed = example.replace("yoon", 10, 100);
        System.out.println(changed);                        // false
        System.out.println(example);                        // {hyun=26, yeon=25, yoon=26}

        boolean changed2 = example.replace("yoon", 26, 100);
        System.out.println(changed2);                       // true
        System.out.println(example);                        // {yeon=25, hyun=26, yoon=100}
    }

    private static void relaceAllExample() {
        Map<String, Integer> example = new HashMap<>(Map.of("hyun", 27, "yoon", 26, "yeon", 25));
        example.replaceAll((key, value) -> key.length() * 100 + value);

        System.out.println(example);    // {hyun=427, yeon=425, yoon=426}
    }

    private static void removeExample() {
        Map<String, Integer> example = new HashMap<>(Map.of("hyun", 27, "yoon", 26, "yeon", 25));

        example.remove("hyun");         // key가 존재하면 바로 삭제
        System.out.println(example);        // {yeon=25, yoon=26}

        example.remove("yoon", 25);         // key에 대응되는 value 가 일치하지 않는다.
        System.out.println(example);        // {yeon=25, yoon=26}

        example.remove("yoon", 26);         // key에 대응되는 value 가 일치한다.
        System.out.println(example);        // {yeon=25}
    }

    private static void computeIfAbsentExample() {
        Map<String, Integer> example = new HashMap<>() {{
            put("hyun", 27);
            put("yoon", 26);
            put("yeon", 25);
            put("ayaan", null);
        }};                                         // {yeon=25, ayaan=null, hyun=27, yoon=26}

        // key 에 대응되는 value 가 존재하는 경우
        Integer hyunAge = example.computeIfAbsent("hyun", (key) -> {
            System.out.println(key + "가 없으므로 계산중!");
            return key.length() * 10;
        });

        System.out.println(hyunAge);                // 27
        System.out.println(example);                // {yeon=25, ayaan=null, hyun=27, yoon=26}


        // key 가 존재하지 않는 경우 (value 가 존재하지 않는 경우)
        Integer sullyAge = example.computeIfAbsent("sully", (key) -> {
            System.out.println(key + "가 없으므로 계산중!");
            return key.length() * 10;
        });                                            // sully가 없으므로 계산중! 출력

        System.out.println(sullyAge);                // 50
        System.out.println(example);                 // {yeon=25, ayaan=null, hyun=27, sully=50, yoon=26}


        // key 가 존재하나 대응되는 value 가 null 인 경우
        Integer ayaanAge = example.computeIfAbsent("ayaan", (key) -> {
            System.out.println(key + "가 없으므로 계산중!");
            return key.length() * 10;
        });                                            // ayaan가 없으므로 계산중! 출력

        System.out.println(ayaanAge);               // 50
        System.out.println(example);                // {yeon=25, ayaan=50, hyun=27, sully=50, yoon=26}
    }

    private static void getOrDefaultExample() {
        Map<String, Integer> example = Map.of("hyun", 27, "yoon", 26, "yeon", 25);

        Integer hyunAge = example.getOrDefault("hyun", -1);
        System.out.println(hyunAge);        // 27

        Integer ayaanAge = example.getOrDefault("ayaan", -1);
        System.out.println(ayaanAge);       // -1
    }

    private static void mapSortExample() {
        Map<String, Integer> example = Map.of("hyun", 27, "yoon", 26, "yeon", 25);

        example.entrySet()                                  // Map<..> -> Set<Entry<..>>
                .stream()                                   // Set<Entry<..>> -> Stream<Entry<..>>
                .sorted(Map.Entry.comparingByKey())
                .forEach(System.out::println);              // hyun=27
                                                            // yeon=25
                                                            // yoon=26

        example.entrySet()                                  // Map<..> -> Set<Entry<..>>
                .stream()                                   // Set<Entry<..>> -> Stream<Entry<..>>
                .sorted(Map.Entry.comparingByValue())
                .forEach(System.out::println);              // yeon=25
                                                            // yoon=26
                                                            // hyun=27
    }

    private static void forEachExample() {
        Map<String, Integer> example = Map.of("hyun", 27, "yoon", 26, "yeon", 26);

        example.forEach((k, v) -> System.out.println(k + " is " + v + " years old"));   // yeon is 26 years old
        // yoon is 26 years old
        // hyun is 27 years old
    }

    private static void sortExample() {
        ArrayList<String> example = new ArrayList<>(List.of("hyun", "yoon", "yeon", "hyun"));

        example.sort((s1, s2) -> s1.compareTo(s2));

        System.out.println(example);        // [hyun, hyun, yeon, yoon]
    }

    private static void replaceAllExample() {
        ArrayList<String> example = new ArrayList<>(List.of("hyun", "yoon", "yeon", "hyun"));

        example.replaceAll(each -> each + "2");

        System.out.println(example);        // [hyun2, yoon2, yeon2, hyun2]
    }

    private static void removeIfExample() {
        ArrayList<String> example = new ArrayList<>(List.of("hyun", "yoon", "yeon", "hyun"));

//        for (String each : example) {               // 예외 발생
//            if (each.equals("hyun")) {
//                example.remove("hyun");
//            }
//        }

//        for (Iterator<String> iterator = example.iterator(); iterator.hasNext();) {   // 예외 발생
//            String each = iterator.next();
//            if (each.equals("hyun")) {
//                example.remove("hyun");
//            }
//        }

//        for (Iterator<String> iterator = example.iterator(); iterator.hasNext(); ) {
//            String each = iterator.next();
//            if (each.equals("hyun")) {
//                iterator.remove();
//            }
//        }

        example.removeIf(each -> each.equals("hyun"));

        System.out.println(example);        // [yoon, yeon]
    }
}

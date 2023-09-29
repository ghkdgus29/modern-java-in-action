package chap5;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Practice {

    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        // 2011 년에 일어난 모든 트랜잭션을 찾아서 값을 오름차순으로 정렬하시오.
        List<Transaction> one = transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());

        System.out.println(one);

        // 거래자가 근무하는 모든 도시를 중복 없이 나열하시오.
        List<String> two = transactions.stream()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());

        System.out.println(two);


        // 케임브리지에서 근무하는 모든 거래자를 찾아서 이름순으로 정렬하시오.
        List<Trader> three = transactions.stream()
                .map(Transaction::getTrader)
                .distinct()
                .filter(t -> t.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());

        System.out.println(three);

        // 모든 거래자의 이름을 알파벳순으로 정렬해서 반환하시오.
        List<String> four = transactions.stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        System.out.println(four);

        // 밀라노에 거래자가 있는가?
        boolean five = transactions.stream()
                .anyMatch(t -> t.getTrader().getCity().equals("Milan"));

        System.out.println(five);

        // 케임브리지에 거주하는 거래자의 모든 트랜잭션값을 출력하시오
        transactions.stream()
                .filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);

        // 전체 트랜잭션 중 최댓값은 얼마인가?
        Optional<Integer> seven = transactions.stream()
                .map(Transaction::getValue)
                .reduce((max, cur) -> Integer.max(max, cur));

        System.out.println(seven);

        // 전체 트랜잭션 중 최솟값은 얼마인가?
        Optional<Integer> eight = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::min);

        System.out.println(eight);
    }


}

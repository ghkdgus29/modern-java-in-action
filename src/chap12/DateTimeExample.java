package chap12;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class DateTimeExample {

    public static void main(String[] args) {
        ofExample();
        nowExample();
        getInformation();
        parse();
        combination();
        extract();
        epochExample();
        betweenExample();
        withExample();
        plusMinusExample();
        temporalAdjustersExample();
        customAdjusterExample();

        formatExample();
        parseExample();

        customFormatting();
    }

    private static void customFormatting() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");        // 커스텀 포맷터 생성
        LocalDate date = LocalDate.of(2023, 10, 11);
        String formattedDate = date.format(formatter);                                  // 날짜 -> 문자열로 포맷팅

        System.out.println(formattedDate);                      // 11/10/2023

        LocalDate parsedDate = LocalDate.parse(formattedDate, formatter);               // 문자열 -> 날짜로 파싱

        System.out.println(parsedDate.equals(date));            // true
    }

    private static void parseExample() {
        LocalDate date1 = LocalDate.parse("20231011", DateTimeFormatter.BASIC_ISO_DATE);
        LocalDate date2 = LocalDate.parse("2023-10-11", DateTimeFormatter.ISO_LOCAL_DATE);

        System.out.println(date1);      // 2023-10-11
        System.out.println(date2);      // 2023-10-11
    }

    private static void formatExample() {
        LocalDate date = LocalDate.of(2023, 10, 11);
        String s1 = date.format(DateTimeFormatter.BASIC_ISO_DATE);
        String s2 = date.format(DateTimeFormatter.ISO_LOCAL_DATE);

        System.out.println(s1);     // 20231011
        System.out.println(s2);     // 2023-10-11
    }

    private static void customAdjusterExample() {
        LocalDate thursday = LocalDate.of(2023, 10, 12);
        LocalDate nextWorkingDay1 = thursday.with(new NextWorkingDay());

        System.out.println(nextWorkingDay1);        // 2023-10-13


        LocalDate friday = LocalDate.of(2023, 10, 13);
        LocalDate nextWorkingDay2 = friday.with(new NextWorkingDay());

        System.out.println(nextWorkingDay2);        // 2023-10-16


        LocalDate saturday = LocalDate.of(2023, 10, 14);
        LocalDate nextWorkingDay3 = saturday.with(new NextWorkingDay());

        System.out.println(nextWorkingDay3);        // 2023-10-16
    }

    private static void temporalAdjustersExample() {
        LocalDate date = LocalDate.of(2023, 10, 11);
        LocalDate nextSunday = date.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        System.out.println(nextSunday);     // 2023-10-15

        LocalDate lastDay = date.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(lastDay);        // 2023-10-31
    }

    private static void plusMinusExample() {
        LocalDate date = LocalDate.of(2013, 3, 18);
        date = date.plusYears(2).minusDays(10);

        System.out.println(date);       // 2015-3-8
    }

    private static void withExample() {
        LocalDateTime dateTime = LocalDateTime.of(2023, 10, 11, 20, 53, 41);
        LocalDateTime dt1 = dateTime.withYear(2024);
        LocalDateTime dt2 = dateTime.withMonth(2);
        LocalDateTime dt3 = dateTime.withDayOfMonth(20);
        LocalDateTime dt4 = dateTime.withHour(7);

        System.out.println(dt1);        // 2024-10-11T20:53:41
        System.out.println(dt2);        // 2023-02-11T20:53:41
        System.out.println(dt3);        // 2023-10-20T20:53:41
        System.out.println(dt4);        // 2023-10-11T07:53:41
    }

    private static void betweenExample() {
        LocalTime time1 = LocalTime.of(21, 40, 0);
        LocalTime time2 = LocalTime.of(21, 57, 0);
        Duration duration = Duration.between(time1, time2);

        System.out.println(duration);       // PT17M


        LocalDate date1 = LocalDate.of(2023, 10, 11);
        LocalDate date2 = LocalDate.of(2023, 10, 13);
        Period period = Period.between(date1, date2);

        System.out.println(period);         // P2D
        int days = period.getDays();
    }

    private static void epochExample() {
        Instant instant1 = Instant.ofEpochSecond(3);
        Instant instant2 = Instant.ofEpochSecond(2, 10_0000_0000);      // 2초 + 10억 나노 초 (1초)
        Instant instant3 = Instant.ofEpochSecond(4, -10_0000_0000);     // 4초 - 10억 나노 초 (1초)

        System.out.println(instant1.equals(instant2));      // true
        System.out.println(instant2.equals(instant3));      // true
        System.out.println(instant1);                       // 1970-01-01T00:00:03Z (3초)
    }

    private static void extract() {
        LocalDateTime dateTime = LocalDateTime.of(2023, 10, 11, 20, 53, 41);
        LocalDate date = dateTime.toLocalDate();
        LocalTime time = dateTime.toLocalTime();

        System.out.println(date);       // 2023-10-11
        System.out.println(time);       // 20:53:41
    }

    private static void combination() {
        LocalDate date = LocalDate.of(2023, 10, 11);
        LocalTime time = LocalTime.of(20, 53, 41);

        LocalDateTime dateTime1 = date.atTime(time);
        LocalDateTime dateTime2 = time.atDate(date);

        System.out.println(dateTime1);                          // 2023-10-11T21:10:45
        System.out.println(dateTime2);                          // 2023-10-11T21:10:45
        System.out.println(dateTime1.equals(dateTime2));        // true
    }

    private static void parse() {
        LocalDate date = LocalDate.parse("2023-10-11");
        LocalTime time = LocalTime.parse("21:10:45");
        LocalDateTime dateTime = LocalDateTime.parse("2023-10-11T21:10:45");

        System.out.println(date);
        System.out.println(time);
        System.out.println(dateTime);
    }

    private static void getInformation() {
        LocalDateTime dateTime = LocalDateTime.of(2023, 10, 11, 20, 53, 41);
        int year = dateTime.getYear();
        int month = dateTime.getMonthValue();
        int day = dateTime.getDayOfMonth();
        int hour = dateTime.getHour();

        System.out.println(year);       // 2023
        System.out.println(month);      // 10
        System.out.println(day);        // 11
        System.out.println(hour);       // 20
    }

    private static void nowExample() {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        LocalDateTime dateTime = LocalDateTime.now();

        System.out.println(date);       // 2023-10-11
        System.out.println(time);       // 20:57:34.850065800
        System.out.println(dateTime);   // 2023-10-11T20:57:34.850065800
    }

    private static void ofExample() {
        LocalDate date = LocalDate.of(2023, 10, 11);
        LocalTime time = LocalTime.of(20, 53, 41);
        LocalDateTime dateTime = LocalDateTime.of(2023, 10, 11, 20, 53, 41);

        System.out.println(date);       // 2023-10-11
        System.out.println(time);       // 20:53:41
        System.out.println(dateTime);   // 2023-10-11T20:53:41
    }
}

package chap3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ExecuteAround {

    private static final String FILE = ExecuteAround.class.getResource("./data.txt").getFile();

    public static void main(String[] args) throws IOException {
        String oneLine = processFile((BufferedReader b) -> b.readLine());      // 한 줄만 읽는 동작 전달
        System.out.println(oneLine);                    // Java

        System.out.println();

        String twoLines = processFile((BufferedReader b) -> b.readLine() + b.readLine());      // 두 줄을 읽는 동작 전달
        System.out.println(twoLines);                   // Java8
    }

    public static String processFile(BufferedReaderProcessor p) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {        // 자원 준비, 정리과정
            return p.process(br);       // 핵심 로직 동작
        }
    }
}

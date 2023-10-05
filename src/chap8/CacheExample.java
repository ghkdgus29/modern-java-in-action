package chap8;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CacheExample {

    private MessageDigest messageDigest;

    public CacheExample() {
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CacheExample cacheExample = new CacheExample();

        List<String> lines = Arrays.asList(
                " Nel   mezzo del cammin  di nostra  vita ",
                "mi  ritrovai in una  selva oscura",
                " che la  dritta via era   smarrita "
        );
        Map<String, byte[]> dataToHash = cacheExample.transformDataToHash(lines);

        dataToHash.forEach((line, hash) -> System.out.println(line + " -> " + hash));
    }

    public Map<String, byte[]> transformDataToHash(List<String> lines) {
        Map<String, byte[]> dataToHash = new HashMap<>();
        lines.forEach(line ->
                dataToHash.computeIfAbsent(line, string -> calculateDigest(string)));

        return dataToHash;
    }

    private byte[] calculateDigest(String key) {
        System.out.println("계산중!");
        return messageDigest.digest(key.getBytes(StandardCharsets.UTF_8));
    }
}

package com.acciojobs.book_my_show.utilitis;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class SystemUtility {
    private static final Random RANDOM = new Random();

    public synchronized static String generate(String prefix) {
        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

        int random = 1000 + RANDOM.nextInt(9000);

        return prefix + "-" + timestamp + "-" + random;
    }
}

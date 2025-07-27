package org.dows.uim.util;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;

/**
 * @author tangsm
 * @data 2025/7/21 星期一
 */
public class DateTimeFormatterUtil {

    /**
     * 表示25年第213天12点34分56秒789毫秒
     * @return String
     */
    public static String generateTimestamp() {
        LocalDateTime now = LocalDateTime.now();
        return String.format("%02d%03d%02d%02d%02d%03d",
                now.getYear() % 100,    // 取年份后两位
                now.getDayOfYear(),
                now.getHour(),
                now.getMinute(),
                now.getSecond(),
                now.get(ChronoField.MILLI_OF_SECOND));
    }

    public static void main(String[] args) {
        System.out.println(generateTimestamp());
    }
}

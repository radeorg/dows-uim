package org.dows.uim.util;

/**
 * @author tangsm
 * @data 2025/7/21 星期一
 */
public class NumberIncrementUtil {
    /**
     * 提取并递增字符串末尾数字
     * @param input 输入字符串
     * @return 纯数字字符串(保持原位数格式)
     */
    public static String incrementTrailingNumber(String input) {
        if (input == null) return "01";

        java.util.regex.Matcher matcher =
                java.util.regex.Pattern.compile("(\\d+)$").matcher(input);

        if (matcher.find()) {
            String numStr = matcher.group(1);
            int num = Integer.parseInt(numStr) + 1;
            return String.format("%0" + numStr.length() + "d", num);
        }
        return "01";
    }

    public static void main(String[] args) {
        System.out.println(incrementTrailingNumber("公司01"));  // 02
        System.out.println(incrementTrailingNumber("部门10"));  // 11
        System.out.println(incrementTrailingNumber("无数字"));   // 01
        System.out.println(incrementTrailingNumber("测试005")); // 006
    }
}

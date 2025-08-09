package org.dows.uim.util;

import lombok.extern.slf4j.Slf4j;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * @author tangsm
 * @data 2025/7/21 星期一
 */
@Slf4j
public class CompanyNameUtil {
    // 需要过滤的常见公司后缀
    private static final String[] FILTER_SUFFIXES = {
            "有限公司", "有限责任公司", "股份公司", "集团有限公司", "科技公司"
    };

    public static String getFirstLetters(String companyName) {
        // 1. 过滤公司后缀
        String filteredName = filterSuffix(companyName);

        // 2. 检查是否为纯英文名称
        if (isPureEnglish(filteredName)) {
            return filteredName;
        }

        // 3. 获取拼音首字母
        StringBuilder result = new StringBuilder();
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

        for (char c : filteredName.toCharArray()) {
            if (Character.toString(c).matches("[\\u4E00-\\u9FA5]")) {
                try {
                    String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c, format);
                    if (pinyinArray != null && pinyinArray.length > 0) {
                        result.append(pinyinArray[0].charAt(0));
                    }
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    log.error("获取拼音首字母失败", e);
                }
            } else if (Character.isLetter(c)) {
                result.append(Character.toLowerCase(c));
            }
        }
        return result.toString();
    }

    private static String filterSuffix(String name) {
        for (String suffix : FILTER_SUFFIXES) {
            if (name.endsWith(suffix)) {
                return name.substring(0, name.length() - suffix.length());
            }
        }
        return name;
    }

    private static boolean isPureEnglish(String name) {
        for (char c : name.toCharArray()) {
            if (Character.toString(c).matches("[\\u4E00-\\u9FA5]")) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(getFirstLetters("阿里巴巴有限公司")); // 输出: ALBB
        System.out.println(getFirstLetters("Tencent科技公司")); // 输出: TENCENT
    }
}

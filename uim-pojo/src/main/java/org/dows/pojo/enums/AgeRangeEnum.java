package org.dows.pojo.enums;

import lombok.Getter;
/**
 * @ClassName AgeRangeEnum
 * @Description TODO
 * @Author jack.china.ye
 * @Date 2025/6/3 22:13
 */


@Getter
public enum AgeRangeEnum {

    UNLIMITED(0, "不限"),
    AGE_25_30(1, "25-30岁"),
    AGE_30_35(2, "30-35岁"),
    AGE_35_40(3, "35-40岁"),
    ABOVE_40(4, "40岁以上");

    private final int code;
    private final String description;

    AgeRangeEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static AgeRangeEnum getByCode(int code) {
        for (AgeRangeEnum type : AgeRangeEnum.values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid AgeRangeEnum code: " + code);
    }

    public static int getCodeByDescription(String description) {
        for (AgeRangeEnum type : AgeRangeEnum.values()) {
            if (type.getDescription().equals(description)) {
                return type.getCode();
            }
        }
        throw new IllegalArgumentException("Invalid AgeRangeEnum description: " + description);
    }

    public static AgeRangeEnum getByDescription(String description) {
        for (AgeRangeEnum type : AgeRangeEnum.values()) {
            if (type.getDescription().equals(description)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid AgeRangeEnum description: " + description);
    }
}

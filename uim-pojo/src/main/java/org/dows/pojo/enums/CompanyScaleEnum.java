package org.dows.pojo.enums;

import lombok.Getter;

/**
 * @ClassName CompanyScaleEnum
 * @Description TODO
 * @Author jack.china.ye
 * @Date 2025/6/3 22:16
 */


@Getter
public enum CompanyScaleEnum {

    ONE_TO_TEN(1, "1-10人"),
    TEN_TO_FIFTY(2, "10-50人"),
    FIFTY_ONE_TO_TWO_HUNDRED(3, "51-200人"),
    TWO_HUNDRED_ONE_TO_FIVE_HUNDRED(4, "201-500人"),
    FIVE_HUNDRED_ONE_TO_THOUSAND(5, "501-1000人"),
    ABOVE_THOUSAND(6, "1000人以上");

    private final int code;
    private final String description;

    CompanyScaleEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static CompanyScaleEnum getByCode(int code) {
        for (CompanyScaleEnum type : CompanyScaleEnum.values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid CompanyScaleEnum code: " + code);
    }

    public static int getCodeByDescription(String description) {
        for (CompanyScaleEnum type : CompanyScaleEnum.values()) {
            if (type.getDescription().equals(description)) {
                return type.getCode();
            }
        }
        throw new IllegalArgumentException("Invalid CompanyScaleEnum description: " + description);
    }

    public static CompanyScaleEnum getByDescription(String description) {
        for (CompanyScaleEnum type : CompanyScaleEnum.values()) {
            if (type.getDescription().equals(description)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid CompanyScaleEnum description: " + description);
    }
}


package org.dows.pojo.enums;

import lombok.Getter;

/**
 * @ClassName CoreBenefitsEnum
 * @Description TODO
 * @Author jack.china.ye
 * @Date 2025/6/4 22:48
 */


@Getter
public enum CoreBenefitsEnum {

    SOCIAL_INSURANCE(0, "五险一金"),
    SUPPLEMENTARY_MEDICAL(1, "补充医疗"),
    ANNUAL_PHYSICAL(2, "年度体检"),
    EQUITY_INCENTIVE(3, "股权激励"),
    YEAR_END_BONUS(4, "年终奖"),
    PAID_ANNUAL_LEAVE(5, "带薪年假"),
    CUSTOM(6, "自定义");

    private final int code;
    private final String description;

    CoreBenefitsEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static CoreBenefitsEnum getByCode(int code) {
        for (CoreBenefitsEnum benefit : CoreBenefitsEnum.values()) {
            if (benefit.getCode() == code) {
                return benefit;
            }
        }
        throw new IllegalArgumentException("Invalid CoreBenefitsEnum code: " + code);
    }

    public static int getCodeByDescription(String description) {
        for (CoreBenefitsEnum benefit : CoreBenefitsEnum.values()) {
            if (benefit.getDescription().equals(description)) {
                return benefit.getCode();
            }
        }
        return CUSTOM.code;
    }

    public static String getUstomizeByDescription(String description) {
        for (CoreBenefitsEnum benefit : CoreBenefitsEnum.values()) {
            if (benefit.getDescription().equals(description)) {
                return "";
            }
        }
        return description;
    }

    public static CoreBenefitsEnum getByDescription(String description) {
        for (CoreBenefitsEnum benefit : CoreBenefitsEnum.values()) {
            if (benefit.getDescription().equals(description)) {
                return benefit;
            }
        }
        return CUSTOM;
    }
}


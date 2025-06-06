package org.dows.pojo.enums;

import lombok.Getter;

/**
 * @ClassName FinancingStageEnum
 * @Description TODO
 * @Author jack.china.ye
 * @Date 2025/6/3 22:17
 */

/**
 * 融资阶段
 */
@Getter
public enum FinancingStageEnum {

    NOT_FINANCED(1, "未融资"),
    ANGEL_ROUND(2, "天使轮"),
    PRE_A_ROUND(3, "Pre-A轮"),
    A_ROUND(4, "A轮"),
    B_ROUND(5, "B轮"),
    C_OR_ABOVE(6, "C轮及以上"),
    PROFITABLE(7, "已盈利");

    private final int code;
    private final String description;

    FinancingStageEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static FinancingStageEnum getByCode(int code) {
        for (FinancingStageEnum stage : FinancingStageEnum.values()) {
            if (stage.getCode() == code) {
                return stage;
            }
        }
        throw new IllegalArgumentException("Invalid FinancingStageEnum code: " + code);
    }

    public static int getCodeByDescription(String description) {
        for (FinancingStageEnum stage : FinancingStageEnum.values()) {
            if (stage.getDescription().equals(description)) {
                return stage.getCode();
            }
        }
        throw new IllegalArgumentException("Invalid FinancingStageEnum description: " + description);
    }

    public static FinancingStageEnum getByDescription(String description) {
        for (FinancingStageEnum stage : FinancingStageEnum.values()) {
            if (stage.getDescription().equals(description)) {
                return stage;
            }
        }
        throw new IllegalArgumentException("Invalid FinancingStageEnum description: " + description);
    }
}


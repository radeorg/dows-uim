package org.dows.pojo.enums;
import lombok.Getter;

/**
 * @ClassName MonthlySalaryRangeEnum
 * @Description TODO
 * @Author jack.china.ye
 * @Date 2025/6/3 22:23
 */


@Getter
public enum MonthlySalaryRangeEnum {

    BELOW_10K(1, "1万以下"),
    FROM_10K_TO_15K(2, "1-1.5万"),
    FROM_15K_TO_20K(3, "1.5-2万"),
    FROM_20K_TO_25K(4, "2-2.5万"),
    FROM_25K_TO_30K(5, "2.5-3万"),
    NEGOTIABLE(6, "面议");

    private final int code;
    private final String description;

    MonthlySalaryRangeEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static MonthlySalaryRangeEnum getByCode(int code) {
        for (MonthlySalaryRangeEnum range : MonthlySalaryRangeEnum.values()) {
            if (range.getCode() == code) {
                return range;
            }
        }
        throw new IllegalArgumentException("Invalid MonthlySalaryRangeEnum code: " + code);
    }

    public static int getCodeByDescription(String description) {
        for (MonthlySalaryRangeEnum range : MonthlySalaryRangeEnum.values()) {
            if (range.getDescription().equals(description)) {
                return range.getCode();
            }
        }
        throw new IllegalArgumentException("Invalid MonthlySalaryRangeEnum description: " + description);
    }

    public static MonthlySalaryRangeEnum getByDescription(String description) {
        for (MonthlySalaryRangeEnum range : MonthlySalaryRangeEnum.values()) {
            if (range.getDescription().equals(description)) {
                return range;
            }
        }
        throw new IllegalArgumentException("Invalid MonthlySalaryRangeEnum description: " + description);
    }
}


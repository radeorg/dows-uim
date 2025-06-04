package org.dows.pojo.enums;

import lombok.Getter;

/**
 * @ClassName GenderRequirementEnum
 * @Description TODO
 * @Author jack.china.ye
 * @Date 2025/6/3 22:08
 */
@Getter
public enum GenderRequirementEnum {

    UNLIMITED(0, "不限"),
    MALE(1, "男"),
    FEMALE(2, "女"),
    PREFER_MALE(3, "优先男性"),
    PREFER_FEMALE(4, "优先女性");

    private final int code;
    private final String description;

    GenderRequirementEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static GenderRequirementEnum getByCode(int code) {
        for (GenderRequirementEnum type : GenderRequirementEnum.values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid GenderRequirementEnum code: " + code);
    }

    public static int getCodeByDescription(String description) {
        for (GenderRequirementEnum type : GenderRequirementEnum.values()) {
            if (type.getDescription().equals(description)) {
                return type.getCode();
            }
        }
        throw new IllegalArgumentException("Invalid GenderRequirementEnum description: " + description);
    }

    public static GenderRequirementEnum getByDescription(String description) {
        for (GenderRequirementEnum type : GenderRequirementEnum.values()) {
            if (type.getDescription().equals(description)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid GenderRequirementEnum description: " + description);
    }
}

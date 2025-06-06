package org.dows.pojo.enums;

import lombok.Getter;

/**
 * @ClassName WorkExperienceEnum
 * @Description TODO
 * @Author jack.china.ye
 * @Date 2025/6/3 22:14
 */

/**
 * 工作经验
 */
@Getter
public enum WorkExperienceEnum {

    UNLIMITED(0, "不限"),
    FRESH_GRADUATE(1, "应届"),
    ONE_TO_THREE_YEARS(2, "1-3年"),
    THREE_TO_FIVE_YEARS(3, "3-5年"),
    FIVE_TO_TEN_YEARS(4, "5-10年"),
    ABOVE_TEN_YEARS(5, "10年以上");

    private final int code;
    private final String description;

    WorkExperienceEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static WorkExperienceEnum getByCode(int code) {
        for (WorkExperienceEnum type : WorkExperienceEnum.values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid WorkExperienceEnum code: " + code);
    }

    public static int getCodeByDescription(String description) {
        for (WorkExperienceEnum type : WorkExperienceEnum.values()) {
            if (type.getDescription().equals(description)) {
                return type.getCode();
            }
        }
        throw new IllegalArgumentException("Invalid WorkExperienceEnum description: " + description);
    }

    public static WorkExperienceEnum getByDescription(String description) {
        for (WorkExperienceEnum type : WorkExperienceEnum.values()) {
            if (type.getDescription().equals(description)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid WorkExperienceEnum description: " + description);
    }
}

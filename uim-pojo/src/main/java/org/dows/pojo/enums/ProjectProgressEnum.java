package org.dows.pojo.enums;

import lombok.Getter;

/**
 * @ClassName ProjectProgressEnum
 * @Description TODO
 * @Author jack.china.ye
 * @Date 2025/6/3 22:20
 */


@Getter
public enum ProjectProgressEnum {

    DESIGN_PHASE(1, "设计阶段"),
    DEVELOPMENT_PHASE(2, "开发阶段"),
    ONLINE_NOT_PROFITABLE(3, "已上线(未盈利)"),
    ONLINE_PROFITABLE(4, "已上线(盈利)"),
    MARKET_VALIDATION_PHASE(5, "市场验证阶段");

    private final int code;
    private final String description;

    ProjectProgressEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static ProjectProgressEnum getByCode(int code) {
        for (ProjectProgressEnum phase : ProjectProgressEnum.values()) {
            if (phase.getCode() == code) {
                return phase;
            }
        }
        throw new IllegalArgumentException("Invalid ProjectProgressEnum code: " + code);
    }

    public static int getCodeByDescription(String description) {
        for (ProjectProgressEnum phase : ProjectProgressEnum.values()) {
            if (phase.getDescription().equals(description)) {
                return phase.getCode();
            }
        }
        throw new IllegalArgumentException("Invalid ProjectProgressEnum description: " + description);
    }

    public static ProjectProgressEnum getByDescription(String description) {
        for (ProjectProgressEnum phase : ProjectProgressEnum.values()) {
            if (phase.getDescription().equals(description)) {
                return phase;
            }
        }
        throw new IllegalArgumentException("Invalid ProjectProgressEnum description: " + description);
    }
}


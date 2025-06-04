package org.dows.pojo.enums;
import lombok.Getter;

/**
 * @ClassName WorkModeEnum
 * @Description TODO
 * @Author jack.china.ye
 * @Date 2025/6/3 22:12
 */


@Getter
public enum WorkModeEnum {

    ON_SITE(1, "全职坐班"),
    HYBRID(2, "混合办公"),
    REMOTE(3, "全员远程");

    private final int code;
    private final String description;

    WorkModeEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static WorkModeEnum getByCode(int code) {
        for (WorkModeEnum type : WorkModeEnum.values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid WorkModeEnum code: " + code);
    }

    public static int getCodeByDescription(String description) {
        for (WorkModeEnum type : WorkModeEnum.values()) {
            if (type.getDescription().equals(description)) {
                return type.getCode();
            }
        }
        throw new IllegalArgumentException("Invalid WorkModeEnum description: " + description);
    }

    public static WorkModeEnum getByDescription(String description) {
        for (WorkModeEnum type : WorkModeEnum.values()) {
            if (type.getDescription().equals(description)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid WorkModeEnum description: " + description);
    }
}


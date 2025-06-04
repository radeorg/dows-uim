package org.dows.pojo.enums;
import lombok.Getter;

/**
 * @ClassName YesOrNoEnum
 * @Description TODO
 * @Author jack.china.ye
 * @Date 2025/6/3 22:10
 */


@Getter
public enum YesOrNoEnum {

    YES(1, "是"),
    NO(2, "否");

    private final int code;
    private final String description;

    YesOrNoEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static YesOrNoEnum getByCode(int code) {
        for (YesOrNoEnum type : YesOrNoEnum.values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid YesOrNoEnum code: " + code);
    }

    public static int getCodeByDescription(String description) {
        for (YesOrNoEnum type : YesOrNoEnum.values()) {
            if (type.getDescription().equals(description)) {
                return type.getCode();
            }
        }
        throw new IllegalArgumentException("Invalid YesOrNoEnum description: " + description);
    }

    public static YesOrNoEnum getByDescription(String description) {
        for (YesOrNoEnum type : YesOrNoEnum.values()) {
            if (type.getDescription().equals(description)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid YesOrNoEnum description: " + description);
    }
}

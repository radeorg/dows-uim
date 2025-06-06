package org.dows.pojo.enums;
import lombok.Getter;

/**
 * @ClassName WorkModeEnum
 * @Description TODO
 * @Author jack.china.ye
 * @Date 2025/6/3 22:12
 */


@Getter
public enum RecruitmentPurposeEnum {

    FILL(1, "填补岗位空缺"),
    EXPAND(2, "业务扩张新增"),
    NEWLY_ADDED(3, "技术升级需求");

    private final int code;
    private final String description;

    RecruitmentPurposeEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static RecruitmentPurposeEnum getByCode(int code) {
        for (RecruitmentPurposeEnum type : RecruitmentPurposeEnum.values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid WorkModeEnum code: " + code);
    }

    public static int getCodeByDescription(String description) {
        for (RecruitmentPurposeEnum type : RecruitmentPurposeEnum.values()) {
            if (type.getDescription().equals(description)) {
                return type.getCode();
            }
        }
        throw new IllegalArgumentException("Invalid WorkModeEnum description: " + description);
    }

    public static RecruitmentPurposeEnum getByDescription(String description) {
        for (RecruitmentPurposeEnum type : RecruitmentPurposeEnum.values()) {
            if (type.getDescription().equals(description)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid WorkModeEnum description: " + description);
    }
}


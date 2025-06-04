package org.dows.pojo.enums;
import lombok.Getter;

/**
 * @ClassName EducationRequirementEnum
 * @Description TODO
 * @Author jack.china.ye
 * @Date 2025/6/3 22:15
 */


@Getter
public enum EducationRequirementEnum {

    UNLIMITED(0, "不限"),
    JUNIOR_COLLEGE_OR_ABOVE(1, "大专及以上"),
    BACHELOR_OR_ABOVE(2, "本科及以上"),
    MASTER_OR_ABOVE(3, "硕士及以上"),
    DOCTOR_OR_ABOVE(4, "博士及以上");

    private final int code;
    private final String description;

    EducationRequirementEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static EducationRequirementEnum getByCode(int code) {
        for (EducationRequirementEnum type : EducationRequirementEnum.values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid EducationRequirementEnum code: " + code);
    }

    public static int getCodeByDescription(String description) {
        for (EducationRequirementEnum type : EducationRequirementEnum.values()) {
            if (type.getDescription().equals(description)) {
                return type.getCode();
            }
        }
        throw new IllegalArgumentException("Invalid EducationRequirementEnum description: " + description);
    }

    public static EducationRequirementEnum getByDescription(String description) {
        for (EducationRequirementEnum type : EducationRequirementEnum.values()) {
            if (type.getDescription().equals(description)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid EducationRequirementEnum description: " + description);
    }
}


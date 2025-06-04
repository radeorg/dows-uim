package org.dows.pojo.enums;

import lombok.Getter;

import lombok.Getter;

/**
 * @ClassName TeamFeaturesEnum
 * @Description TODO
 * @Author jack.china.ye
 * @Date 2025/6/4 22:56
 */


@Getter
public enum TeamFeaturesEnum {

    FLAT_MANAGEMENT(0, "扁平化管理"),
    TECH_DRIVEN(1, "技术驱动型"),
    FLEXIBLE_HOURS(2, "弹性工作时间"),
    NO_OVERTIME_CULTURE(3, "不加班文化"),
    SILICON_VALLEY_STYLE(4, "硅谷工作环境"),
    CUSTOM(5, "自定义");

    private final int code;
    private final String description;

    TeamFeaturesEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static TeamFeaturesEnum getByCode(int code) {
        for (TeamFeaturesEnum feature : TeamFeaturesEnum.values()) {
            if (feature.getCode() == code) {
                return feature;
            }
        }
        throw new IllegalArgumentException("Invalid TeamFeaturesEnum code: " + code);
    }


    public static int getCodeByDescription(String description) {
        for (TeamFeaturesEnum feature : TeamFeaturesEnum.values()) {
            if (feature.getDescription().equals(description)) {
                return feature.getCode();
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

    public static TeamFeaturesEnum getByDescription(String description) {
        for (TeamFeaturesEnum feature : TeamFeaturesEnum.values()) {
            if (feature.getDescription().equals(description)) {
                return feature;
            }
        }
        return CUSTOM;
    }
}


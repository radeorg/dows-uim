package org.dows.uim.constant;


import lombok.Getter;

@Getter
public enum CommonDelEnum {

    NORMAL(0, "正常"),
    DELETE(1, "删除"),
    ;

    private final int code;
    private final String description;

    CommonDelEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }
}

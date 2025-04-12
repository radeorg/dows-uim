package org.dows.uim.constant;

import lombok.Getter;

public enum IdentifierType {

    PHONE(0, "电话"),
    EMAIL(1, "邮箱"),
    WCOPENID(2, "微信openId"),
    ;

    @Getter
    private final int type;
    @Getter
    private final String name;


    IdentifierType(int type, String name) {
        this.type = type;
        this.name = name;
    }

}

package org.dows.uim.constant;

import lombok.Getter;

public enum AccountType {

    NORMAL(0, "普通账号"),
    ORG_RECRUIT_ACCOUNT(1, "ORG招聘账户号"),
    ;

    @Getter
    private final int value;
    @Getter
    private final String name;


    AccountType(int value, String name) {
        this.value = value;
        this.name = name;
    }

}

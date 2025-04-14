package org.dows.uim.constant;

import lombok.Getter;

public enum BindingVerify {
    EMAIL(1, "邮箱"),
    TELEPHONE(2, "手机号"),
    REFERRALS_NO(3, "推荐人");

    @Getter
    private final Integer verify;
    @Getter
    private final String description;

    BindingVerify(int value, String description) {
        this.verify = value;
        this.description = description;
    }
}

package org.dows.uim.api.response;

import lombok.Data;

@Data
public class AccountInstanceResponse {
    private boolean isSuperAccount;
    private String password;
    private String accountName;
}

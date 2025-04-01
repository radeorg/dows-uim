package org.dows.uim.api.response;

import lombok.Data;

@Data
public class AccountInstanceResponse {
    private boolean superAccount;
    private String password;
    private String identifier;
    private Long accountInstanceId;
}

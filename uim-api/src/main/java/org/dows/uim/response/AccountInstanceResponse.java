package org.dows.uim.response;

import lombok.Data;
import org.dows.rade.constant.IdentifierType;

@Data
public class AccountInstanceResponse {
    private boolean superAccount;
    private String password;
    private String identifier;
    private Long accountInstanceId;
    private String nickname;
    private String telephone;
    private IdentifierType identifierType;
}

package org.dows.uim.request;

import lombok.Data;
import org.dows.uim.constant.AccountType;

@Data
public class AddOrgAccountRequest {

    private String orgName;
    private String accountName;
    private AccountType accountType;
    private String telephone;
    private String email;
    private String password;
    private String zoneNo;
}

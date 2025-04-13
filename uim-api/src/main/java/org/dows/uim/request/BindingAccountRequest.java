package org.dows.uim.request;

import lombok.Data;

@Data
public class BindingAccountRequest {
    private Long accountInstanceId;
    private String nickname;
    private String avatar;
    private String telephone;
    private String email;
    private String zoneNo;
    private String referralsNo;
}

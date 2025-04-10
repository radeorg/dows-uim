package org.dows.uim.response;

import lombok.Data;

@Data
public class AccountIdentifierResponse {
    private Long accountIdentifierId;
    private Long accountInstanceId;
    private String identifier;
    private Integer type;
    private String appId;
}
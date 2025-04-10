package org.dows.uim.response;

import lombok.Data;

@Data
public class FindAccountIdentifierRequest {
    private String identifier;
    private String identifierType;
    private Integer state;
    private String appId;
}

package org.dows.uim.api.response;

import lombok.Data;

@Data
public class FindAccountIdentifierRequest {
    private String identifier;
    private String identifierType;
    private Integer state;
    private String appId;
}

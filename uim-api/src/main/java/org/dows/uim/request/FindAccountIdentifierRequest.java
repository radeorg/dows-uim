package org.dows.uim.request;

import lombok.Data;
import org.dows.rade.constant.IdentifierType;

@Data
public class FindAccountIdentifierRequest {
    private Long accountInstanceId;
    private String identifier;
    private IdentifierType identifierType;
    private Integer state;
    private String appId;
}

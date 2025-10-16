package org.dows.uim.request;

import lombok.Data;
import org.dows.rade.constant.IdentifierType;

@Data
public class AccountIdentifierRequest {
    private Long accountIdentifierId;
    private String identifier;
    private Integer deleted;
}

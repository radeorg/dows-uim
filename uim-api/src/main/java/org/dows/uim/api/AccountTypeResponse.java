package org.dows.uim.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Account type response")
@Data
public class AccountTypeResponse {
    @Schema(description = "Account instance ID")
    private Long accountInstanceId;
    @Schema(description = "Account type ID")
    private Long accountTypeId;
    @Schema(description = "Account type")
    private Integer accountType;
}

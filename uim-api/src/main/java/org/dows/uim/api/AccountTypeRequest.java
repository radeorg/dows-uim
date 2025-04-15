package org.dows.uim.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Account Type Request")
@Data
public class AccountTypeRequest {
    @Schema(description = "账号实例ID")
    private Long accountInstanceId;
}

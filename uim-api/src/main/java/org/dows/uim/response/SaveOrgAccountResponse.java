package org.dows.uim.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SaveOrgAccountResponse {

    @Schema(description = "code")
    private Long code;

    @Schema(description = "描述")
    private String message;
}

package org.dows.uim.api.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "规则配置")
@Data
public class RuleConfigResponse {
    @Schema(description = "岗位规则ID")
    private Long orgRuleId;
}

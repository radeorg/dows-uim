package org.dows.uim.api.response;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

@Data
public class RuleConfigResponse {
    @Parameter(description = "岗位规则ID")
    private Long orgRuleId;
}

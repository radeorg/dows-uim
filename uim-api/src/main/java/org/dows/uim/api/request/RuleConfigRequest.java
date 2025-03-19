package org.dows.uim.api.request;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class RuleConfigRequest {
    @Schema(description = "组织树ID")
    @Parameter(description = "岗位规则ID")
    private Long orgRuleId;

    @Parameter(description = "组织树ID")
    private Long orgTreeId;

    @Parameter(description = "规则名称")
    private String ruleName;

    @Parameter(description = "规则描述")
    private String ruleDescription;

    @Parameter(description = "岗位动作")
    private List<OrgAction> orgAction;
}

package org.dows.uim.request;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

import java.util.List;

@Data
public class OrgAction {
    @Parameter(description = "岗位行动ID")
    private Long orgActionId;

    @Parameter(description = "岗位规则ID")
    private Long orgRuleId;

    @Parameter(description = "操作者ID")
    private Long operatorId;

    @Parameter(description = "动作顺序")
    private Integer seq;

    @Parameter(description = "动作名称")
    private String actionName;

    @Parameter(description = "岗位动作描述")
    private String actionDescription;

    @Parameter(description = "岗位指标")
    private List<OrgIndicator> orgIndicator;
}

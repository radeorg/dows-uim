package org.dows.uim.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@Schema(name = "岗位规则对象")
public class OrgRuleSaveRequest {
    @Schema(description = "岗位规则ID")
    private Long orgRuleId;

    @Schema(description = "组织树ID")
    private Long orgTreeId;

    @Schema(description = "规则名称")
    private String ruleName;

    @Schema(description = "规则描述")
    private String ruleDescription;

    @Schema(description = "操作者ID")
    private Long operatorId;

    @Schema(description = "是否可用0-可用，1-不可用")
    private Integer enabled;

    @Schema(description = "应用ID")
    private String appId;

    @Schema(description = "时间戳")
    private Date ts;
}

package org.dows.uim.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/org")
@Tag(name = "管理端组织管理", description = "管理端组织管理")
@RequiredArgsConstructor
public class AdminOrgRest {

    @PostMapping("/role/config")
    @Operation(summary = "组织角色配置")
    public boolean roleConfig(@RequestBody RoleConfigRequest request) {
        // 实现逻辑
        return true; // 示例返回值
    }

    @PostMapping("/rule/config")
    @Operation(summary = "组织配置规则")
    public RuleConfigResponse ruleConfig(@RequestBody RuleConfigRequest request) {
        // 实现逻辑
        return new RuleConfigResponse(); // 示例返回值
    }
}

@Data
class RoleConfigRequest {
    @Parameter(description = "组织树ID")
    private List<Long> orgTreeId;

    @Parameter(description = "角色实例ID")
    private List<Long> rbacRoleId;
}

@Data
class RuleConfigRequest {
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
    private List<LorgAction> lorgAction;
}

@Data
class LorgAction {
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

@Data
class OrgIndicator {
    @Parameter(description = "岗位指标ID")
    private Long orgIndicatorId;

    @Parameter(description = "岗位行动ID")
    private Long orgActionId;

    @Parameter(description = "指标名称")
    private String indicatorName;

    @Parameter(description = "指标关键字")
    private String indicatorKeyword;

    @Parameter(description = "指标分值")
    private Integer indicatorScore;

    @Parameter(description = "指标匹配度")
    private Integer matchScore;
}

@Data
class RuleConfigResponse {
    @Parameter(description = "岗位规则ID")
    private Long orgRuleId;
}
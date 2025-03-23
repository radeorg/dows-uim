package org.dows.uim.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.dows.uim.api.request.RoleConfigRequest;
import org.dows.uim.api.request.RuleConfigRequest;
import org.dows.uim.api.response.RuleConfigResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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


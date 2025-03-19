package org.dows.uim.api.request;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

import java.util.List;

@Data
public class RoleConfigRequest {
    @Parameter(description = "组织树ID")
    private List<Long> orgTreeId;

    @Parameter(description = "角色实例ID")
    private List<Long> rbacRoleId;
}

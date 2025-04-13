package org.dows.uim.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@Schema(name = "岗位动作对象")
public class OrgActionSaveRequest {
    @Schema(description = "岗位行动ID")
    private Long orgActionId;

    @Schema(description = "岗位规则ID")
    private Long orgRuleId;

    @Schema(description = "操作者ID")
    private Long operatorId;

    @Schema(description = "动作顺序")
    private Integer seq;

    @Schema(description = "动作名称")
    private String actionName;

    @Schema(description = "岗位动作描述")
    private String actionDescription;

    @Schema(description = "应用ID")
    private String appId;

    @Schema(description = "时间戳")
    private Date ts;
}

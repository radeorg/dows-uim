package org.dows.uim.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "岗位查询对象")
public class OrgJdQueryRequest {

    @Schema(description = "岗位描述ID")
    private Long orgJdId;

    @Schema(description = "组织树rootID")
    private Long orgRootId;

    @Schema(description = "组织树ID")
    private Long orgTreeId;

    @Schema(description = "岗位名称+岗位编号")
    private String jdName;
}

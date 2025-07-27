package org.dows.uim.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "租户应用信息")
@Data
public class TenantAppResponse {

    /**
     * 租户应用ID
     */
    @Schema(description = "租户应用ID")
    private Long tenantAppId;

    /**
     * 组织登记ID
     */
    @Schema(description = "组织登记ID")
    private Long orgRegisterId;

    /**
     * 应用ID
     */
    @Schema(description = "应用ID")
    private String appId;

    /**
     * 组织空间
     */
    @Schema(description = "组织空间")
    private String namespace;
}

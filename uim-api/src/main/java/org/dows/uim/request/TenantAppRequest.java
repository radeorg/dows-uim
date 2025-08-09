package org.dows.uim.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "租户应用信息")
@Data
public class TenantAppRequest {

    /**
     * 企业名称
     */
    @Schema(description = "企业名称")
    private String companyName;

    /**
     * 组织登记ID
     */
    @Schema(description = "组织登记ID")
    private Long orgRegisterId;

    /**
     * 账号实例ID
     */
    @Schema(description = "账号实例ID")
    private Long accountInstanceId;

    /**
     * 租户实例ID
     */
    @Schema(description = "租户实例ID")
    private Long tenantInstanceId;

    /**
     * 应用ID
     */
    @Schema(description = "应用ID")
    private String appId;
}

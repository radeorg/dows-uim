package org.dows.uim.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "租户实例信息")
@Data
public class TenantInstanceRequest {

    /**
     * 企业名称
     */
    @Schema(description = "企业名称")
    private String companyName;

    /**
     * 账号实例ID
     */
    @Schema(description = "账号实例ID")
    private Long accountInstanceId;

    /**
     * 应用ID
     */
    @Schema(description = "应用ID")
    private String appId;
}

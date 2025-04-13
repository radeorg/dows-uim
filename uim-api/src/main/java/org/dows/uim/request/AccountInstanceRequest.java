package org.dows.uim.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AccountInstanceRequest {

    @Schema(description = "账号实例ID")
    private Long accountInstanceId;

    private int identifierType;

    @Schema(description = "账号标识符")
    private String identifier;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "区域编码(+86，+11...)")
    private String zoneNo;

    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "推荐码")
    private String referralsNo;

    @Schema(description = "来源(来源渠道推广时标记用)")
    private String source;

    @Schema(description = "应用ID")
    private String appId;

}

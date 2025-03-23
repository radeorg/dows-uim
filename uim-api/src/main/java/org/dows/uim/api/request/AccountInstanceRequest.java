package org.dows.uim.api.request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

public class AccountInstanceRequest {

    @Schema(description = "账号实例ID")
    private Long accountInstanceId;

    @Schema(description = "账号标识符")
    private String identifier;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "区域编码(+86，+11...)")
    private String zoneNo;

    @Schema(description = "手机号")
    private String cellphone;

    @Schema(description = "头像")
    private String avator;

    @Schema(description = "推荐码")
    private String referralsNo;

    @Schema(description = "来源(来源渠道推广时标记用)")
    private String source;

    @Schema(description = "应用ID")
    private String appId;

    @Schema(description = "操作者ID")
    private Long operatorId;

    @Schema(description = "乐观锁, 默认: 0")
    private Integer ver;

    @Schema(description = "逻辑删除  0未删除  1 删除")
    private Integer deleted;

    @Schema(description = "时间戳")
    private Date ts;

}

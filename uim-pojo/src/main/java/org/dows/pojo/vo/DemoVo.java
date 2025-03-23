package org.dows.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class DemoVo {

    @Schema(description = "账号标识ID")
    private Long accountIdentifierId;

    /**
     * 账号标识符
     */
    @Schema(description = "账号标识符")
    private String identifier;

    /**
     * 操作者ID
     */
    @Schema(description = "操作者ID")
    private Long operatorId;

    /**
     * 类型[0:账号,1:手机号,2:邮箱,3:第三方token]
     */
    @Schema(description = "类型[0:账号,1:手机号,2:邮箱,3:第三方token]")
    private Integer type;

    /**
     * 乐观锁, 默认: 0
     */
    @Schema(description = "乐观锁, 默认: 0")
    private Integer ver;

    /**
     * 应用ID
     */
    @Schema(description = "应用ID")
    private String appId;

    /**
     * 逻辑删除  0未删除  1 删除
     */
    @Schema(description = "逻辑删除  0未删除  1 删除")
    private Integer deleted;

}

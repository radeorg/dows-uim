package org.dows.uim.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
public class OrgAddressRequest {

    @Schema(description = "地址维度ID")
    private Long orgAddressId;

    @Schema(description = "组织根节点ID")
    private Long orgRootId;

    @Schema(description = "组织树ID")
    private Long orgTreeId;

    @Schema(description = "详细地址")
    private String address;

    @Schema(description = "版本")
    private Integer ver;

    @Schema(description = "状态")
    private Integer state;

    @Schema(description = "操作者ID")
    private Long operatorId;

    @Schema(description = "逻辑删除，0未删除，1删除")
    private Integer deleted;

    @Schema(description = "appId")
    private String appId;

    @Schema(description = "操作时间")
    private Date ts;

    @Schema(description = "更新时间")
    private Date ut;

    @Schema(description = "ownerId")
    private Long ownerId;
}

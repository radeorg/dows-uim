package org.dows.uim.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class HrAccountInstanceRequest {

    /**
     * 昵称
     */
    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "手机号")
    private String telephone;
    /**
     * 组织名/部门名
     */
    @Schema(description = "部门名")
    private String orgName;


    @Schema(description = "应用ID")
    private String appId;
    /**
     * 组织根节点ID
     */
    @Schema(description = "组织根节点ID")
    private Long orgRootId;
    /**
     * 分页大小
     */
    @Schema(description = "分页大小")
    private Integer pageSize;

    /**
     * 当前页数
     */
    @Schema(description = "当前页数")
    private Integer pageNum;

}

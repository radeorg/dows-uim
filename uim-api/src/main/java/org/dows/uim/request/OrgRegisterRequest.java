package org.dows.uim.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class OrgRegisterRequest {

    /**
     * 组织登记ID
     */
    @Schema(description = "组织登记ID")
    private Long orgRegisterId;

    /**
     * 组织树ID
     */
    @Schema(description = "组织树ID")
    private Long orgTreeId;

//    /**
//     * 操作者ID
//     */
//    @Schema(description = "操作者ID")
//    private Long operatorId;

    /**
     * 社会统一信用代码
     */
    @Schema(description = "社会统一信用代码")
    private String creditNo;

    /**
     * 组织地址
     */
    @Schema(description = "组织地址")
    private String orgAddress;

    /**
     * 公司规模
     */
    @Schema(description = "公司规模")
    private String memberScale;

    /**
     * 组织logo
     */
    @Schema(description = "组织logo")
    private String logo;

    /**
     * 企业简介
     */
    @Schema(description = "企业简介")
    private String introduction;

    /**
     * 企业邮箱
     */
    @Schema(description = "企业邮箱")
    private String email;

    /**
     * 联系电话
     */
    @Schema(description = "联系电话")
    private String telephone;

    /**
     * 联系人
     */
    @Schema(description = "联系人")
    private String contacts;

    /**
     * 应用ID
     */
    @Schema(description = "应用ID")
    private String appId;

}

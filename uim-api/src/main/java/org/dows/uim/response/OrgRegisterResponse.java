package org.dows.uim.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "组织注册信息")
@Data
public class OrgRegisterResponse {

    /**
     * 组织登记ID
     */
    @Schema(description = "组织登记ID")
    private Long orgRegisterId;

    /**
     * 组织树ID[组织rootId]
     */
    @Schema(description = "组织树ID[组织rootId]")
    private Long orgRootId;

    /**
     * 账号实例ID
     */
    @Schema(description = "账号实例ID")
    private Long accountInstanceId;

    @Schema(description = "组织名")
    private String orgName;

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
}

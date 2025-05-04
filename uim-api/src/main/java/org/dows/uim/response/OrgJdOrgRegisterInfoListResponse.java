package org.dows.uim.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "岗位、组织注册信息结果对象")
public class OrgJdOrgRegisterInfoListResponse {

    /**
     * 岗位描述ID
     */
    @Schema(description = "岗位描述ID")
    private Long orgJdId;

    /**
     * 组织树ID
     */
    @Schema(description = "组织树ID")
    private Long orgRootId;

    /**
     * 组织树ID
     */
    @Schema(description = "组织树ID")
    private Long orgTreeId;

    /**
     * 岗位规则ID
     */
    @Schema(description = "岗位规则ID")
    private Long orgRuleId;

    @Schema(description = "岗位地址")
    private String orgAddress;

    @Schema(description = "职位类别ID")
    private Long orgJdCategoryId;
    /**
     * 人事账号ID
     */
    @Schema(description = "人事账号ID")
    private Long ownerId;

    /**
     * 操作者ID
     */
    @Schema(description = "操作者ID")
    private Long operatorId;

    /**
     * 岗位名称+岗位编号
     */
    @Schema(description = "岗位名称+岗位编号")
    private String jdName;

    /**
     * 岗位描述
     */
    @Schema(description = "岗位描述")
    private String description;

    /**
     * 状态[1:下架，2:上架]
     */
    @Schema(description = "状态[1:下架，2:上架]")
    private Integer state;

    /**
     * 发布渠道集合
     */
    @Schema(description = "发布渠道集合")
    private String channels;

    /**
     * 应用ID
     */
    @Schema(description = "应用ID")
    private String appId;

    /**
     * 注册公司信息
     */
    @Schema(description = "注册公司信息")
    private OrgRegisterInfo orgRegisterInfo;

    @Data
    public static class OrgRegisterInfo {
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
         * 组织名
         */
        @Schema(description = "组织名")
        private String orgName;

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
    }

}

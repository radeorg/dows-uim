package org.dows.uim.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.keygen.KeyGenerators;
import com.tangzc.mybatisflex.autotable.annotation.ColumnDefine;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dows.rade.crud.BaseEntity;

import java.util.Date;

/**
 * 组织登记表 实体类。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "组织登记表")
@Table(value = "org_register")
public class OrgRegisterEntity extends BaseEntity<OrgRegisterEntity> {

    /**
     * 组织登记ID
     */
    @Schema(description = "组织登记ID")
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    @ColumnDefine(comment = "组织登记ID")
    private Long orgRegisterId;


    /**
     * 组织树ID
     */
    @Schema(description = "组织树根节点ID")
    @Column(value = "org_root_id")
    @ColumnDefine(comment = "组织树根节点ID")
    private Long orgRootId;

    /**
     * 组织树ID
     */
    @Schema(description = "组织树ID")
    @Column(value = "org_tree_id")
    @ColumnDefine(comment = "组织树ID")
    private Long orgTreeId;

    /**
     * 操作者ID
     */
    /*@Schema(description = "操作者ID")
    @Column(value = "operator_id")
    @ColumnDefine(comment = "操作者ID")
    private Long operatorId;*/
    @JsonIgnore
    private Long accountInstanceId;

    /**
     * 社会统一信用代码
     */
    @Schema(description = "社会统一信用代码")
    @Column(value = "credit_no")
    @ColumnDefine(comment = "社会统一信用代码")
    private String creditNo;

    /**
     * 组织地址
     */
    @Schema(description = "组织地址")
    @Column(value = "org_address")
    @ColumnDefine(comment = "组织地址")
    private String orgAddress;

    /**
     * 公司规模
     */
    @Schema(description = "公司规模")
    @Column(value = "member_scale")
    @ColumnDefine(comment = "公司规模")
    private String memberScale;

    /**
     * 组织logo
     */
    @Schema(description = "组织logo")
    @Column(value = "logo")
    @ColumnDefine(comment = "组织logo")
    private String logo;

    /**
     * 企业简介
     */
    @Schema(description = "企业简介")
    @Column(value = "introduction")
    @ColumnDefine(comment = "企业简介")
    private String introduction;

    /**
     * 企业邮箱
     */
    @Schema(description = "企业邮箱")
    @Column(value = "email")
    @ColumnDefine(comment = "企业邮箱")
    private String email;

    /**
     * 联系电话
     */
    @Schema(description = "联系电话")
    @Column(value = "phone")
    @ColumnDefine(comment = "联系电话")
    private String telephone;

    /**
     * 联系人
     */
    @Schema(description = "联系人")
    @Column(value = "contacts")
    @ColumnDefine(comment = "联系人")
    private String contacts;

    /**
     * 应用ID
     */
    @Schema(description = "应用ID")
    @Column(value = "app_id")
    @ColumnDefine(comment = "应用ID")
    private String appId;

    /**
     * 版本
     */
    @Schema(description = "版本")
    @Column(value = "ver")
    private Integer ver;

    /**
     * 逻辑删除，0未删除，1删除
     */
    @Schema(description = "逻辑删除，0未删除，1删除")
    @Column(value = "deleted")
    private Integer deleted;

    /**
     * 时间戳
     */
    @Schema(description = "时间戳")
    @Column(value = "ts")
    private Date ts;


}

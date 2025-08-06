package org.dows.uim.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.keygen.KeyGenerators;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.dows.rade.crud.BaseEntity;
import org.dows.rade.crud.AutoFillDataListener;

import java.util.Date;

/**
 * 组织登记表 实体类。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "组织登记表")
@Table(value = "org_register", onUpdate = AutoFillDataListener.class, onInsert = AutoFillDataListener.class)
public class OrgRegisterEntity extends BaseEntity<OrgRegisterEntity> {

    /**
     * 组织登记ID
     */
    @Schema(description = "组织登记ID")
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    private Long orgRegisterId;

    /**
     * 组织树ID[组织rootId]
     */
    @Schema(description = "组织树ID[组织rootId]")
    @Column(value = "org_root_id")
    private Long orgRootId;

    /**
     * 账号实例ID
     */
    @Schema(description = "账号实例ID")
    @Column(value = "account_instance_id")
    private Long accountInstanceId;

    /**
     * 社会统一信用代码
     */
    @Schema(description = "社会统一信用代码")
    @Column(value = "credit_no")
    private String creditNo;

    /**
     * 组织地址
     */
    @Schema(description = "组织地址")
    @Column(value = "org_address")
    private String orgAddress;

    /**
     * 公司规模
     */
    @Schema(description = "公司规模")
    @Column(value = "member_scale")
    private String memberScale;

    /**
     * 组织logo
     */
    @Schema(description = "组织logo")
    @Column(value = "logo")
    private String logo;

    /**
     * 组织名
     */
    @Schema(description = "组织名")
    @Column(value = "org_name")
    private String orgName;

    /**
     * 企业简介
     */
    @Schema(description = "企业简介")
    @Column(value = "introduction")
    private String introduction;

    /**
     * 企业邮箱
     */
    @Schema(description = "企业邮箱")
    @Column(value = "email")
    private String email;

    /**
     * 联系电话
     */
    @Schema(description = "联系电话")
    @Column(value = "telephone")
    private String telephone;

    /**
     * 联系人
     */
    @Schema(description = "联系人")
    @Column(value = "contacts")
    private String contacts;

    /**
     * 应用ID
     */
    @Schema(description = "应用ID")
    @Column(value = "app_id")
    private String appId;

    /**
     * 版本
     */
    @Schema(description = "版本")
    @Column(value = "ver", onUpdateValue = "ver+1")
    private Integer ver;

    /**
     * 逻辑删除，0未删除，1删除
     */
    @Schema(description = "逻辑删除，0未删除，1删除")
    @Column(value = "deleted", isLogicDelete = true)
    private Integer deleted;

    /**
     * 时间戳
     */
    @Schema(description = "时间戳")
    @Column(value = "ts")
    private Date ts;

    @Column(value = "ut")
    private Date ut;

    @Column(value = "owner_id")
    private Long ownerId;


}

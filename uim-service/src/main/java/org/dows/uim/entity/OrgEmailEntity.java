package org.dows.uim.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.keygen.KeyGenerators;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.dows.rade.crud.BaseEntity;
import org.dows.uim.AutoFillDataListener;

import java.util.Date;

/**
 * 组织邮箱表 实体类。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "组织邮箱表")
@Table(value = "org_email", onUpdate = AutoFillDataListener.class, onInsert = AutoFillDataListener.class)
public class OrgEmailEntity extends BaseEntity<OrgEmailEntity> {

    /**
     * 组织邮箱ID
     */
    @Schema(description = "组织邮箱ID")
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    private Long orgEmailId;

    /**
     * 组织树ID[组织rootId]
     */
    @Schema(description = "组织树ID[组织rootId]")
    @Column(value = "org_root_id")
    private Long orgRootId;

    /**
     * 组织树ID
     */
    @Schema(description = "组织树ID")
    @Column(value = "org_tree_id")
    private Long orgTreeId;

    /**
     * 企业邮箱
     */
    @Schema(description = "企业邮箱")
    @Column(value = "email")
    private String email;


    @Schema(description = "授权码")
    @Column(value = "auth_code")
    private String authCode;

    /**
     * JSON配置
     */
    @Schema(description = "JSON配置")
    @Column(value = "config_json")
    private String configJson;

    /**
     * 描述
     */
    @Schema(description = "描述")
    @Column(value = "description")
    private String description;

    /**
     * 应用ID
     */
    @Schema(description = "应用ID")
    @Column(value = "app_id", tenantId = true)
    private String appId;

    /**
     * 邮箱类型[收件，发件,...]
     */
    @Schema(description = "邮箱类型[收件，发件,...]")
    @Column(value = "email_type")
    private Integer emailType;

    /**
     * 版本
     */
    @Schema(description = "版本")
    @Column(value = "ver", version = true)
    private Integer ver = 0;

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

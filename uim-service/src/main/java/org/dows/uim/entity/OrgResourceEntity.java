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
 * 组织资源表 实体类。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "组织资源表")
@Table(value = "org_resource", onUpdate = AutoFillDataListener.class, onInsert = AutoFillDataListener.class)
public class OrgResourceEntity extends BaseEntity<OrgResourceEntity> {

    /**
     * 组织资源ID
     */
    @Schema(description = "组织资源ID")
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    private Long orgResourceId;

    /**
     * 组织树ID
     */
    @Schema(description = "组织树ID")
    @Column(value = "org_tree_id")
    private Long orgTreeId;

    /**
     * 账号实例ID
     */
    @Schema(description = "账号实例ID")
    @Column(value = "account_instance_id")
    private Long accountInstanceId;

    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    @Column(value = "user_instance_id")
    private Long userInstanceId;

    /**
     * 资源地址
     */
    @Schema(description = "资源地址")
    @Column(value = "resource_url")
    private String resourceUrl;

    /**
     * 应用ID
     */
    @Schema(description = "应用ID")
    @Column(value = "app_id")
    private String appId;

    /**
     * 存储通道[local,cos,alioss]
     */
    @Schema(description = "存储通道[local,cos,alioss]")
    @Column(value = "oss_type")
    private String ossType;

    /**
     * 资源类型[0:身份证，1：营业执照，2:门头照...]
     */
    @Schema(description = "资源类型[0:身份证，1：营业执照，2:门头照...]")
    @Column(value = "resource_type")
    private Integer resourceType;

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

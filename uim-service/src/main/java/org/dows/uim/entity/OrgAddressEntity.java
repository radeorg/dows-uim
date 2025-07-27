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
 * 公司地址表(OrgAddress)实体类
 *
 * @since 2025-04-22 11:31:50
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "组织地址")
@Table(value = "org_address", onUpdate = AutoFillDataListener.class, onInsert = AutoFillDataListener.class)
public class OrgAddressEntity extends BaseEntity<OrgAddressEntity> {
    /**
     * 地址维度ID
     */
    @Schema(description = "地址维度ID")
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    private Long orgAddressId;
    /**
     * 组织根节点ID
     */
    @Schema(description = "组织根节点ID")
    @Column(value = "org_root_id")
    private Long orgRootId;
    /**
     * 组织树ID
     */
    @Schema(description = "组织树ID")
    @Column(value = "org_tree_id")
    private Long orgTreeId;
    /**
     * 详细地址
     */
    @Column(value = "address")
    private String address;
    /**
     * 乐观锁，默认为0
     */
    @Column(value = "ver", onUpdateValue = "ver+1")
    private Integer ver;
    /**
     * 状态
     */
    @Column(value = "state")
    private Integer state;
    /**
     * 操作者ID
     */
    private Long operatorId;
    /**
     * 逻辑删除，0未删除，1删除
     */
    private Integer deleted;

    @Schema(description = "应用ID")
    @Column(value = "app_id", tenantId = true)
    private String appId;
    @Schema(description = "时间戳")
    @Column(value = "ts")
    private Date ts;

    @Column(value = "ut")
    private Date ut;

    @Column(value = "owner_id")
    private Long ownerId;


}


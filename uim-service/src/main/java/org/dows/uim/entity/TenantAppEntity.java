package org.dows.uim.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.keygen.KeyGenerators;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.dows.rade.crud.AutoFillDataListener;
import org.dows.rade.crud.BaseEntity;

import java.util.Date;

/**
 * 租户应用表 实体类。
 *
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "租户应用表")
@Table(value = "tenant_app", onUpdate = AutoFillDataListener.class, onInsert = AutoFillDataListener.class)
public class TenantAppEntity extends BaseEntity<TenantAppEntity> {

    /**
     * 租户应用ID
     */
    @Schema(description = "租户应用ID")
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    private Long tenantAppId;

    /**
     * 组织登记ID
     */
    @Schema(description = "组织登记ID")
    @Column(value = "org_register_id")
    private Long orgRegisterId;

    /**
     * 组织空间
     */
    @Schema(description = "组织空间")
    @Column(value = "namespace")
    private String namespace;

    /**
     * 操作者ID
     */
    @Schema(description = "操作者ID")
    @Column(value = "operator_id")
    private Long operatorId;

    /**
     * 应用ID
     */
    @Schema(description = "应用ID")
    @Column(value = "app_id", tenantId = true)
    private String appId;

    /**
     * 时间戳
     */
    @Schema(description = "时间戳")
    @Column(value = "ts")
    private Date ts;

    @Column(value = "ut")
    private Date ut;
}

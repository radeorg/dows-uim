package org.dows.uim.entity;

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
 * 租户账号表(TenantAccount)实体类
 *
 * @author lait.zhang@gmail.com
 * @since 2025-08-10 17:51:17
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "租户用户账号表")
@Table(value = "tenant_account", onUpdate = AutoFillDataListener.class, onInsert = AutoFillDataListener.class)
public class TenantAccountEntity extends BaseEntity<TenantAccountEntity> {
    /**
     * 租户账号ID
     */
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    private Long tenantAccountId;
    /**
     * 租户实例ID
     */
    private Long tenantInstanceId;
    /**
     * 账号实例ID
     */
    private Long accountInstanceId;
    /**
     * 应用ID
     */
    private String appId;
    /**
     * 逻辑删除，0未删除，1删除
     */
    private Integer deleted;
    /**
     * 时间戳
     */
    private Date ts;

    private Date ut;

    private Long ownerId;

}


package org.dows.uim.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.keygen.KeyGenerators;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dows.rade.crud.BaseEntity;

import java.util.Date;

/**
 * 账号类型表 实体类。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "账号类型表")
@Table(value = "account_type")
public class AccountTypeEntity extends BaseEntity<AccountTypeEntity> {

    /**
     * 账号类型ID
     */
    @Schema(description = "账号类型ID")
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    private Long accountTypeId;

    /**
     * 账号实例ID
     */
    @Schema(description = "账号实例ID")
    @Column(value = "account_instance_id")
    private Long accountInstanceId;

    /**
     * 操作者ID
     */
    @Schema(description = "操作者ID")
    @Column(value = "operator_id")
    private Long operatorId;

    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    @Column(value = "user_instance_id")
    private Long userInstanceId;

    /**
     * 引用ID
     */
    @Schema(description = "引用ID")
    @Column(value = "reference_id")
    private Long referenceId;

    /**
     * 账号类型
     */
    @Schema(description = "账号类型")
    @Column(value = "account_type")
    private Integer accountType;

    /**
     * 引用类型值
     */
    @Schema(description = "引用类型值")
    @Column(value = "reference_type")
    private Integer referenceType;

    /**
     * 类型名称
     */
    @Schema(description = "类型名称")
    @Column(value = "type_name")
    private String typeName;

    /**
     * 应用ID
     */
    @Schema(description = "应用ID")
    @Column(value = "app_id")
    private String appId;

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


}

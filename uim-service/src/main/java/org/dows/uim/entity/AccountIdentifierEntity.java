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
 * 账号标识表 实体类。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "账号标识表")
@Table(value = "account_identifier")
public class AccountIdentifierEntity extends BaseEntity<AccountIdentifierEntity> {

    /**
     * 账号标识ID
     */
    @Schema(description = "账号标识ID")
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    private Long accountIdentifierId;

    @Schema(description = "主账号ID")
    @Column(value = "account_instance_id")
    private Long accountInstanceId;

    /**
     * 账号标识符
     */
    @Schema(description = "账号标识符")
    @Column(value = "identifier")
    private String identifier;

    /**
     * 操作者ID
     */
    @Schema(description = "操作者ID")
    @Column(value = "operator_id")
    private Long operatorId;

    /**
     * 类型[0:账号,1:手机号,2:邮箱,3:第三方token]
     */
    @Schema(description = "类型[0:账号,1:手机号,2:邮箱,3:第三方token]")
    @Column(value = "type")
    private Integer type;

    /**
     * 乐观锁, 默认: 0
     */
    @Schema(description = "乐观锁, 默认: 0")
    @Column(value = "ver")
    private Integer ver;

    /**
     * 应用ID
     */
    @Schema(description = "应用ID")
    @Column(value = "app_id")
    private String appId;

    /**
     * 逻辑删除  0未删除  1 删除
     */
    @Schema(description = "逻辑删除  0未删除  1 删除")
    @Column(value = "deleted")
    private Integer deleted;

    /**
     * 时间戳
     */
    @Schema(description = "时间戳")
    @Column(value = "ts")
    private Date ts;


}

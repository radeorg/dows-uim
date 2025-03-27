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
 * 账号实例表 实体类。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "账号实例表")
@Table(value = "account_instance")
public class AccountInstanceEntity extends BaseEntity<AccountInstanceEntity> {

    /**
     * 账号实例ID
     */
    @Schema(description = "账号实例ID")
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    private Long accountInstanceId;

    /**
     * 账号标识符
     */
    @Schema(description = "账号标识符")
    @Column(value = "identifier")
    private String identifier;

    /**
     * 密码
     */
    @Schema(description = "密码")
    @Column(value = "password")
    private String password;

    /**
     * 区域编码(+86，+11...)
     */
    @Schema(description = "区域编码(+86，+11...)")
    @Column(value = "zone_no")
    private String zoneNo;

    /**
     * 手机号
     */
    @Schema(description = "手机号")
    @Column(value = "cellphone")
    private String cellphone;

    /**
     * 头像
     */
    @Schema(description = "头像")
    @Column(value = "avator")
    private String avator;

    /**
     * 推荐码
     */
    @Schema(description = "推荐码")
    @Column(value = "referrals_no")
    private String referralsNo;

    /**
     * 来源(来源渠道推广时标记用)
     */
    @Schema(description = "来源(来源渠道推广时标记用)")
    @Column(value = "source")
    private String source;

    /**
     * 应用ID
     */
    @Schema(description = "应用ID")
    @Column(value = "app_id")
    private String appId;

    /**
     * 操作者ID
     */
    @Schema(description = "操作者ID")
    @Column(value = "operator_id")
    private Long operatorId;

    /**
     * 乐观锁, 默认: 0
     */
    @Schema(description = "乐观锁, 默认: 0")
    @Column(value = "ver")
    private Integer ver;

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

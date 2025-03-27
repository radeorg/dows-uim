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
 * 用户证书表 实体类。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "用户证书表")
@Table(value = "user_certication")
public class UserCerticationEntity extends BaseEntity<UserCerticationEntity> {

    /**
     * 用户证书ID
     */
    @Schema(description = "用户证书ID")
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    private Long userCerticationId;

    /**
     * 操作者ID
     */
    @Schema(description = "操作者ID")
    @Column(value = "operator_id")
    private Long operatorId;

    /**
     * 用户实例ID
     */
    @Schema(description = "用户实例ID")
    @Column(value = "user_instance_id")
    private Long userInstanceId;

    /**
     * 证书名称
     */
    @Schema(description = "证书名称")
    @Column(value = "cert_name")
    private String certName;

    /**
     * 证书编号
     */
    @Schema(description = "证书编号")
    @Column(value = "cert_no")
    private String certNo;

    /**
     * 应用ID
     */
    @Schema(description = "应用ID")
    @Column(value = "app_id")
    private String appId;

    /**
     * 永久性标识：0-否，1-是
     */
    @Schema(description = "永久性标识：0-否，1-是")
    @Column(value = "perminent")
    private Integer perminent;

    /**
     * 证书获取日期
     */
    @Schema(description = "证书获取日期")
    @Column(value = "award_date")
    private Date awardDate;

    /**
     * 证书有效日期
     */
    @Schema(description = "证书有效日期")
    @Column(value = "expiry_date")
    private Date expiryDate;

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

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
 * 用户实例表 实体类。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "用户实例表")
@Table(value = "user_instance", onUpdate = AutoFillDataListener.class, onInsert = AutoFillDataListener.class)
public class UserInstanceEntity extends BaseEntity<UserInstanceEntity> {

    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    private Long userInstanceId;

    /**
     * 操作者ID
     */
    @Schema(description = "操作者ID")
    @Column(value = "operator_id")
    private Long operatorId;

    /**
     * 用户名
     */
    @Schema(description = "用户名")
    @Column(value = "user_name")
    private String userName;

    /**
     * 身份证号
     */
    @Schema(description = "身份证号")
    @Column(value = "identify_no")
    private String identifyNo;

    /**
     * 用户年龄
     */
    @Schema(description = "用户年龄")
    @Column(value = "user_age")
    private Integer userAge;

    /**
     * 性别
     */
    @Schema(description = "性别")
    @Column(value = "sex")
    private Integer sex;

    /**
     * 应用ID
     */
    @Schema(description = "应用ID")
    @Column(value = "app_id", tenantId = true)
    private String appId;

    /**
     * 乐观锁，默认为0
     */
    @Schema(description = "乐观锁，默认为0")
    @Column(value = "ver", onUpdateValue = "ver+1")
    private Integer ver;

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

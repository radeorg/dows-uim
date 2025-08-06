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
 * 用户工作表 实体类。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "用户工作表")
@Table(value = "user_job", onUpdate = AutoFillDataListener.class, onInsert = AutoFillDataListener.class)
public class UserJobEntity extends BaseEntity<UserJobEntity> {

    /**
     * 用户工作信息ID
     */
    @Schema(description = "用户工作信息ID")
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    private Long userJobId;

    /**
     * 用户实例ID
     */
    @Schema(description = "用户实例ID")
    @Column(value = "user_instance_id")
    private Long userInstanceId;

    /**
     * 职业
     */
    @Schema(description = "职业")
    @Column(value = "profession")
    private String profession;

    /**
     * 工作单位名称
     */
    @Schema(description = "工作单位名称")
    @Column(value = "org_name")
    private String orgName;

    /**
     * 单位[日|月|年]
     */
    @Schema(description = "单位[日|月|年]")
    @Column(value = "unit")
    private String unit;

    /**
     * 开始时间
     */
    @Schema(description = "开始时间")
    @Column(value = "start_time")
    private Date startTime;

    /**
     * 结束时间
     */
    @Schema(description = "结束时间")
    @Column(value = "end_time")
    private Date endTime;

    /**
     * 时长
     */
    @Schema(description = "时长")
    @Column(value = "duration")
    private Integer duration;

    /**
     * 状态
     */
    @Schema(description = "状态")
    @Column(value = "state")
    private Integer state;

    /**
     * 乐观锁，默认为0
     */
    @Schema(description = "乐观锁，默认为0")
    @Column(value = "ver", onUpdateValue = "ver+1")
    private Integer ver;

    /**
     * 应用ID
     */
    @Schema(description = "应用ID")
//    @Column(value = "app_id", tenantId = true)
    private String appId;

    /**
     * 逻辑删除，0未删除，1删除
     */
    @Schema(description = "逻辑删除，0未删除，1删除")
    @Column(value = "deleted", isLogicDelete = true)
    private Integer deleted;

    /**
     * 操作者ID
     */
    @Schema(description = "操作者ID")
    @Column(value = "operator_id")
    private Long operatorId;

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

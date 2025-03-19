package org.dows.uim.entity;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Builder;
import io.swagger.v3.oas.annotations.media.Schema;
import com.mybatisflex.core.activerecord.Model;
import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;

import java.lang.Long;
import java.util.Date;
import java.lang.String;
import java.lang.Integer;

/**
 * 用户工作表 实体类。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@RequiredArgsConstructor
@Data(staticConstructor = "create")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "用户工作表")
@Table(value = "user_job")
public class UserJobEntity extends Model<UserJobEntity> {

    /**
     * 用户工作信息ID
     */
    @Schema(description = "用户工作信息ID")
    @Id(keyType = KeyType.Auto)
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


}

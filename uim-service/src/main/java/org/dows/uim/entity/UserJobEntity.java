package org.dows.uim.entity;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
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
@Data(staticConstructor = "create")
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "user_job")
public class UserJobEntity extends Model<UserJobEntity> {

    /**
     * 用户工作信息ID
     */
    @Id(keyType = KeyType.Sequence)
    private Long userJobId;

    /**
     * 用户实例ID
     */
    @Column(value = "user_instance_id", comment = "用户实例ID")
    private Long userInstanceId;

    /**
     * 职业
     */
    @Column(value = "profession", comment = "职业")
    private String profession;

    /**
     * 工作单位名称
     */
    @Column(value = "org_name", comment = "工作单位名称")
    private String orgName;

    /**
     * 单位[日|月|年]
     */
    @Column(value = "unit", comment = "单位[日|月|年]")
    private String unit;

    /**
     * 开始时间
     */
    @Column(value = "start_time", comment = "开始时间")
    private Date startTime;

    /**
     * 结束时间
     */
    @Column(value = "end_time", comment = "结束时间")
    private Date endTime;

    /**
     * 时长
     */
    @Column(value = "duration", comment = "时长")
    private Integer duration;

    /**
     * 状态
     */
    @Column(value = "state", comment = "状态")
    private Integer state;

    /**
     * 乐观锁, 默认: 0
     */
    @Column(value = "ver", comment = "乐观锁, 默认: 0")
    private Integer ver;

    /**
     * 应用ID
     */
    @Column(value = "app_id", comment = "应用ID")
    private String appId;

    /**
     * 逻辑删除  0未删除  1 删除
     */
    @Column(value = "deleted", comment = "逻辑删除  0未删除  1 删除")
    private Integer deleted;

    /**
     * 操作者ID
     */
    @Column(value = "operator_id", comment = "操作者ID")
    private Long operatorId;

    /**
     * 时间戳
     */
    @Column(value = "ts", comment = "时间戳")
    private Date ts;


}

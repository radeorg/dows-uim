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
 * 用户教育表 实体类。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Data(staticConstructor = "create")
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "user_education")
public class UserEducationEntity extends Model<UserEducationEntity> {

    /**
     * 用户教育ID
     */
    @Id(keyType = KeyType.Sequence)
    private Long userEducationId;

    /**
     * 操作者ID
     */
    @Column(value = "operator_id", comment = "操作者ID")
    private Long operatorId;

    /**
     * 用户实例ID
     */
    @Column(value = "user_instance_id", comment = "用户实例ID")
    private Long userInstanceId;

    /**
     * 专业名称
     */
    @Column(value = "application", comment = "专业名称")
    private String application;

    /**
     * 学校名称
     */
    @Column(value = "university_name", comment = "学校名称")
    private String universityName;

    /**
     * 专业描述
     */
    @Column(value = "application_description", comment = "专业描述")
    private String applicationDescription;

    /**
     * 开始年月
     */
    @Column(value = "start_time", comment = "开始年月")
    private Date startTime;

    /**
     * 结束年月
     */
    @Column(value = "end_time", comment = "结束年月")
    private Date endTime;

    /**
     * 性质：0-职业学院，1-专科，2-本科，3-硕士，4-博士
     */
    @Column(value = "education_type", comment = "性质：0-职业学院，1-专科，2-本科，3-硕士，4-博士")
    private Integer educationType;

    /**
     * 至今
     */
    @Column(value = "current_today", comment = "至今")
    private Integer currentToday;

    /**
     * 逻辑删除  0未删除  1 删除
     */
    @Column(value = "deleted", comment = "逻辑删除  0未删除  1 删除")
    private Integer deleted;

    /**
     * 应用ID
     */
    @Column(value = "app_id", comment = "应用ID")
    private String appId;

    /**
     * 时间戳
     */
    @Column(value = "ts", comment = "时间戳")
    private Date ts;


}

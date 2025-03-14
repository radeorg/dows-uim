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
 * 用户培训表 实体类。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Data(staticConstructor = "create")
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "user_training")
public class UserTrainingEntity extends Model<UserTrainingEntity> {

    /**
     * 用户培训ID
     */
    @Id(keyType = KeyType.Sequence)
    private Long userTrainingId;

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
     * 机构名称
     */
    @Column(value = "university_name", comment = "机构名称")
    private String universityName;

    /**
     * 培训描述
     */
    @Column(value = "training_description", comment = "培训描述")
    private String trainingDescription;

    /**
     * 应用ID
     */
    @Column(value = "app_id", comment = "应用ID")
    private String appId;

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
     * 时间戳
     */
    @Column(value = "ts", comment = "时间戳")
    private Date ts;

    /**
     * 逻辑删除  0未删除  1 删除
     */
    @Column(value = "deleted", comment = "逻辑删除  0未删除  1 删除")
    private Integer deleted;


}

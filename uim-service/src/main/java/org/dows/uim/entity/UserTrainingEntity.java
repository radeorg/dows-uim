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
 * 用户培训表 实体类。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@RequiredArgsConstructor
@Data(staticConstructor = "create")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "用户培训表")
@Table(value = "user_training")
public class UserTrainingEntity extends Model<UserTrainingEntity> {

    /**
     * 用户培训ID
     */
    @Schema(description = "用户培训ID")
    @Id(keyType = KeyType.Auto)
    private Long userTrainingId;

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
     * 机构名称
     */
    @Schema(description = "机构名称")
    @Column(value = "university_name")
    private String universityName;

    /**
     * 培训描述
     */
    @Schema(description = "培训描述")
    @Column(value = "training_description")
    private String trainingDescription;

    /**
     * 应用ID
     */
    @Schema(description = "应用ID")
    @Column(value = "app_id")
    private String appId;

    /**
     * 开始年月
     */
    @Schema(description = "开始年月")
    @Column(value = "start_time")
    private Date startTime;

    /**
     * 结束年月
     */
    @Schema(description = "结束年月")
    @Column(value = "end_time")
    private Date endTime;

    /**
     * 时间戳
     */
    @Schema(description = "时间戳")
    @Column(value = "ts")
    private Date ts;

    /**
     * 逻辑删除  0未删除  1 删除
     */
    @Schema(description = "逻辑删除  0未删除  1 删除")
    @Column(value = "deleted")
    private Integer deleted;


}

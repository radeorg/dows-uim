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
 * 用户培训表 实体类。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "用户培训表")
@Table(value = "user_training")
public class UserTrainingEntity extends BaseEntity<UserTrainingEntity> {

    /**
     * 用户培训ID
     */
    @Schema(description = "用户培训ID")
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
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
     * 逻辑删除，0未删除，1删除
     */
    @Schema(description = "逻辑删除，0未删除，1删除")
    @Column(value = "deleted", isLogicDelete = true)
    private Integer deleted;

    @Column(value = "ut")
    private Date ut;

    @Column(value = "owner_id")
    private Long ownerId;


}

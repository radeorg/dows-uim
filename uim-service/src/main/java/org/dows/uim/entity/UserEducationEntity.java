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
 * 用户教育表 实体类。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "用户教育表")
@Table(value = "user_education")
public class UserEducationEntity extends BaseEntity<UserEducationEntity> {

    /**
     * 用户教育ID
     */
    @Schema(description = "用户教育ID")
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    private Long userEducationId;

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
     * 专业名称
     */
    @Schema(description = "专业名称")
    @Column(value = "application")
    private String application;

    /**
     * 学校名称
     */
    @Schema(description = "学校名称")
    @Column(value = "university_name")
    private String universityName;

    /**
     * 专业描述
     */
    @Schema(description = "专业描述")
    @Column(value = "application_description")
    private String applicationDescription;

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
     * 性质：0-职业学院，1-专科，2-本科，3-硕士，4-博士
     */
    @Schema(description = "性质：0-职业学院，1-专科，2-本科，3-硕士，4-博士")
    @Column(value = "education_type")
    private Integer educationType;

    /**
     * 至今
     */
    @Schema(description = "至今")
    @Column(value = "current_today")
    private Integer currentToday;

    /**
     * 逻辑删除，0未删除，1删除
     */
    @Schema(description = "逻辑删除，0未删除，1删除")
    @Column(value = "deleted", isLogicDelete = true)
    private Integer deleted;

    /**
     * 应用ID
     */
    @Schema(description = "应用ID")
    @Column(value = "app_id")
    private String appId;

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

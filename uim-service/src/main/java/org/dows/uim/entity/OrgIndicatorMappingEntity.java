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
 * 指标映射表 实体类。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "指标映射表")
@Table(value = "org_indicator_mapping", onUpdate = AutoFillDataListener.class, onInsert = AutoFillDataListener.class)
public class OrgIndicatorMappingEntity extends BaseEntity<OrgIndicatorMappingEntity> {

    /**
     * 指标映射ID
     */
    @Schema(description = "指标映射ID")
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    private Long orgIndicatorMappingId;

    /**
     * 岗位指标ID
     */
    @Schema(description = "岗位指标ID")
    @Column(value = "org_indicator_id")
    private Long orgIndicatorId;

    /**
     * 通道[deepseek,doubao]
     */
    @Schema(description = "通道[deepseek,doubao]")
    @Column(value = "ai_channel")
    private String aiChannel;

    /**
     * 条件表达式
     */
    @Schema(description = "条件表达式")
    @Column(value = "condition_expression")
    private String conditionExpression;

    /**
     * 取值表达式
     */
    @Schema(description = "取值表达式")
    @Column(value = "json_path")
    private String jsonPath;

    /**
     * 操作者ID
     */
    @Schema(description = "操作者ID")
    @Column(value = "operator_id")
    private Long operatorId;

    /**
     * 应用ID
     */
    @Schema(description = "应用ID")
    @Column(value = "app_id", tenantId = true)
    private String appId;

    /**
     * 时间戳
     */
    @Schema(description = "时间戳")
    @Column(value = "ts")
    private Date ts;

    /**
     * 逻辑表达式>,<,=，equals,contain
     */
    @Schema(description = "逻辑表达式>,<,=，equals,contain")
    @Column(value = "condition")
    private String condition;

    @Column(value = "ut")
    private Date ut;

    @Column(value = "owner_id")
    private Long ownerId;


}

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
 * 岗位指标表 实体类。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "岗位指标表")
@Table(value = "org_indicator")
public class OrgIndicatorEntity extends BaseEntity<OrgIndicatorEntity> {

    /**
     * 岗位指标ID
     */
    @Schema(description = "岗位指标ID")
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    private Long orgIndicatorId;

    /**
     * 岗位行动ID
     */
    @Schema(description = "岗位行动ID")
    @Column(value = "org_action_id")
    private Long orgActionId;

    /**
     * 岗位规则ID
     */
    @Schema(description = "岗位规则ID")
    @Column(value = "org_rule_id")
    private Long orgRuleId;

    /**
     * 指标名称
     */
    @Schema(description = "指标名称")
    @Column(value = "indicator_name")
    private String indicatorName;

    /**
     * 指标英文名
     */
    @Schema(description = "指标英文名")
    @Column(value = "indicator_code")
    private String indicatorCode;


    @Schema(description = "指标关键字")
    @Column(value = "indicator_keyword")
    private String indicatorKeyword;

    @Schema(description = "指标别名")
    @Column(value = "indicator_alias")
    private String indicatorAlias;

    @Schema(description = "指标别名1")
    @Column(value = "indicator_alias1")
    private String indicatorAlias1;

    /**
     * 数据类型
     */
    @Schema(description = "数据类型")
    @Column(value = "data_type")
    private String dataType;

    /**
     * 逻辑表达式>,<,=，equals,contain
     */
    @Schema(description = "逻辑表达式>,<,=，equals,contain")
    @Column(value = "condition")
    private String condition;

    /**
     * 指标分值
     */
    @Schema(description = "指标分值")
    @Column(value = "indicator_score")
    private Integer indicatorScore;

    /**
     * 指标匹配度
     */
    @Schema(description = "指标匹配度")
    @Column(value = "match_score")
    private Integer matchScore;

    /**
     * 是否可用0-可用，1-不可用
     */
    @Schema(description = "是否可用0-可用，1-不可用")
    @Column(value = "enabled")
    private Integer enabled;

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
    @Column(value = "app_id")
    private String appId;

    /**
     * 时间戳
     */
    @Schema(description = "时间戳")
    @Column(value = "ts")
    private Date ts;


}

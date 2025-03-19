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
 * 岗位指标表 实体类。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@RequiredArgsConstructor
@Data(staticConstructor = "create")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "岗位指标表")
@Table(value = "org_indicator")
public class OrgIndicatorEntity extends Model<OrgIndicatorEntity> {

    /**
     * 岗位指标ID
     */
    @Schema(description = "岗位指标ID")
    @Id(keyType = KeyType.Auto)
    private Long orgIndicatorId;

    /**
     * 岗位行动ID
     */
    @Schema(description = "岗位行动ID")
    @Column(value = "org_action_id")
    private Long orgActionId;

    /**
     * 指标名称
     */
    @Schema(description = "指标名称")
    @Column(value = "indicator_name")
    private String indicatorName;

    /**
     * 指标关键字
     */
    @Schema(description = "指标关键字")
    @Column(value = "indicator_keyword")
    private String indicatorKeyword;

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

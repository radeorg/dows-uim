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
 * 岗位指标表 实体类。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Data(staticConstructor = "create")
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "org_indicator")
public class OrgIndicatorEntity extends Model<OrgIndicatorEntity> {

    /**
     * 岗位指标ID
     */
    @Id(keyType = KeyType.Sequence)
    private Long orgIndicatorId;

    /**
     * 岗位行动ID
     */
    @Column(value = "org_action_id", comment = "岗位行动ID")
    private Long orgActionId;

    /**
     * 指标名称
     */
    @Column(value = "indicator_name", comment = "指标名称")
    private String indicatorName;

    /**
     * 指标关键字
     */
    @Column(value = "indicator_keyword", comment = "指标关键字")
    private String indicatorKeyword;

    /**
     * 指标分值
     */
    @Column(value = "indicator_score", comment = "指标分值")
    private Integer indicatorScore;

    /**
     * 指标匹配度
     */
    @Column(value = "match_score", comment = "指标匹配度")
    private Integer matchScore;

    /**
     * 是否可用0-可用，1-不可用
     */
    @Column(value = "enabled", comment = "是否可用0-可用，1-不可用")
    private Integer enabled;

    /**
     * 操作者ID
     */
    @Column(value = "operator_id", comment = "操作者ID")
    private Long operatorId;

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

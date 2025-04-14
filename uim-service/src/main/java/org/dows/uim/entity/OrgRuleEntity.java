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
 * 岗位规则表 实体类。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "岗位规则表")
@Table(value = "org_rule")
public class OrgRuleEntity extends BaseEntity<OrgRuleEntity> {

    /**
     * 岗位规则ID
     */
    @Schema(description = "岗位规则ID")
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    private Long orgRuleId;

    /**
     * 组织树ID
     */
    @Schema(description = "组织树ID")
    @Column(value = "org_tree_id")
    private Long orgTreeId;

    /**
     * 规则名称
     */
    @Schema(description = "规则名称")
    @Column(value = "rule_name")
    private String ruleName;

    /**
     * 规则描述
     */
    @Schema(description = "规则描述")
    @Column(value = "rule_description")
    private String ruleDescription;

    /**
     * 操作者ID
     */
    @Schema(description = "操作者ID")
    @Column(value = "operator_id")
    private Long operatorId;

    /**
     * 是否可用0-可用，1-不可用
     */
    @Schema(description = "是否可用0-可用，1-不可用")
    @Column(value = "enabled")
    private Integer enabled;

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

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
 * 岗位规则表 实体类。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Data(staticConstructor = "create")
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "org_rule")
public class OrgRuleEntity extends Model<OrgRuleEntity> {

    /**
     * 岗位规则ID
     */
    @Id(keyType = KeyType.Sequence)
    private Long orgRuleId;

    /**
     * 组织树ID
     */
    @Column(value = "org_tree_id", comment = "组织树ID")
    private Long orgTreeId;

    /**
     * 规则名称
     */
    @Column(value = "rule_name", comment = "规则名称")
    private String ruleName;

    /**
     * 规则描述
     */
    @Column(value = "rule_description", comment = "规则描述")
    private String ruleDescription;

    /**
     * 操作者ID
     */
    @Column(value = "operator_id", comment = "操作者ID")
    private Long operatorId;

    /**
     * 是否可用0-可用，1-不可用
     */
    @Column(value = "enabled", comment = "是否可用0-可用，1-不可用")
    private Integer enabled;

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

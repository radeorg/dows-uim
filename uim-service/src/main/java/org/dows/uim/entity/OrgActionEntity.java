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
 * 岗位行动表 实体类。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Data(staticConstructor = "create")
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "org_action")
public class OrgActionEntity extends Model<OrgActionEntity> {

    /**
     * 岗位行动ID
     */
    @Id(keyType = KeyType.Sequence)
    private Long orgActionId;

    /**
     * 岗位规则ID
     */
    @Column(value = "org_rule_id", comment = "岗位规则ID")
    private Long orgRuleId;

    /**
     * 操作者ID
     */
    @Column(value = "operator_id", comment = "操作者ID")
    private Long operatorId;

    /**
     * 动作顺序
     */
    @Column(value = "seq", comment = "动作顺序")
    private Integer seq;

    /**
     * 动作名称
     */
    @Column(value = "action_name", comment = "动作名称")
    private String actionName;

    /**
     * 岗位动作描述
     */
    @Column(value = "action_description", comment = "岗位动作描述")
    private String actionDescription;

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

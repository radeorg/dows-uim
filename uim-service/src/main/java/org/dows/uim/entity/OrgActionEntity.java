package org.dows.uim.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.keygen.KeyGenerators;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.dows.rade.crud.BaseEntity;
import org.dows.uim.AutoFillDataListener;

import java.util.Date;

/**
 * 岗位行动表 实体类。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "岗位行动表")
@Table(value = "org_action", onUpdate = AutoFillDataListener.class, onInsert = AutoFillDataListener.class)
public class OrgActionEntity extends BaseEntity<OrgActionEntity> {

    /**
     * 岗位行动ID
     */
    @Schema(description = "岗位行动ID")
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    private Long orgActionId;

    /**
     * 岗位规则ID
     */
    @Schema(description = "岗位规则ID")
    @Column(value = "org_rule_id")
    private Long orgRuleId;

    /**
     * 操作者ID
     */
    @Schema(description = "操作者ID")
    @Column(value = "operator_id")
    private Long operatorId;

    /**
     * 动作顺序
     */
    @Schema(description = "动作顺序")
    @Column(value = "seq")
    private Integer seq;

    /**
     * 动作名称
     */
    @Schema(description = "动作名称")
    @Column(value = "action_name")
    private String actionName;

    /**
     * 岗位动作描述
     */
    @Schema(description = "岗位动作描述")
    @Column(value = "action_description")
    private String actionDescription;

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

    @Column(value = "ut")
    private Date ut;

    @Column(value = "owner_id")
    private Long ownerId;


}

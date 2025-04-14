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
 * 岗位JD表 实体类。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "岗位JD表")
@Table(value = "org_jd")
public class OrgJdEntity extends BaseEntity<OrgJdEntity> {

    /**
     * 岗位描述ID
     */
    @Schema(description = "岗位描述ID")
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    private Long orgJdId;

    /**
     * 组织树ID
     */
    @Schema(description = "组织树ID")
    @Column(value = "org_root_id")
    private Long orgRootId;

    /**
     * 组织树ID
     */
    @Schema(description = "组织树ID")
    @Column(value = "org_tree_id")
    private Long orgTreeId;

    /**
     * 岗位规则ID
     */
    @Schema(description = "岗位规则ID")
    @Column(value = "org_rule_id")
    private Long orgRuleId;

    /**
     * 人事账号ID
     */
    @Schema(description = "人事账号ID")
    @Column(value = "owner_id")
    private Long ownerId;

    /**
     * 操作者ID
     */
    @Schema(description = "操作者ID")
    @Column(value = "operator_id")
    private Long operatorId;

    /**
     * 岗位名称+岗位编号
     */
    @Schema(description = "岗位名称+岗位编号")
    @Column(value = "jd_name")
    private String jdName;

    /**
     * 岗位描述
     */
    @Schema(description = "岗位描述")
    @Column(value = "description")
    private String description;

    /**
     * 状态[1:下架，2:上架]
     */
    @Schema(description = "状态[1:下架，2:上架]")
    @Column(value = "state")
    private Integer state;

    /**
     * 发布渠道集合
     */
    @Schema(description = "发布渠道集合")
    @Column(value = "channels")
    private String channels;

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


}

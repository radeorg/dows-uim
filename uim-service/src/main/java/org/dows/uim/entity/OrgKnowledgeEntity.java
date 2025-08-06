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
 * 岗位知识表 实体类。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "岗位知识表")
@Table(value = "org_knowledge", onUpdate = AutoFillDataListener.class, onInsert = AutoFillDataListener.class)
public class OrgKnowledgeEntity extends BaseEntity<OrgKnowledgeEntity> {

    /**
     * 岗位知识ID
     */
    @Schema(description = "岗位知识ID")
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    private Long orgKnowledgeId;

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
     * 操作者ID
     */
    @Schema(description = "操作者ID")
    @Column(value = "operator_id")
    private Long operatorId;

    /**
     * 引用的知识ID
     */
    @Schema(description = "引用的知识ID")
    @Column(value = "reference_id")
    private Long referenceId;

    /**
     * 来源表
     */
    @Schema(description = "来源表")
    @Column(value = "reference_table")
    private String referenceTable;

    /**
     * 内容资源
     */
    @Schema(description = "内容资源")
    @Column(value = "content_uri")
    private String contentUri;

    /**
     * 应用ID
     */
    @Schema(description = "应用ID")
//    @Column(value = "app_id", tenantId = true)
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

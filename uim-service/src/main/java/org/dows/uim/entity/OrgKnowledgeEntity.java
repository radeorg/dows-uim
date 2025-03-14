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

/**
 * 岗位知识表 实体类。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Data(staticConstructor = "create")
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "org_knowledge")
public class OrgKnowledgeEntity extends Model<OrgKnowledgeEntity> {

    /**
     * 岗位知识ID
     */
    @Id(keyType = KeyType.Sequence)
    private Long orgKnowledgeId;

    /**
     * 组织树ID
     */
    @Column(value = "org_tree_id", comment = "组织树ID")
    private Long orgTreeId;

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
     * 引用的知识ID，可以来自[uim,exam]
     */
    @Column(value = "reference_id", comment = "引用的知识ID，可以来自[uim,exam]")
    private Long referenceId;

    /**
     * 来源表，可以来自[uim,exam]表
     */
    @Column(value = "reference_table", comment = "来源表，可以来自[uim,exam]表")
    private String referenceTable;

    /**
     * 内容资源
     */
    @Column(value = "content_uri", comment = "内容资源")
    private String contentUri;

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

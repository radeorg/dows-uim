package org.dows.uim.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.keygen.KeyGenerators;
import com.tangzc.mybatisflex.autotable.annotation.ColumnDefine;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dows.rade.crud.BaseEntity;

import java.util.Date;

/**
 * 组织节点表 实体类。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "组织节点表")
@Table(value = "org_node")
public class OrgNodeEntity extends BaseEntity<OrgNodeEntity> {

    /**
     * 组织节点ID
     */
    @Schema(description = "组织节点ID")
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    @ColumnDefine(comment = "组织节点ID")
    private Long orgNodeId;
    /**
     * 组织树ID
     */
    @Schema(description = "组织树根ID")
    @Column(value = "org_root_id")
    @ColumnDefine(comment = "组织树根ID")
    private Long orgRootId;
    /**
     * 组织树ID
     */
    @Schema(description = "组织树ID")
    @Column(value = "org_tree_id")
    @ColumnDefine(comment = "组织树ID")
    private Long orgTreeId;

    /**
     * 账号实例ID
     */
    @Schema(description = "账号实例ID")
    @Column(value = "account_instance_id")
    @ColumnDefine(comment = "账号实例ID")
    private Long accountInstanceId;

    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    @Column(value = "user_instance_id")
    @ColumnDefine(comment = "用户ID")
    private Long userInstanceId;

    /**
     * 组别名
     */
    @Schema(description = "组别名")
    @Column(value = "alias_name")
    @ColumnDefine(comment = "组别名")
    private String aliasName;

    /**
     * 应用ID
     */
    @Schema(description = "应用ID")
    @Column(value = "app_id")
    @ColumnDefine(comment = "应用ID")
    private String appId;

    /**
     * 版本
     */
    @Schema(description = "版本")
    @Column(value = "ver")
    private Integer ver;

    /**
     * 操作者ID
     */
    @Schema(description = "操作者ID")
    @Column(value = "operator_id")
    private Long operatorId;

    /**
     * 逻辑删除  0未删除  1 删除
     */
    @Schema(description = "逻辑删除  0未删除  1 删除")
    @Column(value = "deleted")
    private Integer deleted;

    /**
     * 时间戳
     */
    @Schema(description = "时间戳")
    @Column(value = "ts")
    private Date ts;


}

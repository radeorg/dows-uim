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
 * 组织节点表 实体类。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Data(staticConstructor = "create")
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "org_node")
public class OrgNodeEntity extends Model<OrgNodeEntity> {

    /**
     * 组织节点ID
     */
    @Id(keyType = KeyType.Sequence)
    private Long orgNodeId;

    /**
     * 组织树ID
     */
    @Column(value = "org_tree_id", comment = "组织树ID")
    private Long orgTreeId;

    /**
     * 账号实例ID
     */
    @Column(value = "account_instance_id", comment = "账号实例ID")
    private Long accountInstanceId;

    /**
     * 用户ID
     */
    @Column(value = "user_instance_id", comment = "用户ID")
    private Long userInstanceId;

    /**
     * 组别名
     */
    @Column(value = "alias_name", comment = "组别名")
    private String aliasName;

    /**
     * 应用ID
     */
    @Column(value = "app_id", comment = "应用ID")
    private String appId;

    /**
     * 版本
     */
    @Column(value = "ver", comment = "版本")
    private Integer ver;

    /**
     * 操作者ID
     */
    @Column(value = "operator_id", comment = "操作者ID")
    private Long operatorId;

    /**
     * 逻辑删除  0未删除  1 删除
     */
    @Column(value = "deleted", comment = "逻辑删除  0未删除  1 删除")
    private Integer deleted;

    /**
     * 时间戳
     */
    @Column(value = "ts", comment = "时间戳")
    private Date ts;


}

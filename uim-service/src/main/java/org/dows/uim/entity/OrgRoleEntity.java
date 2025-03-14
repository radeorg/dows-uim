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
 * 组织角色表 实体类。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Data(staticConstructor = "create")
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "org_role")
public class OrgRoleEntity extends Model<OrgRoleEntity> {

    /**
     * 组织角色ID
     */
    @Id(keyType = KeyType.Sequence)
    private Long orgRoleId;

    /**
     * 组织树ID
     */
    @Column(value = "org_tree_id", comment = "组织树ID")
    private Long orgTreeId;

    /**
     * 角色实例ID
     */
    @Column(value = "rbac_role_id", comment = "角色实例ID")
    private Long rbacRoleId;

    /**
     * 人数
     */
    @Column(value = "population", comment = "人数")
    private Integer population;

    /**
     * 操作者ID
     */
    @Column(value = "operator_id", comment = "操作者ID")
    private Long operatorId;

    /**
     * 乐观锁, 默认: 0
     */
    @Column(value = "ver", comment = "乐观锁, 默认: 0")
    private Integer ver;

    /**
     * 应用ID
     */
    @Column(value = "app_id", comment = "应用ID")
    private String appId;

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

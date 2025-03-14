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
 * 账号角色表 实体类。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Data(staticConstructor = "create")
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "account_role")
public class AccountRoleEntity extends Model<AccountRoleEntity> {

    /**
     * 账号角色ID
     */
    @Id(keyType = KeyType.Sequence)
    private Long accountRoleId;

    /**
     * 账号实例ID
     */
    @Column(value = "account_instance_id", comment = "账号实例ID")
    private Long accountInstanceId;

    /**
     * 角色实例ID
     */
    @Column(value = "rbac_role_id", comment = "角色实例ID")
    private Long rbacRoleId;

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

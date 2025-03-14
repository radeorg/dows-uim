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
 * 账号类型表 实体类。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Data(staticConstructor = "create")
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "account_type")
public class AccountTypeEntity extends Model<AccountTypeEntity> {

    /**
     * 账号类型ID
     */
    @Id(keyType = KeyType.Sequence)
    private Long accountTypeId;

    /**
     * 操作者ID
     */
    @Column(value = "operator_id", comment = "操作者ID")
    private Long operatorId;

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
     * 引用ID(商户表中的ID)
     */
    @Column(value = "reference_id", comment = "引用ID(商户表中的ID)")
    private Long referenceId;

    /**
     * 类型名称[代理商,商户]来自字典表
     */
    @Column(value = "type_name", comment = "类型名称[代理商,商户]来自字典表")
    private String typeName;

    /**
     * 类型值(来自字典表)
     */
    @Column(value = "type_value", comment = "类型值(来自字典表)")
    private Integer typeValue;

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

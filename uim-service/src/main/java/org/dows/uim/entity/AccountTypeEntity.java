package org.dows.uim.entity;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Builder;
import io.swagger.v3.oas.annotations.media.Schema;
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
@RequiredArgsConstructor
@Data(staticConstructor = "create")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "账号类型表")
@Table(value = "account_type")
public class AccountTypeEntity extends Model<AccountTypeEntity> {

    /**
     * 账号类型ID
     */
    @Schema(description = "账号类型ID")
    @Id(keyType = KeyType.Auto)
    private Long accountTypeId;

    /**
     * 操作者ID
     */
    @Schema(description = "操作者ID")
    @Column(value = "operator_id")
    private Long operatorId;

    /**
     * 账号实例ID
     */
    @Schema(description = "账号实例ID")
    @Column(value = "account_instance_id")
    private Long accountInstanceId;

    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    @Column(value = "user_instance_id")
    private Long userInstanceId;

    /**
     * 引用ID(商户表中的ID)
     */
    @Schema(description = "引用ID(商户表中的ID)")
    @Column(value = "reference_id")
    private Long referenceId;

    /**
     * 类型名称[代理商,商户]来自字典表
     */
    @Schema(description = "类型名称[代理商,商户]来自字典表")
    @Column(value = "type_name")
    private String typeName;

    /**
     * 类型值(来自字典表)
     */
    @Schema(description = "类型值(来自字典表)")
    @Column(value = "type_value")
    private Integer typeValue;

    /**
     * 乐观锁, 默认: 0
     */
    @Schema(description = "乐观锁, 默认: 0")
    @Column(value = "ver")
    private Integer ver;

    /**
     * 应用ID
     */
    @Schema(description = "应用ID")
    @Column(value = "app_id")
    private String appId;

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

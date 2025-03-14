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
 * 账号实例表 实体类。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Data(staticConstructor = "create")
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "account_instance")
public class AccountInstanceEntity extends Model<AccountInstanceEntity> {

    /**
     * 账号实例ID
     */
    @Id(keyType = KeyType.Sequence)
    private Long accountInstanceId;

    /**
     * 账号标识符
     */
    @Column(value = "identifier", comment = "账号标识符")
    private String identifier;

    /**
     * 密码
     */
    @Column(value = "password", comment = "密码")
    private String password;

    /**
     * 区域编码(+86，+11...)
     */
    @Column(value = "zone_no", comment = "区域编码(+86，+11...)")
    private String zoneNo;

    /**
     * 手机号
     */
    @Column(value = "cellphone", comment = "手机号")
    private String cellphone;

    /**
     * 头像
     */
    @Column(value = "avator", comment = "头像")
    private String avator;

    /**
     * 推荐码
     */
    @Column(value = "referrals_no", comment = "推荐码")
    private String referralsNo;

    /**
     * 来源(来源渠道推广时标记用)
     */
    @Column(value = "source", comment = "来源(来源渠道推广时标记用)")
    private String source;

    /**
     * 应用ID
     */
    @Column(value = "app_id", comment = "应用ID")
    private String appId;

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

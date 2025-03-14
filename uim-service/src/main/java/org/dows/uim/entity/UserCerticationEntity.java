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
 * 用户证书表 实体类。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Data(staticConstructor = "create")
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "user_certication")
public class UserCerticationEntity extends Model<UserCerticationEntity> {

    /**
     * 用户证书ID
     */
    @Id(keyType = KeyType.Sequence)
    private Long userCerticationId;

    /**
     * 操作者ID
     */
    @Column(value = "operator_id", comment = "操作者ID")
    private Long operatorId;

    /**
     * 用户实例ID
     */
    @Column(value = "user_instance_id", comment = "用户实例ID")
    private Long userInstanceId;

    /**
     * 证书名称
     */
    @Column(value = "cert_name", comment = "证书名称")
    private String certName;

    /**
     * 证书编号
     */
    @Column(value = "cert_no", comment = "证书编号")
    private String certNo;

    /**
     * 应用ID
     */
    @Column(value = "app_id", comment = "应用ID")
    private String appId;

    /**
     * 永久性标识：0-否，1-是
     */
    @Column(value = "perminent", comment = "永久性标识：0-否，1-是")
    private Integer perminent;

    /**
     * 证书获取日期
     */
    @Column(value = "award_date", comment = "证书获取日期")
    private Date awardDate;

    /**
     * 证书有效日期
     */
    @Column(value = "expiry_date", comment = "证书有效日期")
    private Date expiryDate;

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

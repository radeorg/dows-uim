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
 * 用户实例表 实体类。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Data(staticConstructor = "create")
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "user_instance")
public class UserInstanceEntity extends Model<UserInstanceEntity> {

    /**
     * 用户ID
     */
    @Id(keyType = KeyType.Sequence)
    private Long userInstanceId;

    /**
     * 操作者ID
     */
    @Column(value = "operator_id", comment = "操作者ID")
    private Long operatorId;

    /**
     * 用户名
     */
    @Column(value = "user_name", comment = "用户名")
    private String userName;

    /**
     * 身份证号
     */
    @Column(value = "identify_no", comment = "身份证号")
    private String identifyNo;

    /**
     * 用户年龄
     */
    @Column(value = "user_age", comment = "用户年龄")
    private Integer userAge;

    /**
     * 性别
     */
    @Column(value = "sex", comment = "性别")
    private Integer sex;

    /**
     * 应用ID
     */
    @Column(value = "app_id", comment = "应用ID")
    private String appId;

    /**
     * 乐观锁, 默认: 0
     */
    @Column(value = "ver", comment = "乐观锁, 默认: 0")
    private Integer ver;

    /**
     * 时间戳
     */
    @Column(value = "ts", comment = "时间戳")
    private Date ts;


}

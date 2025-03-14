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
 * 用户联系人表 实体类。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Data(staticConstructor = "create")
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "user_contact")
public class UserContactEntity extends Model<UserContactEntity> {

    /**
     * 用户联系人ID
     */
    @Id(keyType = KeyType.Sequence)
    private Long userContactId;

    /**
     * 用户实例ID
     */
    @Column(value = "user_instance_id", comment = "用户实例ID")
    private Long userInstanceId;

    /**
     * 联系人
     */
    @Column(value = "contact", comment = "联系人")
    private String contact;

    /**
     * 联系号码
     */
    @Column(value = "contact_num", comment = "联系号码")
    private String contactNum;

    /**
     * 联系类型（0:手机，1:邮箱，2:电话）
     */
    @Column(value = "contact_typ", comment = "联系类型（0:手机，1:邮箱，2:电话）")
    private Integer contactTyp;

    /**
     * 排序
     */
    @Column(value = "sorted", comment = "排序")
    private Integer sorted;

    /**
     * 是否是自己
     */
    @Column(value = "self", comment = "是否是自己")
    private Integer self;

    /**
     * 状态
     */
    @Column(value = "state", comment = "状态")
    private Integer state;

    /**
     * 乐观锁, 默认: 0
     */
    @Column(value = "ver", comment = "乐观锁, 默认: 0")
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

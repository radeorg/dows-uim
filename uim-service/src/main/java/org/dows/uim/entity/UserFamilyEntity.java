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
 * 用户家庭表 实体类。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Data(staticConstructor = "create")
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "user_family")
public class UserFamilyEntity extends Model<UserFamilyEntity> {

    /**
     * 用户家庭ID
     */
    @Id(keyType = KeyType.Sequence)
    private Long userFamilyId;

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
     * 成员ID[用户ID]
     */
    @Column(value = "member_id", comment = "成员ID[用户ID]")
    private Long memberId;

    /**
     * 关系[父亲|母亲|丈夫|妻子|兄弟|儿子|女儿]
     */
    @Column(value = "relation", comment = "关系[父亲|母亲|丈夫|妻子|兄弟|儿子|女儿]")
    private String relation;

    /**
     * 组建时间
     */
    @Column(value = "build_time", comment = "组建时间")
    private Date buildTime;

    /**
     * 是否户主[0:否，1：是]
     */
    @Column(value = "householder", comment = "是否户主[0:否，1：是]")
    private Integer householder;

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

package org.dows.uim.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.keygen.KeyGenerators;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.dows.rade.crud.BaseEntity;
import org.dows.uim.AutoFillDataListener;

import java.util.Date;

/**
 * 用户家庭表 实体类。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "用户家庭表")
@Table(value = "user_family", onUpdate = AutoFillDataListener.class, onInsert = AutoFillDataListener.class)
public class UserFamilyEntity extends BaseEntity<UserFamilyEntity> {

    /**
     * 用户家庭ID
     */
    @Schema(description = "用户家庭ID")
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    private Long userFamilyId;

    /**
     * 操作者ID
     */
    @Schema(description = "操作者ID")
    @Column(value = "operator_id")
    private Long operatorId;

    /**
     * 用户实例ID
     */
    @Schema(description = "用户实例ID")
    @Column(value = "user_instance_id")
    private Long userInstanceId;

    /**
     * 成员ID[用户ID]
     */
    @Schema(description = "成员ID[用户ID]")
    @Column(value = "member_id")
    private Long memberId;

    /**
     * 关系[父亲|母亲|丈夫|妻子|兄弟|儿子|女儿]
     */
    @Schema(description = "关系[父亲|母亲|丈夫|妻子|兄弟|儿子|女儿]")
    @Column(value = "relation")
    private String relation;

    /**
     * 组建时间
     */
    @Schema(description = "组建时间")
    @Column(value = "build_time")
    private Date buildTime;

    /**
     * 是否户主[0:否，1：是]
     */
    @Schema(description = "是否户主[0:否，1：是]")
    @Column(value = "householder")
    private Integer householder;

    /**
     * 状态
     */
    @Schema(description = "状态")
    @Column(value = "state")
    private Integer state;

    /**
     * 乐观锁，默认为0
     */
    @Schema(description = "乐观锁，默认为0")
    @Column(value = "ver", onUpdateValue = "ver+1")
    private Integer ver;

    /**
     * 逻辑删除，0未删除，1删除
     */
    @Schema(description = "逻辑删除，0未删除，1删除")
    @Column(value = "deleted", isLogicDelete = true)
    private Integer deleted;

    /**
     * 时间戳
     */
    @Schema(description = "时间戳")
    @Column(value = "ts")
    private Date ts;

    @Column(value = "ut")
    private Date ut;

    @Column(value = "owner_id")
    private Long ownerId;


}

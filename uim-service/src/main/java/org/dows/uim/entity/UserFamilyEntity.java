package org.dows.uim.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dows.rade.crud.BaseEntity;

import java.util.Date;

/**
 * 用户家庭表 实体类。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "用户家庭表")
@Table(value = "user_family")
public class UserFamilyEntity extends BaseEntity<UserFamilyEntity> {

    /**
     * 用户家庭ID
     */
    @Schema(description = "用户家庭ID")
    @Id(keyType = KeyType.Auto)
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
     * 乐观锁, 默认: 0
     */
    @Schema(description = "乐观锁, 默认: 0")
    @Column(value = "ver")
    private Integer ver;

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

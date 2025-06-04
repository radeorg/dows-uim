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
 * 用户联系人表 实体类。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "用户联系人表")
@Table(value = "user_contact", onUpdate = AutoFillDataListener.class, onInsert = AutoFillDataListener.class)
public class UserContactEntity extends BaseEntity<UserContactEntity> {

    /**
     * 用户联系人ID
     */
    @Schema(description = "用户联系人ID")
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    private Long userContactId;

    /**
     * 用户实例ID
     */
    @Schema(description = "用户实例ID")
    @Column(value = "user_instance_id")
    private Long userInstanceId;

    /**
     * 联系人
     */
    @Schema(description = "联系人")
    @Column(value = "contact")
    private String contact;

    /**
     * 联系号码
     */
    @Schema(description = "联系号码")
    @Column(value = "contact_num")
    private String contactNum;

    /**
     * 联系类型（0:手机，1:邮箱，2:电话）
     */
    @Schema(description = "联系类型（0:手机，1:邮箱，2:电话）")
    @Column(value = "contact_typ")
    private Integer contactTyp;

    /**
     * 排序
     */
    @Schema(description = "排序")
    @Column(value = "sorted")
    private Integer sorted;

    /**
     * 是否是自己
     */
    @Schema(description = "是否是自己")
    @Column(value = "self")
    private Integer self;

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
     * 操作者ID
     */
    @Schema(description = "操作者ID")
    @Column(value = "operator_id")
    private Long operatorId;

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

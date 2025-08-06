package org.dows.uim.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Table;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.dows.rade.crud.BaseEntity;
import org.dows.rade.crud.AutoFillDataListener;

import java.util.Date;

/**
 * 实体类。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "$table.comment")
@Table(value = "user_certication", onUpdate = AutoFillDataListener.class, onInsert = AutoFillDataListener.class)
public class UserCerticationEntity extends BaseEntity<UserCerticationEntity> {

    @Column(value = "user_certication_id")
    private Long userCerticationId;

    @Column(value = "operator_id")
    private Long operatorId;

    @Column(value = "user_instance_id")
    private Long userInstanceId;

    @Column(value = "cert_name")
    private String certName;

    @Column(value = "cert_no")
    private String certNo;

    @Column(value = "app_id")
    private String appId;

    @Column(value = "perminent")
    private Integer perminent;

    @Column(value = "award_date")
    private Date awardDate;

    @Column(value = "expiry_date")
    private Date expiryDate;

    @Column(value = "deleted", isLogicDelete = true)
    private Integer deleted;

    @Column(value = "ts")
    private Date ts;

    @Column(value = "ut")
    private Date ut;

    @Column(value = "owner_id")
    private Long ownerId;


}

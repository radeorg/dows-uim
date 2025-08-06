package org.dows.uim.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.keygen.KeyGenerators;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dows.rade.crud.AutoFillDataListener;
import org.dows.rade.crud.BaseEntity;

import java.util.Date;

/**
 * @ClassName HrmJdCodeEntity
 * @Description TODO
 * @Author jack.china.ye
 * @Date 2025/6/1 15:04
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "JD码表")
@Table(value = "hrm_jd_code", onUpdate = AutoFillDataListener.class, onInsert = AutoFillDataListener.class)
public class HrmJdCodeEntity extends BaseEntity<HrmJdCodeEntity> {

    /**
     * jd码表ID
     */
    @Schema(description = "jd码表ID")
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    private Long hrmCodeId;

    /**
     * 码编号
     */
    @Schema(description = "码编号")
    @Column(value = "code")
    private Integer code;

    /**
     * 码值
     */
    @Schema(description = "码值")
    @Column(value = "value")
    private String value;

    /**
     * 类型
     */
    @Schema(description = "类型")
    @Column(value = "code_type")
    private String codeType;

    /**
     * 类型名称
     */
    @Schema(description = "类型名称")
    @Column(value = "type_name")
    private String typeName;

    /**
     * 应用ID
     */
    @Schema(description = "应用ID")
//    @Column(value = "app_id", tenantId = true)
    private String appId;

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

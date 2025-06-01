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
  *@ClassName HrmFeatureBenefitsEntity
  *@Description TODO
  *@Author jack.china.ye
  *@Date 2025/6/1 14:54
  */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "企业福利特色映射表")
@Table(value = "hrm_feature_benefits")
public class HrmFeatureBenefitsEntity extends BaseEntity<HrmFeatureBenefitsEntity> {

    /**
     * 福利特色ID
     */
    @Schema(description = "福利特色ID")
    @Id(keyType = KeyType.Auto)
    private Long hrmFeatureBenefitsId;

    /**
     * 岗位编号
     */
    @Schema(description = "岗位编号")
    @Column(value = "jd_no")
    private String jdNo;

    /**
     * 码值编号
     */
    @Schema(description = "码值编号")
    @Column(value = "code")
    private Integer code;

    /**
     * 1:福利；2：特色
     */
    @Schema(description = "1:福利；2：特色")
    @Column(value = "code_type")
    private Integer codeType;

    /**
     * 应用ID
     */
    @Schema(description = "应用ID")
    @Column(value = "app_id")
    private String appId;

    /**
     * 逻辑删除，0未删除，1删除
     */
    @Schema(description = "逻辑删除，0未删除，1删除")
    @Column(value = "deleted")
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


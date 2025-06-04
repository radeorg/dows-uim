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
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    private Long hrmFeatureBenefitsId;

    /**
     * 岗位编号
     */
    @Schema(description = "岗位编号")
    @Column(value = "jd_no")
    private String jdNo;

    /**
     * 月薪范围
     */
    @Schema(description = "月薪范围[1 \"1：万以下\",2 ：\"1-1.5万\", 3：\"1.5-2万\", 4：\"2-2.5万\", 5：\"2.5-3万\",6：\"面议\"]")
    @Column(value = "monthly_salary_range")
    private Integer monthlySalaryRange;

    /**
     * 工作模式
     */
    @Schema(description = "工作模式[1\"全职坐班\", 2\"混合办公\",3 \"全员远程\"]")
    @Column(value = "work_mode")
    private Integer workMode;

    /**
     * 码值编号
     */
    @Schema(description = "福利")
    @Column(value = "benefit")
    private String benefit;

    /**
     * 1:福利；2：特色
     */
    @Schema(description = "特色")
    @Column(value = "feature")
    private String feature;

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


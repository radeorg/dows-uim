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
 *@ClassName HrmFeatureBenefitsEntity
 *@Description TODO
 *@Author jack.china.ye
 *@Date 2025/6/1 14:54
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "企业情况表")
@Table(value = "hrm_enterprise_situation", onUpdate = AutoFillDataListener.class, onInsert = AutoFillDataListener.class)
public class HrmEnterpriseSituationEntity extends BaseEntity<HrmEnterpriseSituationEntity> {

    /**
     * 企业情况ID
     */
    @Schema(description = "企业情况ID")
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    private Long hrmEnterpriseSituationId;

    /**
     * 变更前id
     */
    @Schema(description = "变更前id")
    @Column(value = "old_enterprise_situation_id")
    private Long oldEnterpriseSituationId;

    /**
     * 企业规模
     */
    @Schema(description = "企业规模")
    @Column(value = "company_scale")
    private Integer companyScale;

    /**
     * 融资阶段
     */
    @Schema(description = "融资阶段")
    @Column(value = "financing_stage")
    private Integer financingStage;

    /**
     * 项目类型
     */
    @Schema(description = "项目类型")
    @Column(value = "project_type")
    private Integer projectType;

    /**
     * 项目进展
     */
    @Schema(description = "项目进展")
    @Column(value = "project_progress")
    private Integer projectProgress;


    /**
     * 已有同类岗位
     */
    @Schema(description = "已有同类岗位")
    @Column(value = "similar_positions")
    private String similarPositions;

    /**
     * 企业信息变更记录
     */
    @Schema(description = "企业信息变更记录")
    @Column(value = "remark")
    private String remark;

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

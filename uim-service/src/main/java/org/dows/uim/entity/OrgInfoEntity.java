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
 * 组织信息表 实体类。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "组织信息表")
@Table(value = "org_info")
public class OrgInfoEntity extends BaseEntity<OrgInfoEntity> {

    /**
     * 组织信息ID
     */
    @Schema(description = "组织信息ID")
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    private Long orgInfoId;

    /**
     * 组织角色ID
     */
    @Schema(description = "组织角色ID")
    @Column(value = "org_role_id")
    private Long orgRoleId;

    /**
     * 组织树ID
     */
    @Schema(description = "组织树ID")
    @Column(value = "org_tree_id")
    private Long orgTreeId;

    /**
     * 操作者ID
     */
    @Schema(description = "操作者ID")
    @Column(value = "operator_id")
    private Long operatorId;

    /**
     * 组织信息JSON
     */
    @Schema(description = "组织信息JSON")
    @Column(value = "org_info")
    private String orgInfo;

    /**
     * 组织地址
     */
    @Schema(description = "组织地址")
    @Column(value = "org_address")
    private String orgAddress;

    /**
     * 组织亮点(Json格式）
     */
    @Schema(description = "组织亮点(Json格式）")
    @Column(value = "feature_json")
    private String featureJson;

    /**
     * 人员数量
     */
    @Schema(description = "人员数量")
    @Column(value = "headcount")
    private Integer headcount;

    /**
     * 电子邮箱
     */
    @Schema(description = "电子邮箱")
    @Column(value = "email")
    private String email;

    /**
     * 审核备注
     */
    @Schema(description = "审核备注")
    @Column(value = "remark")
    private String remark;

    /**
     * 审核时间戳
     */
    @Schema(description = "审核时间戳")
    @Column(value = "approve_ts")
    private Date approveTs;

    /**
     * 审批状态:1-同意，2-拒绝；
     */
    @Schema(description = "审批状态:1-同意，2-拒绝；")
    @Column(value = "state")
    private Integer state;

    /**
     * 乐观锁，默认为0
     */
    @Schema(description = "乐观锁，默认为0")
    @Column(value = "ver")
    private Integer ver;

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

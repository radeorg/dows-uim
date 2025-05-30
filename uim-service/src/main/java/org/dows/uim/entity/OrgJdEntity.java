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
 * 岗位JD表 实体类。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "岗位JD表")
@Table(value = "org_jd", onUpdate = AutoFillDataListener.class, onInsert = AutoFillDataListener.class)
public class OrgJdEntity extends BaseEntity<OrgJdEntity> {

    /**
     * 岗位描述ID
     */
    @Schema(description = "岗位描述ID")
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    private Long orgJdId;

    /**
     * 组织树ID
     */
    @Schema(description = "组织树ID")
    @Column(value = "org_root_id")
    private Long orgRootId;

    /**
     * 组织树ID
     */
    @Schema(description = "组织树ID")
    @Column(value = "org_tree_id")
    private Long orgTreeId;

    /**
     * 岗位规则ID
     */
    @Schema(description = "岗位规则ID")
    @Column(value = "org_rule_id")
    private Long orgRuleId;

    @Schema(description = "岗位地址")
    @Column(value = "org_address")
    private String orgAddress;

    @Schema(description = "职位类别ID")
    @Column(value = "org_jd_category_id")
    private Long orgJdCategoryId;
    /**
     * 人事账号ID
     */
    @Schema(description = "人事账号ID")
    @Column(value = "owner_id")
    private Long ownerId;

    /**
     * 操作者ID
     */
    @Schema(description = "操作者ID")
    @Column(value = "operator_id")
    private Long operatorId;

    /**
     * 岗位名称+岗位编号
     */
    @Schema(description = "岗位编号")
    @Column(value = "jd_no")
    private String jdNo;

    /**
     * 岗位名称+岗位编号
     */
    @Schema(description = "岗位名称+岗位编号")
    @Column(value = "jd_name")
    private String jdName;

    /**
     * 岗位名称
     */
    @Schema(description = "性别要求0：不限1:男；2：女；3：优先男性；4：优先女性")
    @Column(value = "gender")
    private Integer gender;

    /**
     * 岗位名称
     */
    @Schema(description = "年龄范围 0：\"不限\",1： \"25-30岁\",2： \"30-35岁\",3： \"35-40岁\", 4：\"40岁以上\"")
    @Column(value = "age_range")
    private Integer ageRange;

    /**
     * 岗位名称
     */
    @Schema(description = "工作经验 0\"不限\", 1\"应届\",2 \"1-3年\",3 \"3-5年\", 4\"5-10年\",5 \"10年以上\"")
    @Column(value = "work_exper")
    private Integer workExper;

    /**
     * 岗位名称
     */
    @Schema(description = "最低学历0\"不限\",1 \"大专及以上\", 2\"本科及以上\",3 \"硕士及以上\", 4\"博士及以上\"")
    @Column(value = "min_education")
    private Integer minEducation;

    /**
     * 岗位名称
     */
    @Schema(description = "其他要求")
    @Column(value = "other_require")
    private String otherRequire;


    /**
     * 岗位描述
     */
    @Schema(description = "岗位描述")
    @Column(value = "description")
    private String description;

    /**
     * 状态[1:下架，2:上架]
     */
    @Schema(description = "状态[1:下架，2:上架]")
    @Column(value = "state")
    private Integer state;

    /**
     * 发布渠道集合
     */
    @Schema(description = "发布渠道集合")
    @Column(value = "channels")
    private String channels;

    /**
     * 应用ID
     */
    @Schema(description = "应用ID")
    @Column(value = "app_id", tenantId = true)
    private String appId;

    /**
     * 时间戳
     */
    @Schema(description = "时间戳")
    @Column(value = "ts")
    private Date ts;

    @Column(value = "ut")
    private Date ut;

    /**
     * 逻辑删除，0未删除，1删除
     */
    @Schema(description = "逻辑删除，0未删除，1删除")
    @Column(value = "deleted")
    private Integer deleted;
}
